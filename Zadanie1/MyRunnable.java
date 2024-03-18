package Zadanie1;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("ID потока: " + Thread.currentThread().getId());
    }
}

class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        Thread thread3 = new Thread(new MyRunnable());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

