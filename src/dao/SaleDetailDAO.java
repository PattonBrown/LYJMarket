package dao;

import vo.SaleDetail;

import java.sql.SQLException;
import java.util.List;

public interface SaleDetailDAO {
    //增加一条记录
    public boolean insert(SaleDetail saleDetail) throws Exception;
    //修改记录
    public boolean update(SaleDetail saleDetail) throws Exception;
    //按主键（barCode）删除记录
    public boolean delete(String barCode) throws Exception;
    //按主键（barCode）查询记录
    public SaleDetail getById(String barCode) throws Exception;
    //查询满足条件的记录：查询条件封装在 SaleDetail 对象中，若 SaleDetail 对象的某个成员变量值为 null，则表示查询时忽略该字段查询条件
    public List<SaleDetail> query(SaleDetail saleDetail) throws Exception;
    public SaleDetail SearchSale(String saleTime) throws Exception;
    //根据日期查询销售记录
    public boolean ExistSale(String saleTime) throws Exception;
    //判断所查日期的销售详情是否存在
    public  int saleCount(String saleTime) throws SQLException;
    //获得销售数量
}
