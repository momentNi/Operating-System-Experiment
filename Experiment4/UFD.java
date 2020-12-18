package Experiment4;

public class UFD {

    private String fileName;
    private int protectNumber;
    private double fileLength;

    public UFD(String fileName, int protectNumber, double fileLength){
        this.fileName = fileName;
        this.protectNumber = protectNumber;
        this.fileLength = fileLength;
    }

    public String getFileName(){
        return fileName;
    }

    public int getProtectNumber(){
        return protectNumber;
    }

    public double getFileLength(){
        return fileLength;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setProtectNumber(int protectNumber){
        this.protectNumber = protectNumber;
    }

    public void setFileLength(double fileLength){
        this.fileLength = fileLength;
    }

    @Override
    public String toString() {
        return "文件名:" + fileName + "\t保护码:" + protectNumber + "\t文件长度:" + fileLength;
    }
}
