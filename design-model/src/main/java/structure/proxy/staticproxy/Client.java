package structure.proxy.staticproxy;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:57:00
 * 静态代理缺点是一个接口对应一个代理类。
 */
public class Client {
    public static void main(String[] args) {
        ClothFactory nike = new Nike();
        System.out.println("==========未代理前==========");
        nike.producure();

        ProxyCloth proxy = new ProxyCloth(nike);
        System.out.println("==========代理后==========");
        proxy.proxy();
    }
}
