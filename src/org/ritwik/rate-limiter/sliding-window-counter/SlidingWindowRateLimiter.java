import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple sliding‑window counter rate limiter.
 * 
 * For each clientKey it keeps:
 * - current bucket start time & count
 * - previous bucket count
 * 
 * To check allowance, it:
 * 1) rotates buckets if time moved on,
 * 2) computes a weighted total = currentCount + previousCount * (remainingTime
 * / windowSize),
 * 3) compares against maxRequests.
 */
public class SlidingWindowRateLimiter {
    private final long windowSizeInMillis;
    private final int maxRequests;
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    /**
     * Returns true if the request for clientKey is allowed;
     * otherwise false.
     */
    public boolean allowRequest(String clientKey) {
        long now = System.currentTimeMillis();
        // floor to bucket start
        long currentWindowStart = now - (now % windowSizeInMillis);

        // fetch-or-create per‑client window
        Window w = windows.computeIfAbsent(clientKey, k -> new Window(currentWindowStart));

        synchronized (w) {
            // rotate buckets if we've moved into a new bucket
            if (w.windowStart != currentWindowStart) {
                // if exactly one bucket passed, shift current→previous
                if (currentWindowStart - w.windowStart == windowSizeInMillis) {
                    w.previousCount = w.currentCount.get();
                } else {
                    // more than one full bucket passed → discard old data
                    w.previousCount = 0;
                }
                w.currentCount.set(0);
                w.windowStart = currentWindowStart;
            }

            // compute weighted sum:
            long elapsedInWindow = now - w.windowStart;
            double weight = (double) (windowSizeInMillis - elapsedInWindow) / windowSizeInMillis;
            double slidingCount = w.currentCount.get() + w.previousCount * weight;

            if (slidingCount >= maxRequests) {
                return false;
            } else {
                w.currentCount.incrementAndGet();
                return true;
            }
        }
    }

    /** Holds state for one client. */
    private static class Window {
        // start timestamp of the “current” bucket
        long windowStart;
        // count in the previous bucket
        int previousCount;
        // count in the current bucket
        AtomicInteger currentCount = new AtomicInteger(0);

        Window(long windowStart) {
            this.windowStart = windowStart;
        }
    }
}
