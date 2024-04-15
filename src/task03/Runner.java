package task03;

/*
Три бегуна должны прийти к старту гонки.
Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте.
Программа должна отсчитать "На старт", "Внимание", "Марш!"
Программа должна завершиться когда все участники закончат гонку.
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {
    private final int delay;
    private final CountDownLatch latch;
    private final CountDownLatch latch2;
    private final CountDownLatch latch3;
    private final String name;
    private Random random = new Random();

    public Runner(int delay, CountDownLatch latch, CountDownLatch latch2,
                  CountDownLatch latch3, String name) {
        this.delay = delay;
        this.latch = latch;
        this.latch2 = latch2;
        this.latch3 = latch3;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(name + ": I'm ready to start!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            latch3.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(random.nextInt(3000, 5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        latch2.countDown();
        Race.winners.add(name);
        System.out.println(name + ": finished the race!");
    }
}
