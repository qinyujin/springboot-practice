package create.builder.mcdonalds;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:26:00
 * 产品，四类产品
 */
public class Product {
    private String productA="汉堡";//汉堡
    private String productB="薯条";//薯条
    private String productC="可乐";//可乐
    private String productD="炸鸡";//炸鸡

    @Override
    public String toString() {
        return "Product{" +
                "productA='" + productA + '\'' +
                ", productB='" + productB + '\'' +
                ", productC='" + productC + '\'' +
                ", productD='" + productD + '\'' +
                '}';
    }

    public String getProductA() {
        return productA;
    }

    public void setProductA(String productA) {
        this.productA = productA;
    }

    public String getProductB() {
        return productB;
    }

    public void setProductB(String productB) {
        this.productB = productB;
    }

    public String getProductC() {
        return productC;
    }

    public void setProductC(String productC) {
        this.productC = productC;
    }

    public String getProductD() {
        return productD;
    }

    public void setProductD(String productD) {
        this.productD = productD;
    }
}
