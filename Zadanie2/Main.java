package Zadanie2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>();
        new Thread(new Reader(listOfNumbers)).start();
        new Thread(new Writer(listOfNumbers)).start();
    }
}
