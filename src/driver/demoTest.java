package driver;

import service.UserService;
import vo.User;
import java.util.Map;

public class demoTest {
    public static void main(String[] args) throws Exception {
        UserService userService=new UserService();
        User user=new User();
        user.setUserName("root");
        user.setPassword("123456");
        Map<String,Object> map=userService.checkLogin(user);
        if(map.get("code").equals("0")) {
            System.out.println("登录成功！");
        }
        else {
            System.out.println(map.get("msg"));
        }
    }

}
