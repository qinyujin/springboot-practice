package create.factory.abstract1;

import create.factory.abstract1.product_categories.PhoneProduct;
import create.factory.abstract1.product_categories.RouterProduct;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:44:00
 * 产品工厂，主要可以生产两类产品：手机和路由器。对应创建对象的工厂以具体品牌来创建，例如小米有小米工厂类
 */
public interface ProductFactory {
    PhoneProduct productPhone();

    RouterProduct productRouter();
    //todo 可以在此增加接口，即产品的类型，比如增加ComputerProduct.
}
