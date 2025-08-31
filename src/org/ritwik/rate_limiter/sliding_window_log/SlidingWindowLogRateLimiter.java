import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sliding‑window “log” rate limiter.
 *
 * Tracks each request timestamp for a client, prunes expired entries
 * on every check, and allows up to maxRequests per windowSizeInMillis.
 */
public class SlidingWindowLogRateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;

    // clientKey → deque of request timestamps (millis)
    private final ConcurrentHashMap<String, Deque<Long>> requestLogs = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    /**
     * Returns true if this request is allowed for the given clientKey.
     */
    public boolean allowRequest(String clientKey) {
        long now = System.currentTimeMillis();

        // Fetch-or-create the per‑client log
        Deque<Long> timestamps = requestLogs.computeIfAbsent(
                clientKey,
                key -> new LinkedList<>());

        synchronized (timestamps) {
            // 1) Remove outdated timestamps
            long cutoff = now - windowSizeInMillis;
            while (!timestamps.isEmpty() && timestamps.peekFirst() <= cutoff) {
                timestamps.pollFirst();
            }

            // 2) Check allowance
            if (timestamps.size() < maxRequests) {
                // 3) Record this request
                timestamps.addLast(now);
                return true;
            } else {
                return false;
            }
        }
    }
}
