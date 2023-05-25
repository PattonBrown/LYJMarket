package service;

import dao.impl.SaleDetailDAOImpl;
import vo.Product;
import vo.SaleDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Cashier {
    Scanner scan =new Scanner(System.in);
    public static int lshNum=1000;
    public Cashier() throws Exception {
        while(true) {
            System.out.println("请输入商品条形码（6位数字字符）：");
            String barCode=scan.next();
            while(barCode.length()!=6){
                System.out.println("条形码输入格式不正确，请重新输入");
                barCode=scan.next();
            }
            Product product=new Product();
            product.setBarCode(barCode);

            SaleDetail saleDetail=new SaleDetail();
            SaleDetailDAOImpl saleDetailDAOImpl=new SaleDetailDAOImpl();


            SaleService saleService=new SaleService();
            Map<String,Object> map=saleService.checkExist(product);
            if(map.get("code").equals("0")) {
                System.out.println("查找成功！");
                saleDetail=saleDetailDAOImpl.getById(barCode);
                System.out.print("该商品数量为：");
                System.out.println(saleDetail.getCount());

                SaleDetail saleDetail2=new SaleDetail();
                System.out.print("输入商品数量：");
                lshNum++;
                int count=scan.nextInt();
                saleDetail2.setBarCode(barCode);
                saleDetail2.setPrice(product.getPrice());
                saleDetail2.setProductName(product.getProductName());
                saleDetail2.setCount(count);
                saleDetail2.setOperator(LogOn.user.getUserName());
                SaleDetailDAOImpl saleDetailDAOImpl2=new SaleDetailDAOImpl();
                String lsh=String.valueOf(lshNum);
                saleDetail2.setLsh(lsh);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                saleDetail2.setSaleTime(date);

                saleDetailDAOImpl2.insert(saleDetail2);
                System.out.println("成功增加一笔销售数据");
                break;
            }
            else {
                System.out.println("您输入的商品条形码不存在，请确认后重新输入");

            }


        }
    }
}
