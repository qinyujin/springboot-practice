package create.factory.abstract1;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:46:00
 * 抽象工厂模式是有一个超级工厂（productFactory）来创建其他的工厂（XiaomiFactory），然后通过具体工厂来创建
 * 产品
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("==========小米工厂==========");
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        PhoneProduct phoneProduct = xiaomiFactory.productPhone();
        RouterProduct routerProduct = xiaomiFactory.productRouter();
        phoneProduct.call();
        phoneProduct.sendMsg();
        routerProduct.openWifi();
        routerProduct.shutdown();

        System.out.println("==========华为工厂==========");
        HuaweiFactory huaweiFactory = new HuaweiFactory();
        PhoneProduct phoneProduct1 = huaweiFactory.productPhone();
    }
}
