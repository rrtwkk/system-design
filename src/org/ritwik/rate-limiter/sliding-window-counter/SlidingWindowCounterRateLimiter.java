import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple Sliding Window Counter Rate Limiter
 * 
 * Uses time buckets to track request counts.
 * More memory efficient than log-based approach.
 */
public class SlidingWindowCounterRateLimiter {

    private final int maxRequests;
    private final long windowSizeMs;
    private final long bucketSizeMs;
    private final Map<String, ClientBuckets> clientBuckets;

    public SlidingWindowCounterRateLimiter(int maxRequests, long windowSizeMs) {
        this(maxRequests, windowSizeMs, windowSizeMs / 10); // Default: 10 buckets
    }

    public SlidingWindowCounterRateLimiter(int maxRequests, long windowSizeMs, long bucketSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.bucketSizeMs = Math.max(1000, bucketSizeMs); // Minimum 1 second buckets
        this.clientBuckets = new ConcurrentHashMap<>();
    }

    /**
     * Check if request is allowed for the given client
     */
    public synchronized boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();

        ClientBuckets buckets = clientBuckets.computeIfAbsent(clientId, k -> new ClientBuckets());

        // Clean up old buckets and count current requests
        int currentCount = getCurrentCount(buckets, currentTime);

        // Check if we can accept this request
        if (currentCount < maxRequests) {
            addRequest(buckets, currentTime);
            return true;
        }

        return false;
    }

    /**
     * Get current estimated request count for a client
     */
    public synchronized int getCurrentRequestCount(String clientId) {
        long currentTime = System.currentTimeMillis();
        ClientBuckets buckets = clientBuckets.get(clientId);

        if (buckets == null) {
            return 0;
        }

        return getCurrentCount(buckets, currentTime);
    }

    /**
     * Get remaining requests allowed for a client
     */
    public synchronized int getRemainingRequests(String clientId) {
        int current = getCurrentRequestCount(clientId);
        return Math.max(0, maxRequests - current);
    }

    /**
     * Add a request to the current bucket
     */
    private void addRequest(ClientBuckets buckets, long currentTime) {
        long bucketKey = currentTime / bucketSizeMs;
        buckets.buckets.put(bucketKey, buckets.buckets.getOrDefault(bucketKey, 0) + 1);
    }

    /**
     * Count requests in the current window and clean up old buckets
     */
    private int getCurrentCount(ClientBuckets buckets, long currentTime) {
        // key Logic
        long oldestValidBucketId = (currentTime - windowSizeMs) / bucketSizeMs;
        long latestBucketId = currentTime / bucketSizeMs;

        // Remove old buckets
        buckets.buckets.entrySet().removeIf(entry -> entry.getKey() <= oldestValidBucketId);

        // Count requests in current window
        int totalCount = 0;
        for (Map.Entry<Long, Integer> entry : buckets.buckets.entrySet()) {
            if (entry.getKey() > oldestValidBucketId && entry.getKey() <= latestBucketId) {
                totalCount += entry.getValue();
            }
        }

        return totalCount;
    }

    /**
     * Simple bucket storage for a client
     */
    private static class ClientBuckets {
        /**
         * Time Buckets (2-second buckets):
         * ┌─────────┬─────────┬─────────┬─────────┬─────────┐
         * │ 0-2s │ 2-4s │ 4-6s │ 6-8s │ 8-10s │
         * │ Bucket0 │ Bucket1 │ Bucket2 │ Bucket3 │ Bucket4 │
         * └─────────┴─────────┴─────────┴─────────┴─────────┘
         * 
         * Map contents:
         * buckets = {
         * 0 → 3, // 3 requests in bucket 0 (0-2 seconds)
         * 1 → 5, // 5 requests in bucket 1 (2-4 seconds)
         * 2 → 2, // 2 requests in bucket 2 (4-6 seconds)
         * 3 → 1 // 1 request in bucket 3 (6-8 seconds)
         * }
         */
        final Map<Long, Integer> buckets = new HashMap<>();
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMs() {
        return windowSizeMs;
    }

    public long getBucketSizeMs() {
        return bucketSizeMs;
    }

    public int getActiveClients() {
        return clientBuckets.size();
    }
}