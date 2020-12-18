package Experiment4;

public class AFD {

    private String fileOpen;
    private int openNumber;
    private int IO_Pointer;

    public AFD(){
        fileOpen = "";
        openNumber = 0;
        IO_Pointer = 0;
    }

    public String getFileOpen() {
        return fileOpen;
    }

    public void setFileOpen(String fileOpen) {
        this.fileOpen = fileOpen;
    }

    public int getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(int openNumber) {
        this.openNumber = openNumber;
    }

    public int getIO_Pointer() {
        return IO_Pointer;
    }

    public void setIO_Pointer(int IO_Pointer) {
        this.IO_Pointer = IO_Pointer;
    }

    public void addItem(String fileOpen, int openNumber, int IO_Pointer){
        setFileOpen(fileOpen);
        setOpenNumber(openNumber);
        setIO_Pointer(IO_Pointer);
    }
}
