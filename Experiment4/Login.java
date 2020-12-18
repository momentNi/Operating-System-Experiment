package Experiment4;

import java.util.Scanner;
import java.util.TreeMap;

public class Login {

    private static TreeMap<String, MFD> mainDir = new TreeMap<>();

    public Login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String str = sc.next();

        if (str.equals("user1") || str.equals("user2") || str.equals("user3") || str.equals("user4")){
            System.out.println("请输入密码：（123）");
            sc = new Scanner(System.in);
            String password = sc.next();
            if (password.equals("123")){
                System.out.println(str+" 您好！");
                Test test = new Test(str, mainDir);
            }
            else{
                System.out.println("密码错误，请重新输入！");
                new Login();
            }
        }
        else{
            System.out.println("用户名不存在！请重新输入！");
            new Login();
        }
    }

    public static void createDirectory(){
        UFD cat = new UFD("cat", 1, 15.0);
        UFD bo = new UFD("bo", 0, 16.0);
        UFD a = new UFD("a", 1, 17.0);
        UFD test = new UFD("test", 1, 18.0);
        UFD data = new UFD("data", 0, 19.0);
        UFD x = new UFD("x", 1, 20.0);

        MFD user1 = new MFD();
        user1.addPair("cat", cat);
        user1.addPair("bo",bo);
        user1.addPair("a", a);
        user1.addPair("test", test);

        MFD user2 = new MFD();
        user2.addPair("data", data);
        user2.addPair("a", a);
        user2.addPair("x", x);

        MFD user3 = new MFD();
        user3.addPair("a", a);
        user3.addPair("test", test);

        MFD user4 = new MFD();
        user4.addPair("x", x);
        user4.addPair("data", data);
        user4.addPair("a", a);

        mainDir.put("user1", user1);
        mainDir.put("user2", user2);
        mainDir.put("user3", user3);
        mainDir.put("user4", user4);

    }

    public static void main(String[] args) {
        createDirectory();
        new
                Login();
    }
}
