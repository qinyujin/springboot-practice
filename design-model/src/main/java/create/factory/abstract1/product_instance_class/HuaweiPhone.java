package create.factory.abstract1.product_instance_class;

import create.factory.abstract1.product_categories.PhoneProduct;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:42:00
 */
public class HuaweiPhone implements PhoneProduct {
    @Override
    public void start() {
        System.out.println("华为手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");

    }

    @Override
    public void call() {
        System.out.println("华为手机打电话");

    }

    @Override
    public void sendMsg() {
        System.out.println("华为手机发短信");

    }
}
