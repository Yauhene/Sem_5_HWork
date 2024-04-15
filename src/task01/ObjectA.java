package task01;

public class ObjectA {

    public synchronized void stepFirst(ObjectB objectB) {
        String name = Thread.currentThread().getName();
        System.out.println("Вошли в ObjectA.stepFirst() " + "поток " + name);
        System.out.println(name + " is in the ObjectA.stepFirst()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(name + " is interrupted");
        }
        System.out.println(name + " tries to call stepTwo()");
        objectB.stepTwo();
    }

    public synchronized void stepTwo() {
        System.out.println(Thread.currentThread().getName() + " -> stepTwo()");
    }
}
