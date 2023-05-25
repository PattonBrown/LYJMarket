package vo;

public class SaleDetail {
    String lsh;
    String barCode;
    String productName;
    double price;
    int count;
    String operator;
    String saleTime;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }
    public  SaleDetail(){

    }
    public SaleDetail(String lsh, String barCode, String productName, double price, int count, String operator, String saleTime) {
        this.lsh = lsh;
        this.barCode = barCode;
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.operator = operator;
        this.saleTime = saleTime;
    }

    @Override
    public String toString() {
        return "SaleDetail{" +
                "lsh='" + lsh + '\'' +
                ", barCode='" + barCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", operator='" + operator + '\'' +
                ", saleTime='" + saleTime + '\'' +
                '}';
    }
}
