/**
 * Simple demonstration of the Sliding Window Log Rate Limiter
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        System.out.println("=== Simple Sliding Window Log Rate Limiter Demo ===\n");

        basicDemo();
        multipleClientsDemo();
    }

    private static void basicDemo() {
        System.out.println("Basic Functionality Demo");
        System.out.println("-------------------------");

        // Create rate limiter: 3 requests per 5 seconds
        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(3, 5000);
        String clientId = "user1";

        System.out.printf("Rate Limiter: %d requests per %d ms\n",
                rateLimiter.getMaxRequests(), rateLimiter.getWindowSizeMs());

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

    private static void multipleClientsDemo() {
        System.out.println("Multiple Clients Demo");
        System.out.println("---------------------");

        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(2, 3000);
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