import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {
    private final long capacity;
    private final long refillTokens;
    private final long refillPeriod;
    private long tokens;
    private long lastRefillTimestamp;

    private final Lock lock = new ReentrantLock();

    public TokenBucket(long capacity, long refillTokens, long refillPeriod, TimeUnit unit) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillPeriod = unit.toNanos(refillPeriod);
        this.tokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    public boolean tryConsume(int numTokens) {
        lock.lock();
        try {
            refill();

            if (tokens >= numTokens) {
                tokens -= numTokens;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        long duration = now - lastRefillTimestamp;

        if (duration > refillPeriod) {
            long newTokens = (duration / refillPeriod) * refillTokens;
            tokens = Math.min(capacity, tokens + newTokens);
            lastRefillTimestamp = now - (duration % refillPeriod);
        }
    }

    public static void main(String[] args) {
        // Create a token bucket with capacity of 10 tokens, refilling 1 token every
        // second
        TokenBucket tokenBucket = new TokenBucket(10, 1, 1, TimeUnit.SECONDS);

        // Simulate consuming tokens
        for (int i = 0; i < 20; i++) {
            boolean allowed = tokenBucket.tryConsume(1);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rate Limited"));

            try {
                Thread.sleep(500); // Wait 0.5 seconds before the next request
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
