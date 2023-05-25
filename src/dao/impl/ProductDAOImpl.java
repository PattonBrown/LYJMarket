package dao.impl;

import dao.ProductDAO;
import vo.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    public ProductDAOImpl() {
        this.conn = conn;
    }
    @Override
    public boolean insert(Product product) throws Exception {
        String sql = "INSERT INTO tproduct(barCode,productName,price,supply) " + "VALUES (?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, product.getBarCode());
        this.pstmt.setString(2, product.getProductName());
        this.pstmt.setDouble(3, product.getPrice());
        this.pstmt.setString(4, product.getSupply());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean update(Product product) throws Exception {
        String sql = "UPDATE tproduct SET productName=?,price=?,supply=? WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(4, product.getBarCode());
        this.pstmt.setString(1, product.getProductName());
        this.pstmt.setDouble(2, product.getPrice());
        this.pstmt.setString(3, product.getSupply());
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean delete(String barCode) throws Exception {
        String sql = "DELETE FROM tproduct WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,barCode);
        if (this.pstmt.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Product getById(String barCode) throws Exception {
        String sql = "SELECT * FROM tproduct WHERE barCode=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, barCode);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            Product product = new Product();
            product.setBarCode(rs.getString("barCode"));
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            product.setSupply(rs.getString("supply"));
            return product;
        } else {
            return null;
        }
    }
    public ArrayList<Product> nameSearch(String productName) throws SQLException {
        String sql="SELECT * FROM tproduct WHERE productName=?";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1,productName);
        ResultSet rs=this.pstmt.executeQuery();
        ArrayList<Product> proList=new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setBarCode(rs.getString("barCode"));
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            product.setSupply(rs.getString("supply"));
            proList.add(product);
        }
        return proList;
    }
    public  boolean searchProduct(String barCode) throws SQLException {
        String sql="SELECT * FROM tproduct WHERE barCode=?";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1,barCode);
        ResultSet rs=this.pstmt.executeQuery();
        if(rs.next()){
            return true;
        }else return false;
    }
    public int productCount() throws SQLException {
        String sql="SELECT count(*) FROM tproduct";
        ResultSet rs=this.pstmt.executeQuery(sql);
        return rs.getInt(1);
    }

    @Override
    public List<Product> query(Product product) throws Exception {
        return null;
    }

    public void insertProduct(ArrayList<Product> productList) throws Exception {
        boolean flag=false;
        for (Product product:productList) {
            String barcode = product.getBarCode();
            if (!searchProduct(product.getBarCode())) {
                insert(product);
            }
        }
    }

}
