package structure.proxy.staticproxy;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:56:00
 */
public class ProxyCloth {
    private ClothFactory factory;

    public ProxyCloth(ClothFactory factory) {
        this.factory = factory;
    }

    public void proxy(){
        System.out.println("代理之前");
        factory.producure();
        System.out.println("代理之后");
    }
}
