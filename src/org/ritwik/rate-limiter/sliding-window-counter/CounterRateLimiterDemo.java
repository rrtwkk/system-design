public class CounterRateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        // allow max 5 requests per 10 seconds
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 10_000);

        String userId = "user-42";
        for (int i = 1; i <= 7; i++) {
            boolean allowed = limiter.allowRequest(userId);
            System.out.printf("Request #%d: %s%n", i, allowed ? "ALLOWED" : "RATE‑LIMITED");
            if (i == 5) {
                // pause just a bit to show sliding behavior
                Thread.sleep(6_000);
            }
        }
    }
}