package Experiment2;

import java.util.ArrayList;
import java.util.Scanner;

public class Round_Robin {

    final int queueSize = 5;
    private int head;
    private int CPU_Time;
    private ArrayList<PCB> PCB_WaitingQueue;
    private ArrayList<PCB> PCB_List;
    private ArrayList<Integer> demandTimes;
    private PCB signalPCB;

    public Round_Robin() {
        head = 0;
        CPU_Time = 0;
        PCB_List = new ArrayList<>();
        PCB_WaitingQueue = new ArrayList<>();
        demandTimes = new ArrayList<>();
    }

    public void initList() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < queueSize +1; i++) {
            PCB pcb = new PCB();
            System.out.println("Please input the demand time of process Q" + i + " (Integer Type) : ");

            pcb.setName("Q" + i);
            pcb.setDemandTime(scanner.nextInt());
            pcb.setRunTime(0);
            pcb.setState("R");
            PCB_WaitingQueue.add(pcb);
            PCB_List.add(pcb);
        }
        scanner.close();

        for (int i = 0; i < queueSize -1; i++)
            PCB_WaitingQueue.get(i).setNextPCB(PCB_WaitingQueue.get(i+1));

        PCB_WaitingQueue.get(queueSize -1).setNextPCB(PCB_WaitingQueue.get(0));

        for (PCB pcb : PCB_WaitingQueue)
            demandTimes.add(pcb.getDemandTime());
    }

    public void adjustQueue() {
        int length = PCB_WaitingQueue.size();

        for (int j = 0; j < length; j++) {
            length = PCB_WaitingQueue.size();
            boolean isTerminated = PCB_WaitingQueue.get(j).getState().equals("E");

            if ( (length == 1) && isTerminated) {
                PCB_WaitingQueue.get(j).setNextPCB(null);
                PCB_WaitingQueue.remove(j);
                break;
            }

            if (isTerminated) {
                if(j == 0)
                    PCB_WaitingQueue.get(length-1).setNextPCB(PCB_WaitingQueue.get(j).getNextPCB());    //让等待队列的最后一个PCB的下一个指针指向第二个
                else
                    PCB_WaitingQueue.get(j-1).setNextPCB(PCB_WaitingQueue.get(j).getNextPCB());     //让当前PCB的下一个指向当前的下一个（跳过一个）

                PCB_WaitingQueue.remove(j);
                length = PCB_WaitingQueue.size();
            }
        }
    }

    public void printPCBList() {
        for (PCB process : PCB_List) {

            if (process.equals(signalPCB)) {
                System.out.println(process.getName() + "\t\t"
                        + process.getDemandTime() + "\t\t\t"
                        + process.getRunTime() + "\t\t\t"
                        + process.getState() + "\t<--- Current Running");
                if (process.getState().equals("R"))
                    process.setTurnaroundTime(process.getTurnaroundTime() + 1);
            }
            else {
                System.out.println(process.getName() + "\t\t"
                        + process.getDemandTime() + "\t\t\t"
                        + process.getRunTime() + "\t\t\t"
                        + process.getState());
                if (process.getState().equals("R")) {
                    process.setTurnaroundTime(process.getTurnaroundTime() + 1);
                    process.setWaitingTime(process.getWaitingTime() + 1);
                }
            }
        }
        System.out.println();
    }

    public void run() {
        initList();
        signalPCB = PCB_WaitingQueue.get(0);

        System.out.println("\nCPU Time : 0 ");
        System.out.println("Name\tNeedTime\tRunTime\t\tState");
        printPCBList();

        while(true) {
            int demandTime = signalPCB.getDemandTime();
            int runTime = signalPCB.getRunTime();

            if (demandTime == ++runTime) {          //如果当前CPU周期运行完后进程结束，就把状态改为E
                signalPCB.setState("E");
            }
            signalPCB.setRunTime(runTime);          //更新已运行时间

            CPU_Time++;
            adjustQueue();                          //调整就绪队列顺序
            signalPCB = signalPCB.getNextPCB();     //指定下一个要运行的程序

            System.out.println("CPU_Time : " + CPU_Time);
            System.out.println("Name\tNeedTime\tRunTime\t\tState");
            printPCBList();
            if (PCB_WaitingQueue.isEmpty())         //如果就绪队列为空，轮转结束
                break;
        }

        System.out.println("Name\tTurnaroundTime\tWaitingTime");
        for (PCB i : PCB_List){
            System.out.printf("%s\t\t\t%d\t\t\t\t%d\n", i.getName(), i.getTurnaroundTime(), i.getWaitingTime());
        }
    }
}
