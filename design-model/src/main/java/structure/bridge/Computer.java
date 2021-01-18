package structure.bridge;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:02:00
 * 四个维度:品牌（惠普，苹果）和类型（台式，笔记本）
 * 通过桥接方式来组合
 * 组合的方式可以是把品牌当成电脑的属性
 */
public abstract class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void info(){
        //现在以及有了品牌了
        brand.info();
    }
}

