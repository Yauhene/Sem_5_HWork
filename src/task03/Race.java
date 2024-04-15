package task03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
3 бегуна должны прийти к старту гонки
Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
Программа должна отсчитать “На старт”, “Внимание”, “Марш”
Программа должна завершиться когда все участники закончат гонку
 */
public class Race {
    static List<String> winners = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        CountDownLatch latch2 = new CountDownLatch(3);
        CountDownLatch latch3 = new CountDownLatch(1);


        Thread runner1 = new Runner(3000, latch, latch2, latch3, "First runner");
        Thread runner2 = new Runner(2000, latch, latch2, latch3, "Second runner");
        Thread runner3 = new Runner(1000, latch, latch2, latch3, "Third runner");

        runner1.start();
        runner2.start();
        runner3.start();

        latch.await();

        System.out.println("На старт!");
        Thread.sleep(1000);
        System.out.println("Внимание!");
        Thread.sleep(1000);
        System.out.println("Марш!");
        latch3.countDown();

//        Thread runner4 = new Runner(2500, latch2, "First runner");
//        Thread runner5 = new Runner(1700, latch2, "Second runner");
//        Thread runner6 = new Runner(4000, latch2, "Third runner");
//
//        runner4.start();
//        runner5.start();
//        runner6.start();

        latch2.await();
        // строка ниже не пошла, неизвестная функция getFirst()  ???
//        System.out.println("Race is finished!\nThe winner is " + winners.getFirst() + "!");

        System.out.println("Race is finished!\nThe winner is " + winners.get(0) + "!");
    }
}
