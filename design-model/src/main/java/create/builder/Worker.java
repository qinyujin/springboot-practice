package create.builder;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:06:00
 * 具体的构建构建者
 */
public class Worker extends Builder {
    private Product product;

    public Worker() {
        product = new Product();
    }

    @Override
    void buildA() {
        product.setBuildA("地基");
        System.out.println("构建地基");
    }

    @Override
    void buildB() {
        product.setBuildB("钢筋");
        System.out.println("构建钢筋");
    }

    @Override
    void buildC() {
        product.setBuildC("电线");
        System.out.println("构造电线");
    }

    @Override
    void buildD() {
        product.setBuildD("粉刷");
        System.out.println("构建粉刷");
    }

    @Override
    Product getProduct() {
        return product;
    }
}
