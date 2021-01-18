package create.builder;

/**
 * @author :覃玉锦
 * @create :2021-01-18 13:38:00
 * builder建造者，假定有四个步骤
 * 地基、钢筋、电线、粉刷
 */
public abstract class Builder {
    abstract void buildA();
    abstract void buildB();
    abstract void buildC();
    abstract void buildD();

    abstract Product getProduct();
}
