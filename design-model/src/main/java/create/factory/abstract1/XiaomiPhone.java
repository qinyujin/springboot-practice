package create.factory.abstract1;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:36:00
 */
public class XiaomiPhone implements PhoneProduct {
    @Override
    public void start() {
        System.out.println("小米手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米手机关机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");

    }

    @Override
    public void sendMsg() {
        System.out.println("小米手机发短信");

    }
}
