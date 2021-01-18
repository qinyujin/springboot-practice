package structure.bridge;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:24:00
 */
public class Laptop extends Computer{
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("笔记本");
    }
}
