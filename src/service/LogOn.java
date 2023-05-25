package service;

import vo.User;

import java.util.Map;
import java.util.Scanner;

public class LogOn {
    public final static User user=new User();
    public  boolean UserLogOn() throws Exception {
        Scanner scanner=new Scanner(System.in);
        String userName;
        String passWord;
        System.out.println("请输入用户名：");
        userName=scanner.nextLine();
        System.out.println("请输入密码：");
        passWord=scanner.nextLine();
        user.setUserName(userName);
        user.setPassword(passWord);

        UserService userService=new UserService();
        Map<String,Object> map=userService.checkLogin(user);
        if(map.get("code").equals("0")) {
            System.out.println("登录成功！");
            return true;
        }
        else {
            System.out.println(map.get("msg"));
            return  false;
        }
    }
}
