package dao.impl;

import dao.SaleDetailDAO;
import vo.Product;
import vo.SaleDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailDAOImpl implements SaleDetailDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    public SaleDetailDAOImpl() {

        this.conn = conn;
    }
    @Override
    public boolean insert(SaleDetail saleDetail) throws Exception {
        String sql = "INSERT INTO tsaleDetail(lsh,barCode,productName,price,count,operator,saleTime) " + "VALUES (?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, saleDetail.getLsh());
        this.pstmt.setString(2, saleDetail.getBarCode());
        this.pstmt.setString(3, saleDetail.getProductName());
        this.pstmt.setDouble(4, saleDetail.getPrice());
        this.pstmt.setInt(5, saleDetail.getCount());
        this.pstmt.setString(6, saleDetail.getOperator());
        this.pstmt.setString(7, saleDetail.getSaleTime());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean update(SaleDetail saleDetail) throws Exception {
        String sql = "UPDATE tsaleDetail SET saleDetail lsh=?,productName=?,price=?,count=?,operator=?,saleTime=? WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, saleDetail.getLsh());
        this.pstmt.setString(7, saleDetail.getBarCode());
        this.pstmt.setString(2, saleDetail.getProductName());
        this.pstmt.setDouble(3, saleDetail.getPrice());
        this.pstmt.setInt(4, saleDetail.getCount());
        this.pstmt.setString(5, saleDetail.getOperator());
        this.pstmt.setString(6, saleDetail.getSaleTime());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean delete(String barCode) throws Exception {
        String sql = "DELETE FROM tsaleDetail WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,barCode);
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public SaleDetail getById(String barCode) throws Exception {
        String sql = "SELECT * FROM tsaleDetail WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, barCode);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setLsh(rs.getString("lsh"));
            saleDetail.setBarCode(rs.getString("barCode"));
            saleDetail.setProductName(rs.getString("productName"));
            saleDetail.setPrice(rs.getDouble("price"));
            saleDetail.setCount(rs.getInt("count"));
            saleDetail.setOperator(rs.getString("operator"));
            saleDetail.setSaleTime(rs.getString("saleTime"));
            return saleDetail;
        } else {
            return null;
        }
    }
    public SaleDetail SearchSale(String saleTime) throws Exception {
        String sql = "SELECT * FROM tsaleDetail WHERE saleTime=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, saleTime);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setLsh(rs.getString("lsh"));
            saleDetail.setBarCode(rs.getString("barCode"));
            saleDetail.setProductName(rs.getString("productName"));
            saleDetail.setPrice(rs.getDouble("price"));
            saleDetail.setCount(rs.getInt("count"));
            saleDetail.setOperator(rs.getString("operator"));
            saleDetail.setSaleTime(rs.getString("saleTime"));
            return saleDetail;
        } else {
            return null;
        }
    }
    public ArrayList<SaleDetail> getSale() throws SQLException {
        String sql="SELECT * FROM tsaledetail ";
        this.pstmt=this.conn.prepareStatement(sql);
        ResultSet rs=this.pstmt.executeQuery();
        ArrayList<SaleDetail> proList=new ArrayList<>();
        while (rs.next()) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setLsh(rs.getString("lsh"));
            saleDetail.setBarCode(rs.getString("barCode"));
            saleDetail.setProductName(rs.getString("productName"));
            saleDetail.setPrice(rs.getDouble("price"));
            saleDetail.setCount(rs.getInt("count"));
            saleDetail.setOperator(rs.getString("operator"));
            saleDetail.setSaleTime(rs.getString("saleTime"));
            proList.add(saleDetail);
        }
        return proList;
    }
    public boolean ExistSale(String saleTime) throws Exception {
        String sql = "SELECT* FROM tsaleDetail WHERE saleTime=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,saleTime);
        if (this.pstmt.execute()) {
            return true;
        } else {
            return false;
        }
    }
    public  int saleCount(String saleTime) throws SQLException {
        String sql="SELECT count(saleTime) FROM tsaleDetail WHERE saleTime =?";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1,saleTime);
        ResultSet rs=this.pstmt.executeQuery(sql);
        return  rs.getInt(1);
    }
    public  double saleAll(SaleDetail saleDetail) throws SQLException {
        double all=0;
        String sql="SELECT * FROM tsaledetail";
        this.pstmt=this.conn.prepareStatement(sql);
        ResultSet rs=this.pstmt.executeQuery(sql);
        while(rs.next()){
            all+=rs.getDouble(4);
        }
        return  all;
    }
    @Override
    public List<SaleDetail> query(SaleDetail saleDetail) throws Exception {
        return null;
    }
}
