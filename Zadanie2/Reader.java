package Zadanie2;
import java.util.concurrent.CopyOnWriteArrayList;

public class Reader implements Runnable {
    private final CopyOnWriteArrayList<Integer> listOfNumbers;

    public Reader(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        while (listOfNumbers.size() < 60) {
            if (!listOfNumbers.isEmpty()) {
                System.out.println("Прочитано: " + listOfNumbers.get(0));
                listOfNumbers.remove(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

