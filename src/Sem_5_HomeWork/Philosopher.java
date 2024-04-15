package Sem_5_HomeWork;

public class Philosopher implements Runnable {
    private static final int EAT_DURATION = 500;
    private static final int NUM_OF_EAT_TIMES = 3;

    private static Object lock = new Object();
    private static int currentPhilosopher = 0;

    private int id;

    public Philosopher(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_OF_EAT_TIMES; i++) {
            synchronized (lock) {
                while (currentPhilosopher != id) {
                    try {

                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Философ #" + id + " кушают...");
                try {
                    Thread.sleep(EAT_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Философ #" + id + " откушали с аппетитом.");
                System.out.println("     Философ #" + id + ": \'Подумаем о высоком... Эта работа меня убьет\'");

                currentPhilosopher = (currentPhilosopher + 1) % 5;

                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Thread[] workers = new Thread[5];

        for (int i = 0; i < 5; i++) {
            workers[i] = new Thread(new Philosopher(i));
            workers[i].start();
        }
    }
}
