package create.builder.mcdonalds;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:25:00
 * 四个构建方法，返回构建者，方便链式操作
 */
public abstract class Builder {
    abstract Builder buildA(String product);//汉堡
    abstract Builder buildB(String product);//薯条
    abstract Builder buildC(String product);//可乐
    abstract Builder buildD(String product);//炸鸡

    abstract Product getProduct();
}
