/**
 * Simple demonstration of the Sliding Window Counter Rate Limiter
 */
public class CounterRateLimiterDemo {

    public static void main(String[] args) {
        System.out.println("=== Simple Sliding Window Counter Rate Limiter Demo ===\n");

        basicDemo();
        bucketSizeDemo();
        multipleClientsDemo();
    }

    private static void basicDemo() {
        System.out.println("Basic Functionality Demo");
        System.out.println("-------------------------");

        // Create rate limiter: 3 requests per 5 seconds
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(3, 5000);
        String clientId = "user1";

        System.out.printf("Rate Limiter: %d requests per %d ms (bucket size: %d ms)\n",
                rateLimiter.getMaxRequests(), rateLimiter.getWindowSizeMs(), rateLimiter.getBucketSizeMs());

        // Make 5 requests rapidly
        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiter.allowRequest(clientId);
            int current = rateLimiter.getCurrentRequestCount(clientId);
            int remaining = rateLimiter.getRemainingRequests(clientId);

            System.out.printf("Request %d: %s (Current: %d, Remaining: %d)\n",
                    i, allowed ? "ALLOWED" : "DENIED", current, remaining);
        }

        System.out.println();
    }

    private static void bucketSizeDemo() {
        System.out.println("Bucket Size Comparison Demo");
        System.out.println("----------------------------");

        String clientId = "user2";

        // Test with different bucket sizes
        System.out.println("Large buckets (2 second):");
        testBucketSize(clientId + "_large", 3, 6000, 2000);

        System.out.println("\nSmall buckets (1 second):");
        testBucketSize(clientId + "_small", 3, 6000, 1000);

        System.out.println();
    }

    private static void testBucketSize(String clientId, int maxRequests, long windowMs, long bucketMs) {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(maxRequests, windowMs,
                bucketMs);

        for (int i = 1; i <= 4; i++) {
            boolean allowed = rateLimiter.allowRequest(clientId);
            int count = rateLimiter.getCurrentRequestCount(clientId);

            System.out.printf("  Request %d: %s (Count: %d)\n",
                    i, allowed ? "ALLOWED" : "DENIED", count);
        }
    }

    private static void multipleClientsDemo() {
        System.out.println("Multiple Clients Demo");
        System.out.println("---------------------");

        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(2, 3000);
        String[] clients = { "alice", "bob", "charlie" };

        for (String client : clients) {
            System.out.printf("\nClient: %s\n", client);
            for (int i = 1; i <= 3; i++) {
                boolean allowed = rateLimiter.allowRequest(client);
                int current = rateLimiter.getCurrentRequestCount(client);
                int remaining = rateLimiter.getRemainingRequests(client);

                System.out.printf("  Request %d: %s (Current: %d, Remaining: %d)\n",
                        i, allowed ? "ALLOWED" : "DENIED", current, remaining);
            }
        }

        System.out.printf("\nTotal active clients: %d\n", rateLimiter.getActiveClients());
        System.out.println();
    }
}