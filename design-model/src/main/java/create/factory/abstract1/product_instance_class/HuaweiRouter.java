package create.factory.abstract1.product_instance_class;

import create.factory.abstract1.product_categories.RouterProduct;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:43:00
 */
public class HuaweiRouter implements RouterProduct {
    @Override
    public void start() {
        System.out.println("华为路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为路由器关机");

    }

    @Override
    public void openWifi() {
        System.out.println("华为路由器开启wifi");

    }

    @Override
    public void setting() {
        System.out.println("华为路由器设置参数");

    }
}
