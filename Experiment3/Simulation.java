package Experiment3;

import java.util.Scanner;

public class Simulation {
    int size = 128;
    int taskAmount = 12;
    int absAddress = 0;
    int k = 0;
    Table[] tables=new Table[7];

    public void initializeTable(int[] page, int[] flag, int[] block, int[] location) {
        for (int i = 0; i < 7; i++) {
            tables[i]=new Table();
            tables[i].block=block[i];
            tables[i].flag=flag[i];
            tables[i].page=page[i];
            tables[i].location =location[i];
        }
    }

    public Table findPage(int page) {
        for (int i = 0; i < 7; i++)
            if (tables[i].page == page)
                return tables[i];
        return null;
    }

    public void address(int page, int unitNumber, String save) {
        Table current = findPage(page);         //页表中寻找当前页是否存在
        if (current.flag == 1) {                //当前页标志位有效
            absAddress = current.block * size + unitNumber;     //计算绝对地址
            if (save.equals("Y"))
                current.modified = 1;       //标记当前页已修改
            System.out.println("转换后的绝对地址为: " + absAddress);
        }
        else {      //当前页标志位无效
            System.out.println("出现页错误: *" + page);
            move(page, unitNumber, save);       //跳转至页面调度方法
        }
    }

    public void move(int page, int unitNumber, String save) {
        if (tables[k].modified == 1)        //当前页已经被修改，将此页调出
            System.out.println("OUT " + tables[k].page);
        System.out.println("IN " + page);
        tables[k].page = page;
        tables[k].flag = 1;
        tables[k].modified = 1;     //将新页面换入
        k = (k + 1) / 7;        //计算下一个要牺牲的页面
        address(page,unitNumber,save);
    }

    public void simulator() {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < taskAmount; i++) {
            System.out.println("请输入页号、单元号以及是否为存储指令(Y/N) (以空格分隔):");
            String instruction = sc.nextLine();
            String[] str = instruction.split(" ");

            int page = Integer.parseInt(str[0]);
            int unitNumber = Integer.parseInt(str[1]);
            String save=str[2];

            address(page, unitNumber, save);
        }
    }

    public  static void  main(String[] args) {
        Simulation simulate=new Simulation();
        int[] page = {0,1,2,3,4,5,6};
        int[] flag = {1,1,1,1,0,0,0};
        int[] block = {5,8,9,1,-1,-1,-1};
        int[] location = {11,12,13,21,22,23,121};

        simulate.initializeTable(page, flag, block, location);
        simulate.simulator();
    }
}
