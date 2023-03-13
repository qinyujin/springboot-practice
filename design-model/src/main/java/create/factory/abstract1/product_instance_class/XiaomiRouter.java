package create.factory.abstract1.product_instance_class;

import create.factory.abstract1.product_categories.RouterProduct;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:37:00
 * 小米路由器
 */
public class XiaomiRouter implements RouterProduct {
    @Override
    public void start() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由器关机");

    }

    @Override
    public void openWifi() {
        System.out.println("小米路由器开启wifi");

    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置参数");
    }
}
