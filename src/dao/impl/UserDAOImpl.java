package dao.impl;

import dao.IUserDAO;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class UserDAOImpl implements IUserDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(User user) throws Exception {
        String sql = "INSERT INTO tuser(userName,chrName,password,role) " + "VALUES (?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getUserName());
        this.pstmt.setString(2, user.getChrName());
        this.pstmt.setString(3, user.getPassword());
        this.pstmt.setString(4, user.getRole());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean update(User user) throws Exception {
        String sql = "UPDATE tuser SET chrName=? ,password=?,role=? WHERE userName=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getChrName());
        this.pstmt.setString(2, user.getPassword());
        this.pstmt.setString(3, user.getRole());
        this.pstmt.setString(4, user.getUserName());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean delete(String userName) throws Exception {
        String sql = "DELETE FROM tuser WHERE userName=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, userName);
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public User getById(String userName) throws Exception {
        String sql = "SELECT userName,chrName,password,role FROM tuser WHERE userName=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, userName);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUserName(rs.getString("userName"));
            user.setChrName(rs.getString("chrName"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        } else {
            return null;
        }
    }
    @Override
    public List<User> query(User user) throws Exception {
        return null;
    }
    public UserDAOImpl(){
        super();
    }
}
