import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {
    private final int windowSizeInSeconds;
    private final int maxRequests;
    private final ConcurrentHashMap<String, Window> clientToWindowMap = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int windowSizeInSeconds, int maxRequests) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequests = maxRequests;
    }

    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis() / 1000;
        Window window = clientToWindowMap.computeIfAbsent(clientId, k -> new Window(currentTime));

        synchronized (window) {
            // If the current time is beyond the window's duration, it resets the window
            if (currentTime >= window.startTime + windowSizeInSeconds) {
                window.startTime = currentTime;
                window.requestCount.set(0);
            }

            if (window.requestCount.incrementAndGet() <= maxRequests) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class Window {
        private long startTime;// start time of the window
        private AtomicInteger requestCount;// no. of req in current window

        public Window(long startTime) {
            this.startTime = startTime;
            this.requestCount = new AtomicInteger(0);
        }
    }

    public static void main(String[] args) {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(10, 5); // 10 seconds window, 5 max requests

        String clientId = "client1";
        for (int i = 0; i < 10; i++) {
            if (rateLimiter.allowRequest(clientId)) {
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " denied");
            }
            try {
                Thread.sleep(1000); // Simulate time between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
