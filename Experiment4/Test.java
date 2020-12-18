package Experiment4;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Test {
    int maxSave = 10;
    int maxOpen = 5;
    int saveNumber = 0;
    int openNumber = 0;
    AFD[] openTable = {new AFD(), new AFD(), new AFD(), new AFD(), new AFD()};
    private int operator;

    public Test(String masterDirectory, TreeMap<String, MFD> mainDir) {
        fileOperate(masterDirectory, mainDir);
    }

    public void fileOperate(String address, TreeMap<String, MFD> mainDir) {
        System.out.println("**************************************");
        System.out.println("## 1:-----创建文件                                                           *");
        System.out.println("## 2:-----读取文件                                                           *");
        System.out.println("## 3:-----写入文件                                                           *");
        System.out.println("## 4:-----删除文件                                                           *");
        System.out.println("## 5:-----打开文件                                                           *");
        System.out.println("## 6:-----关闭文件                                                           *");
        System.out.println("## 7:-----查看目录                                                           *");
        System.out.println("## 8:-----退出登录                                                           *");
        System.out.println("**************************************");

        System.out.println("请选择您要执行的操作：");
        Scanner input = new Scanner(System.in);
        try{
            operator = input.nextInt();
        }catch(Exception e){
            System.out.println("输入指令异常,请重新输入！" + "\n");
            fileOperate(address, mainDir);
        }

        switch(operator){
            case 1:         //创建文件操作
                System.out.println("操作目录 :" + address);
                System.out.println("创建文件: ");
                System.out.println("输入你要创建的文件名字、保护位和文件长度（以空格分隔）：");
                Scanner input1 = new Scanner(System.in);
                String s = input1.nextLine();

                String[] str = s.split(" ");    //对输入的内容进行处理，分别读入需要的数据

                String fileName = str[0];
                int protectNumber = Integer.parseInt(str[1]);
                double fileLength = Double.parseDouble(str[2]);

                if (mainDir.get(address).find(fileName))        //检验该文件是否已经存在
                    System.out.println("该文件已经存在，无法创建");
                else if (saveNumber >= maxSave)
                    System.out.println("保存文件数已达到本次访问上限");
                else {
                    UFD temp = new UFD(fileName, protectNumber, fileLength);    //在UFD表中新增内容
                    mainDir.get(address).addPair(fileName, temp);
                    saveNumber++;       //已存文件数量 + 1
                }

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);      //等待3秒
                } catch (InterruptedException ignored){}
                fileOperate(address, mainDir);      //返回用户操作选择界面
                break;

            case 2:
                System.out.println("操作目录 :" + address);
                System.out.println("读取文件: ");
                System.out.println("输入你要读取的文件名字：");
                Scanner input2 = new Scanner(System.in);
                fileName = input2.next();

                if (!mainDir.get(address).find(fileName))
                    System.out.println("该文件不存在！");
                else if (mainDir.get(address).get(fileName).getProtectNumber() == 0)
                    System.out.println("该文件不可读！");
                else {
                    System.out.println(mainDir.get(address).get(fileName).toString());
                }

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}
                fileOperate(address, mainDir);

                break;

            case 3:     //写入文件操作
                System.out.println("操作目录 :" + address);
                System.out.println("写入文件: ");
                System.out.println("输入你要写入的文件名字：");
                Scanner input3 = new Scanner(System.in);
                fileName = input3.next();

                if (!mainDir.get(address).find(fileName))       //检测当前写入的文件是否存在
                    System.out.println("文件不存在！");
                else if (mainDir.get(address).get(fileName).getProtectNumber() == 0)
                    System.out.println("写入失败，文件不可写！");      //检查文件是否可写
                else {
                    System.out.println("选择写入方式：1、add(追加)  2、cover(覆盖)");
                    Scanner input4 = new Scanner(System.in);
                    int how = input4.nextInt();
                    System.out.println("输入你要写入的文本内容:");
                    Scanner input5 = new Scanner(System.in);
                    String content = input5.next();

                    if (how == 1)       //选择了追加写模式
                        mainDir.get(address).get(fileName).setFileLength(
                                mainDir.get(address).get(fileName).getFileLength() + content.length()); //在原长度上增加输入字符串长度
                    else {      //选择了覆盖写模式
                        mainDir.get(address).get(fileName).setFileLength(content.length()); //重新计算文件长度
                    }

                }

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}
                fileOperate(address, mainDir);

                break;

            case 4:
                System.out.println("操作目录 :" + address);
                System.out.println("删除文件: ");
                System.out.println("输入你要删除的文件名字：");
                Scanner input6 = new Scanner(System.in);
                fileName = input6.next();

                if (!mainDir.get(address).find(fileName))
                    System.out.println("该文件不存在！");
                else {
                    mainDir.get(address).remove(fileName);
                }

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}
                fileOperate(address, mainDir);

                break;

            case 5:
                System.out.println("操作用户目录 ：" + address);
                System.out.println("输入你要打开的文件名字：");

                Scanner input7 = new Scanner(System.in);
                fileName = input7.next();

                if (!mainDir.get(address).find(fileName))
                    System.out.println("该文件不存在！");
                else if (mainDir.get(address).get(fileName).getProtectNumber() == 0)
                    System.out.println("该文件不可读！");
                else if (openNumber >= maxOpen)
                    System.out.println("打开文件数已达到本次访问上限");
                else {
                    openTable[openNumber++].addItem(fileName, mainDir.get(address).get(fileName).getProtectNumber(), 0);
                    System.out.println("文件已打开，AFD表写入成功！");
                }

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}

                fileOperate(address, mainDir);
                break;

            case 6:
                System.out.println("操作用户目录 ：" + address);
                System.out.println("输入你要关闭的文件名字：");

                Scanner input8 = new Scanner(System.in);
                fileName = input8.next();

                boolean found = false;
                for (int i = 0; i < openTable.length; i++){
                    if (openTable[i].getFileOpen().equals(fileName)) {
                        for (int j = i; j < openTable.length - 1; j++)
                            openTable[j] = openTable[j + 1];
                        openNumber--;
                        found = true;
                    }
                }
                if (!found)
                    System.out.println("文件当前未被打开！");

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}

                fileOperate(address, mainDir);
                break;

            case 7:
                System.out.println("操作用户目录 ：" + address);
                System.out.println("您的当前系统文件如下："+"\n");

                MFD current = mainDir.get(address);
                current.show();

                System.out.println("\n操作完毕，正在返回……"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored){}

                fileOperate(address, mainDir);
                break;

            case 8:
                System.out.println("程序已退出，注销成功!"+"\n");
                System.exit(0);
                break;

            default:
                System.out.println("输入的指令有误，请重新输入!"+"\n");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored){}
                fileOperate(address, mainDir);
        }
    }
}
