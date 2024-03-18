package Zadanie4;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Patient implements Runnable {
    private static final Semaphore doctor = new Semaphore(1);
    private static final Semaphore mri = new Semaphore(1);
    private static final AtomicInteger queueLength = new AtomicInteger(0);
    private static final AtomicInteger maxQueueLength = new AtomicInteger(0);
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int id;

    public Patient() {
        id = idCounter.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            queueLength.incrementAndGet();
            maxQueueLength.set(Math.max(queueLength.get(), maxQueueLength.get()));
            doctor.acquire();
            System.out.println("Пациент " + id + " пришел к терапевту");
            queueLength.decrementAndGet();
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
            mri.acquire();
            System.out.println("Пациент " + id + " пошел на МРТ");
            doctor.release();
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
            mri.release();
            System.out.println("Пациент " + id + " завершил обследование на МРТ");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Patient());
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Максимальная длина очереди: " + maxQueueLength.get());
    }
}
