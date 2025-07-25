import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucketImpl {

    public class LeakyBucket {
        /**
         * BlockingQueue: thread safe
         * For distributed system, we need to make this variable globally accessible.
         * We can use redis for it.
         */
        BlockingQueue<Integer> queue;

        public LeakyBucket(int capacity) {
            this.queue = new LinkedBlockingQueue<>(capacity);
        }

        public boolean grantAccess(int id) {
            if (queue.remainingCapacity() > 0) {
                queue.add(id);
                return true;
            }
            return false;
        }

        public void transmitRequest(int id) {
            // this function ca be called at a fixed rate to simulate the
            // leaking principle.
        }
    }

    public class UserBucketCreator {
        Map<Integer, LeakyBucket> bucket;

        public UserBucketCreator(int id) {
            bucket = new HashMap<>();
            bucket.put(id, new LeakyBucket(10));
        }

        void accessApplication(int id) {
            if (bucket.get(id).grantAccess(id)) {
                System.out.println(Thread.currentThread().getName() + " -> able to access the application");
            } else {
                System.out
                        .println(Thread.currentThread().getName() + " -> Too many request, Please try after some time");
            }
        }
    }

    public static void main(String[] args) {
        UserBucketCreator userBucketCreator = new LeakyBucketImpl().new UserBucketCreator(1);
        ExecutorService executors = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            executors.execute(() -> userBucketCreator.accessApplication(1));
            // here we are accessing the same id at rate 12 from different threads
        }
        executors.shutdown();
    }
}
