package service;

import dao.impl.SaleDetailDAOImpl;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import vo.SaleDetail;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ExportData {
    static Scanner scan=new Scanner(System.in);
    public static void SE() throws Exception {//功能选择
        while(true) {
            MainMenu.ExportStatistic();
            int choice=scan.nextInt();
            switch(choice) {
                case 1:WriteToExcel();break;
                case 2:WriteToTxt();break;
                case 3:MainMenu menu=new MainMenu();break;
            }
            break;
        }
    }
    //导入excel
    public static  void WriteToExcel() throws Exception{
        SaleDetailDAOImpl saleDetailDAOImpl=new SaleDetailDAOImpl();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        //系统时间
        Date now=new Date(System.currentTimeMillis());
        String now1="saleDetail"+simpleDateFormat.format(now);
        File file=new File(now1+".xls");
        if (!file.exists()) {
            file.createNewFile();
        }
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet = workbook.createSheet("销售数据", 0);
        ArrayList<SaleDetail> saleDetaillist= saleDetailDAOImpl.getSale();
        String[] titles= {"流水号","商品条形码","商品名称","商品单价","数量","收银员","销售时间"};
        Label label=null;
        for(int i=0;i<titles.length;i++) {
            label=new Label(i,0,titles[i]);
            sheet.addCell(label);
        }

        for (int i = 0; i < saleDetaillist.size(); i++) {
            Label lsh= new Label(0, i+1, ((SaleDetail) saleDetaillist.get(i)).getLsh());
            sheet.addCell(lsh);
            Label barCode= new Label(1, i+1, ((SaleDetail) saleDetaillist.get(i)).getBarCode());
            sheet.addCell(barCode);
            Label productName= new Label(2, i+1, ((SaleDetail) saleDetaillist.get(i)).getProductName());
            sheet.addCell(productName);
            Label price= new Label(3, i+1, ((SaleDetail) saleDetaillist.get(i)).getPrice()+"");
            sheet.addCell(price);
            Label count= new Label(4, i+1, ((SaleDetail) saleDetaillist.get(i)).getCount()+"");
            sheet.addCell(count);
            Label operator= new Label(5, i+1, ((SaleDetail) saleDetaillist.get(i)).getOperator());
            sheet.addCell(operator);
            Label saleTime= new Label(6, i+1, ((SaleDetail) saleDetaillist.get(i)).getSaleTime());
            sheet.addCell(saleTime);
        }
        workbook.write();
        workbook.close();
        System.out.println("成功导出"+saleDetaillist.size()+"条销售数据到excel文件中");
    }
    //导入txt
    public static void WriteToTxt() throws Exception{
        SaleDetailDAOImpl saleDetailDAOImpl=new SaleDetailDAOImpl();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        //系统时间
        Date now=new Date(System.currentTimeMillis());
        String now1="saleDetail"+simpleDateFormat.format(now);
        Date today=new Date(System.currentTimeMillis());
        File file=new File(now1+".txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file,true);
        ArrayList<SaleDetail> saleDetaillist= saleDetailDAOImpl.getSale();
        fos.write("流水号        商品条形码       商品名称      商品单价          数量       收银员      销售时间".getBytes());
        fos.write("\n".getBytes());
        for(SaleDetail saleDetail:saleDetaillist){

            fos.write(saleDetail.toString().getBytes());
            fos.write("\n".getBytes());
        }
        System.out.println("成功导出"+saleDetaillist.size()+"条商品销售数据到文本文件中");
        fos.close();

    }
}
