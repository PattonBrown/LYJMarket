package driver;

import service.*;
import vo.User;

import java.util.Map;
import java.util.Scanner;

public class logTest {
    public final static User user=new User();
    public static void main(String[] args) throws Exception {
        Scanner scan =new Scanner(System.in);
        System.out.println("欢迎使用超市收银系统，请登陆");
        int i;
        int choice;
        for(i=0;i<3;i++){
            System.out.println("用户名：");
            String username=scan.nextLine();
            System.out.println("密码：");
            String password=scan.nextLine();
            user.setUserName(username);
            user.setPassword(password);
            UserService userService=new UserService();
            Map<String,Object> map=userService.checkLogin(user);
            if(map.get("code").equals("0")) {
                break;
            }
            else{
                System.out.println(map.get("msg"));
            }
            while(true) {
                System.out.println(map.get("msg"));
                MainMenu menu=new MainMenu();
                System.out.println("请选择（1-6）：");
                choice=scan.nextInt();
                switch(choice) {
                    case 1:
                        Cashier cashier=new Cashier();
                        MainMenu menu2=new MainMenu() ;
                        break;
                    case 2:
                        QueryStatistics queryStatistics=new QueryStatistics();
                        queryStatistics.QuerySta();
                        break;
                    case 3:
                        if(user.getRole().equals("管理员")){
                            ProductMaintenance productMaintenance=new ProductMaintenance();
                            ProductMaintenance.SE();
                        }else{
                            System.out.println("当前用户没有执行该项功能的权限");
                        }
                        MainMenu menu3=new MainMenu();
                        break;
                    case 4:
                        ChangePassword changePassword=new ChangePassword();
                        changePassword.passwordChange();
                        MainMenu menu4=new MainMenu();
                        break;
                    case 5:
                        ExportData exportData=new ExportData();
                        exportData.SE();
                        MainMenu menu5=new MainMenu();
                        break;
                    case 6:
                        menu.existS();
                        break;
                    default:
                        System.out.println("输入无效，只能输入1-6");
                        MainMenu menu6=new MainMenu();
                        break;
                }
            }
        }
        if(i>=3){
            System.out.println("最多只能尝试3次!");
        }
    }
}
