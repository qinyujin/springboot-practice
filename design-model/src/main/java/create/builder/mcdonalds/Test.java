package create.builder.mcdonalds;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:28:00
 */
public class Test {
    public static void main(String[] args) {
        Worker worker = new Worker();
//        Product product = worker.getProduct();
        Product product = worker.buildA("香蕉").buildB("雪碧").getProduct();
        System.out.println(product.toString());
    }
}
