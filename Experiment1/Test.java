package Experiment1;

public class Test {

    public static void main(String[] args) {
        Thread_Pool pool = new Thread_Pool();
        Producer p1 = new Producer(pool);
        Producer p2 = new Producer(pool);
        Consumer c1 = new Consumer(pool);
        Consumer c2 = new Consumer(pool);
        Thread t1 = new Thread(p1, "Producer_1");
        Thread t2 = new Thread(p2, "Producer_2");
        Thread t3 = new Thread(c1, "Consumer_1");
        Thread t4 = new Thread(c2, "Consumer_2");

        t2.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (true) {
            if (!t1.isAlive() && !t2.isAlive()
                    && c1.pool.show(c1.pool.depot).equals("")
                    && c2.pool.show(c2.pool.depot).equals("")) {
                t3.stop();
                t4.stop();
                break;
            }
        }
    }
}
