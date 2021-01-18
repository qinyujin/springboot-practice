package structure.proxy.staticproxy;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:55:00
 */
public class Nike implements ClothFactory {
    @Override
    public void producure() {
        System.out.println("生产Nike运动服");
    }
}
