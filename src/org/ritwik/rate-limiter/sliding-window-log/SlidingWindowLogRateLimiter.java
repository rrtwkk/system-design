import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple Sliding Window Log Rate Limiter
 * 
 * Maintains a log of request timestamps for each client.
 * Provides exact counting within the time window.
 */
public class SlidingWindowLogRateLimiter {

    private final int maxRequests;
    private final long windowSizeMs;
    private final Map<String, LinkedList<Long>> clientLogs;

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.clientLogs = new ConcurrentHashMap<>();
    }

    /**
     * Check if request is allowed for the given client
     */
    public synchronized boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();

        LinkedList<Long> timestamps = clientLogs.computeIfAbsent(clientId, k -> new LinkedList<>());

        // Remove expired timestamps
        removeExpiredRequests(timestamps, currentTime);

        // Check if we can accept this request
        if (timestamps.size() < maxRequests) {
            timestamps.add(currentTime);
            return true;
        }

        return false;
    }

    /**
     * Get current request count for a client
     */
    public synchronized int getCurrentRequestCount(String clientId) {
        long currentTime = System.currentTimeMillis();
        LinkedList<Long> timestamps = clientLogs.get(clientId);

        if (timestamps == null) {
            return 0;
        }

        removeExpiredRequests(timestamps, currentTime);
        return timestamps.size();
    }

    /**
     * Get remaining requests allowed for a client
     */
    public synchronized int getRemainingRequests(String clientId) {
        int current = getCurrentRequestCount(clientId);
        return Math.max(0, maxRequests - current);
    }

    /**
     * Remove timestamps that are outside the current window
     */
    private void removeExpiredRequests(LinkedList<Long> timestamps, long currentTime) {
        long windowStart = currentTime - windowSizeMs;

        while (!timestamps.isEmpty() && timestamps.peek() <= windowStart) {
            timestamps.poll();
        }
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMs() {
        return windowSizeMs;
    }

    public int getActiveClients() {
        return clientLogs.size();
    }
}