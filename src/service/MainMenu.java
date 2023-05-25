package service;

import java.util.Scanner;

public class MainMenu {
    static Scanner scan =new Scanner(System.in);
    //登录界面
    public static void logOnMenu() throws Exception {
        System.out.println("欢迎使用YJ超市收银系统");
        LogOn logOn=new LogOn();
        int flag=0;
        for(int i=3;i>=1;i--){
            if(logOn.UserLogOn()){
                flag=1;
                break;
            }
            System.out.println("还剩"+i+"次机会");
        }
        if(flag==0){
            System.out.println("3次机会已用完");
        }
    }
    //主菜单
    public MainMenu() {
        System.out.println("===超市收银系统===");
        System.out.println("1、收银");
        System.out.println("2、查询统计");
        System.out.println("3、商品维护");
        System.out.println("4、修改密码");
        System.out.println("5、数据导出");
        System.out.println("6、退出");
        System.out.print("当前收银员:");
        System.out.println(LogOn.user.getChrName());

    }
    //商品维护菜单
    public static void ProProtect() {
        System.out.println("===超市商品管理维护===");
        System.out.println("1、从excel中导入数据");
        System.out.println("2、从文本文件导入数据");
        System.out.println("3、键盘输入");
        System.out.println("4、商品查询");
        System.out.println("5、返回主菜单");
        System.out.print("请选择（1-5）：");
    }
    //数据导出菜单
    public static void ExportStatistic() {
        System.out.println("===超市销售信息导出===");
        System.out.println("1、导出到excel文件");
        System.out.println("2、导出到文本文件");
        System.out.println("3、返回主菜单");
        System.out.print("请选择（1-3）：");
    }
    //退出菜单
    public static void existS() {
        System.out.println("您确认退出系统吗（y/n）");
        String choice=scan.next();
        if(choice.equals("y")||choice.equals("Y")) {
            System.out.println("欢迎下次继续使用!");
            System.exit(0);
        }else
        if(choice.equals("n")||choice.equals("N")) {
            MainMenu menu=new MainMenu();
        }
    }
}
