package Experiment2;

public class PCB {
    private String name;
    private PCB nextPCB;
    private int demandTime;
    private int runTime;
    private int waitingTime;
    private int turnaroundTime;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PCB getNextPCB() {
        return nextPCB;
    }

    public void setNextPCB(PCB nextPCB) {
        this.nextPCB = nextPCB;
    }

    public int getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(int demandTime) {
        this.demandTime = demandTime;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime){
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime(){
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime){
        this.turnaroundTime = turnaroundTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
