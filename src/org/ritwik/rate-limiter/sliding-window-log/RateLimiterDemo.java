public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        // Allow up to 3 requests per 5 seconds
        SlidingWindowLogRateLimiter limiter = new SlidingWindowLogRateLimiter(3, 5000);

        String user = "user-123";
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf("Request %d → %s%n", i, allowed ? "ALLOWED" : "RATE‑LIMITED");
        }
    }
}
