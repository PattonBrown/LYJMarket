package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class driverTest {
    public static void main(String[] args) throws  Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url="jdbc:mysql://localhost:3306/testdb";
        String username="root";
        String password="123456";
        Connection conn= DriverManager.getConnection(url,username,password);

        String sql="select * from user";

        Statement statement=conn.createStatement();

        System.out.println(statement.execute(sql));

        statement.close();
        conn.close();
    }
}
