package create.builder;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:09:00
 * 指挥者负责构建
 */
public class Director {
    public Product build(Builder builder){
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();

        return builder.getProduct();
    }
}
