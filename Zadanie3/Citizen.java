package Zadanie3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Citizen implements Runnable {
    private static final boolean[] windows = new boolean[3];
    private static final AtomicInteger[] left = new AtomicInteger[3];
    private static final AtomicInteger[] total = new AtomicInteger[3];
    private final int category;

    public Citizen(int category) {
        this.category = category;
        total[category].incrementAndGet();
    }

    @Override
    public void run() {
        int window = ThreadLocalRandom.current().nextInt(3);
        if (windows[window] || (window != 0 && window != category)) {
            left[category].incrementAndGet();
        } else {
            windows[window] = true;
            windows[window] = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            left[i] = new AtomicInteger();
            total[i] = new AtomicInteger();
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(new Citizen(ThreadLocalRandom.current().nextInt(3))).start();
            Thread.sleep(10);
        }

        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            System.out.println("Категория " + i + ": " + (100.0 * left[i].get() / total[i].get()) + "% ушли");
        }
    }
}




