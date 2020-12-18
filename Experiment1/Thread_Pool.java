package Experiment1;

import java.util.LinkedList;

public class Thread_Pool {

    public LinkedList<Product> depot = new LinkedList<>();

    public String show(LinkedList<Product> list){
        String s = "";
        for (Product str : list)
            s += str.getId() + " ";
        return s;
    }

    public synchronized void push(Product product, String name) {
        while(depot.size() == 10){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        depot.addLast(product);
        if (depot.size() != 0)
            System.out.println(name + " has produced product " + product.getId() + ".\t"
                    + "The Thread_Pool has " + depot.size() + " product(s)." + "\t"
                    + "They are : " + show(depot));
        else
            System.out.println(name + " has produced product " + product.getId() + ".\t"
                    + "The Thread_Pool is Empty.");
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void pop(String name){
        while(depot.size() == 0){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();

        if (depot.size() > 1)
            System.out.println(name + " has consumed product " + depot.removeFirst().getId() + ".\t"
                    + "The Thread_Pool has " + depot.size() + " product(s)." + "\t"
                    + "They are : " + show(depot));
        else
            System.out.println(name + " has produced product " + depot.removeFirst().getId() + ".\t"
                    + "The Thread_Pool is Empty.");

        try {
            Thread.sleep(400);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
