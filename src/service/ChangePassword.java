package service;

import dao.impl.UserDAOImpl;

import java.util.Scanner;

import static Util.MD5Util.*;

public class ChangePassword {
    LogOn log=new LogOn();
    public boolean passwordChange() throws Exception {
        if(log.UserLogOn()){
            System.out.println("请设置新的密码：");
            Scanner scanner=new Scanner(System.in);
            String newPassword=scanner.nextLine();
            LogOn.user.setPassword(md5(newPassword));
            UserDAOImpl userDAO=new UserDAOImpl();
            userDAO.update(LogOn.user);
            return true;
        }
        else return false;
    }
}
