package Experiment1;

public class Consumer implements Runnable{

    Thread_Pool pool;

    public Consumer(Thread_Pool pool){
        this.pool = pool;
    }

    @Override
    public void run(){
        while(true){
            pool.pop(Thread.currentThread().getName());
        }
    }
}
