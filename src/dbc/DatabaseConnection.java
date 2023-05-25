package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 在整个 JDBC 操作过程之中，数据库只有打开后才可以进行一系列的操作，而后数据库的连接又必须关
 闭。为了方便控制，可以直接将数据库的打开和关闭操作封装在一个类之中。
 */
public class DatabaseConnection {
    Connection conn=null;
    static {
        try {
            Properties property = new Properties();
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public DatabaseConnection() {
        try {
            String url="jdbc:mysql://localhost:3306/sale";
            String username="root";
            String password="123456";
            conn= DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //直接返回实例化对象时创建的连接对象
    public Connection getConnection() {
        return this.conn;
    }
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}