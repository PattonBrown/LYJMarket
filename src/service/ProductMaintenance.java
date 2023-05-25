package service;

import dao.impl.ProductDAOImpl;
import jxl.Sheet;
import jxl.Workbook;
import vo.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductMaintenance {
    static Scanner scanner=new Scanner(System.in);
    public static void SE() throws Exception {
        while(true) {
            MainMenu.ProProtect();
            int choice=scanner.nextInt();
            switch(choice) {
                case 1:WriteFromExcel();break;
                case 2:WriteFromTxt();break;
                case 3:WriteFromKey();break;
                case 4:
                    fuzzyQuery();break;
                case 5:break;
            }
            break;
        }
    }
    //从excel导入数据库
    public static  void WriteFromExcel() throws Exception{
        ArrayList<Product> proList = new ArrayList<Product>();
        ProductDAOImpl productDAOImpl=new ProductDAOImpl();
        Workbook workbook = Workbook.getWorkbook(new File("product.xls"));
        Sheet sheet=workbook.getSheet(0);
        int rows=sheet.getRows();
        int count=0;
        for(int i=1;i<rows;i++){
            String barCode=sheet.getCell(0, i).getContents();
            String productName=sheet.getCell(1, i).getContents();
            Float price=Float.parseFloat(sheet.getCell(2, i).getContents());
            String supply=sheet.getCell(3, i).getContents();
            Product product= new Product(barCode, productName,price,supply);
            proList.add(product);
            if(!productDAOImpl.searchProduct(product.getBarCode())) {
                    productDAOImpl.insertProduct(proList);
                    count++;
            }
        }
        System.out.println("成功从excel文件导入"+count+"条商品数据");
    }

    //从txt导入数据库
    public static void WriteFromTxt() throws Exception {
        File file = new File("product.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
        BufferedReader bufferedReader = new BufferedReader(read);
        String line = null;
        int count=0;
        ProductDAOImpl productDAOImpl=new ProductDAOImpl();
        while ((line = bufferedReader.readLine()) != null) {
            String[] s=line.split(",|，");
            Product product=new Product(s[0],s[1],Float.parseFloat(s[2]),s[3]);
            if(!productDAOImpl.searchProduct(product.getBarCode())) {
                productDAOImpl.insert(product);
                count++;
            }
        }
        System.out.println("成功从文本文件导入"+count+"条商品数据");
    }

    //从键盘导入数据库
    private static void WriteFromKey() throws Exception {
        while(true) {
            System.out.println("请输入商品信息,格式为“商品条形码，商品名称，单价，供应商”");
            String str=scanner.next();
            String[] s=str.split(",|，");
            Product product=new Product(s[0],s[1],Float.parseFloat(s[2]),s[3]);
            ProductDAOImpl productDAOImpl=new ProductDAOImpl();
            if(s[0].length()!=6) {
                System.out.println("你输入的数据格式不正确，请重新输入");
                continue;
            }
            if(!productDAOImpl.searchProduct(product.getBarCode())) {
                productDAOImpl.insert(product);
                System.out.println("增加成功");
                break;
            }
            else {
                System.out.println("条形码不能重复，请重新输入");
            }
        }

    }
    //模糊查找
    public static void fuzzyQuery() throws Exception{
        System.out.println("请输入查询的商品名称：");
        String productName = scanner.next();
        ProductDAOImpl productDAOImpl=new ProductDAOImpl();
        ArrayList<Product> proList=new ArrayList<>();
        proList=productDAOImpl.nameSearch(productName);
        System.out.println("满足条件的记录共"+proList.size()+"条，信息如下：");
        System.out.println("序号\t条形码\t商品名称\t单价\t");
        for(Product product:proList){
            product.toString();
        }
    }
}
