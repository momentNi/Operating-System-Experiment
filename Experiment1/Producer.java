package Experiment1;

public class Producer implements Runnable{

    public static int count = 0;
    Thread_Pool pool;

    public Producer(Thread_Pool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while(true){
            synchronized (Producer.class){
                count++;
                Product product = new Product(count);
                pool.push(product, Thread.currentThread().getName());
                if (count >= 10)    //如果已经生产出十个产品了，就终止运行
                    break;
            }
        }
    }
}
