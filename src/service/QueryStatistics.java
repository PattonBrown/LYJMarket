package service;

import dao.SaleDetailDAO;
import dao.impl.ProductDAOImpl;
import dao.impl.SaleDetailDAOImpl;
import vo.SaleDetail;

import java.util.Scanner;

public class QueryStatistics {// 查询统计
    public boolean  QuerySta() throws Exception {
        SaleDetailDAOImpl sddi=new SaleDetailDAOImpl();
        System.out.println("请输入销售日期：");
        Scanner scanner=new Scanner(System.in);
        String regex="[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
        boolean flag=false;
        while (true) {
            String saleDate = scanner.nextLine();
            if (saleDate.matches(regex)) {
                System.out.println("格式校验通过");
                SaleDetailDAO saleDetailDAO=new SaleDetailDAOImpl();
                SaleDetail saleDetail=new SaleDetail();
                saleDetail=saleDetailDAO.SearchSale(saleDate);
                saleDetail.toString();
                ProductDAOImpl pdi=new ProductDAOImpl();
                String[] dates=saleDate.split("-");
                System.out.println(dates[0]+"年"+dates[1]+"月"+dates[2]+"日销售如下");
                System.out.println("流水号                           商品名称    单 价        数量          金额               时间                    收银员");
                System.out.println("============   =====  ===    ===   ===     ======      ===");
                try {
                    if(sddi.ExistSale(saleDate)){
                        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
                        System.out.println("销售总数: "+sddi.saleCount(saleDate)+"   "+"商品总件: "+pdi.productCount()+"   "+"销售总金额: "+sddi.saleAll(saleDetail));
                        System.out.println(dates[0]+"年"+dates[1]+"月"+dates[2]+"日");
                        System.out.println("请按任意键返回主界面");
                        String i=scanner.nextLine();
                        MainMenu menu=new MainMenu();
                        flag=true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("格式校验不通过，请重新输入：");
            }
    }
    return  flag;
}
}
