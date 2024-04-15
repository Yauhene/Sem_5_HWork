package task01;

public class ObjectB {

    public synchronized void stepFirst(ObjectA objectA) {
        String name = Thread.currentThread().getName();
        System.out.println("Вошли в ObjectB.stepFirst() " + "поток " + name);
        System.out.println(name + " is in the ObjectB.stepFirst()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(name + " is interrupted");
        }
        System.out.println(name + " tries to call stepTwo()");
        objectA.stepTwo();
    }

    public synchronized void stepTwo() {

        System.out.println(Thread.currentThread().getName() + " -> stepTwo()");
    }
}
