package create.factory.method;

/**
 * @author :覃玉锦
 * @create :2021-01-18 11:36:00
 */
public class WuLingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
