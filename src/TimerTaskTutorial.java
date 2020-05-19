import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TimerTaskTutorial {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Running: " + new java.util.Date());
        }
    }

    private static void print() {
        System.out.println("Running: " + new java.util.Date());
    }

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running: " + new java.util.Date());
            }
        };

        executorService.scheduleAtFixedRate(
                r,
                0,
                1,
                TimeUnit.SECONDS);

        //Thread.sleep(5000);

        //executorService.shutdown();

        System.out.println("Reaches the end of main");
    }
}

