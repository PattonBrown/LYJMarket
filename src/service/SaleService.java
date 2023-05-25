package service;

import dao.ProductDAO;
import dbc.DatabaseConnection;
import factory.DAOFactory;
import vo.Product;

import java.util.HashMap;
import java.util.Map;

public class SaleService {
    private DatabaseConnection dbc; // 数据库连接类
    private ProductDAO productDAO; // 由工厂统一提供的 dao 实现类对象
    public SaleService(){
        this.dbc = new DatabaseConnection(); // 连接数据库
        this.productDAO = DAOFactory.getProductDAOInstance(this.dbc.getConnection());
    } // 从工厂类获取 dao 实现类对象
    public Map<String, Object> checkExist(Product product) throws Exception {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        try {
            Product foundProduct = this.productDAO.getById(product.getBarCode());
            if (foundProduct == null) {
                mapResult.put("code", 1);
                mapResult.put("msg", "商品不存在！");
            } else {
                if (!foundProduct.getBarCode().equals(product.getBarCode())) {
                    mapResult.put("code", 1);
                    mapResult.put("msg", "密码不正确！");
                } else {
                    mapResult.put("code", 0);
                    mapResult.put("msg", "登录成功！");
                }
            }
        } catch (Exception e) {
            mapResult.put("code", 2);
            mapResult.put("msg", e.getMessage());
        } finally { // 无论是否有异常，都需要关闭数据库连接
            this.dbc.close();
        }
        return mapResult;
    }
}
