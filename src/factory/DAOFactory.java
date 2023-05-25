package factory;

import dao.IUserDAO;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import dao.impl.UserDAOImpl;

import java.sql.Connection;

/**
 *
 * 最终数据层是要交给业务层调用的，而业务层为了和数据层之间不产生耦合， 所以不允许直接使用子
 类为数据层接口实例化，必须通过工厂类来完成，
 * 工厂应该保存在 factory 包下。 对于业务层在通过工厂类取得数据层接口实例的时候一定要传递连接
 对象。
 *
 */
public class DAOFactory {
    public static IUserDAO getIUserDAOInstance(Connection con) {

        return new UserDAOImpl(con);
    }
public static ProductDAO getProductDAOInstance(Connection con) {
    return new ProductDAOImpl();
}
//    public static SaleDetailDAO getSaleDetailDAOInstance(Connection con) {
//
//        return new SaleDetailDAOImpl(con);
//    }
}
