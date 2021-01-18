package create.factory.abstract1;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:44:00
 * 产品工厂，主要可以生产两类产品：手机和路由器
 */
public interface ProductFactory {
    PhoneProduct productPhone();
    RouterProduct productRouter();
}
