package dao;

import vo.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    //增加一条记录
    public boolean insert(Product product) throws Exception;
    //修改记录
    public boolean update(Product product) throws Exception;
    //按主键（barCode）删除记录
    public boolean delete(String barCode) throws Exception;
    //按主键（barCode）查询记录
    public Product getById(String barCode) throws Exception;
    //查询满足条件的记录：查询条件封装在 product 对象中，若 product 对象的某个成员变量值为 null，则表示查询时忽略该字段查询条件
    public List<Product> query(Product product) throws Exception;
    public int productCount() throws SQLException;
    public  boolean searchProduct(String barCode) throws SQLException;
}
