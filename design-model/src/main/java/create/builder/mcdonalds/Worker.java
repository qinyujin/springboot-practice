package create.builder.mcdonalds;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:30:00
 */
public class Worker extends Builder {
    private Product product;

    public Worker() {
        product = new Product();
    }

    @Override
    Builder buildA(String product) {
        this.product.setProductA(product);
        System.out.println(product);
        return this;
    }

    @Override
    Builder buildB(String product) {
        System.out.println(product);
        this.product.setProductB(product);
        return this;
    }

    @Override
    Builder buildC(String product) {
        System.out.println(product);
        this.product.setProductC(product);
        return this;
    }

    @Override
    Builder buildD(String product) {
        System.out.println(product);
        this.product.setProductD(product);
        return this;
    }

    @Override
    Product getProduct() {
        return product;
    }
}
