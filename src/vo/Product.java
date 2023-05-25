package vo;

public class Product {
    String barCode;
    String productName;
    double price;
    String supply;

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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }
    public  Product(){
        this.barCode=null;
        this.productName=null;
        this.price=0;
        this.supply=null;
    }
    public Product(String barCode, String productName, double price, String supply) {
        this.barCode = barCode;
        this.productName = productName;
        this.price = price;
        this.supply = supply;
    }

    @Override
    public String toString() {
        return "Product{" +
                "barCode='" + barCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", supply='" + supply + '\'' +
                '}';
    }
}
