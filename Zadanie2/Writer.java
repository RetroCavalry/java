package Zadanie2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Writer implements Runnable {
    private final CopyOnWriteArrayList<Integer> listOfNumbers;

    public Writer(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized(listOfNumbers) {
                if (listOfNumbers.size() >= 60) {
                    break;
                }
                listOfNumbers.add(i++);
                System.out.println("Записано: " + i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

