package create.factory.abstract1;

import create.factory.abstract1.product_categories.PhoneProduct;
import create.factory.abstract1.product_categories.RouterProduct;
import create.factory.abstract1.product_instance_class.XiaomiPhone;
import create.factory.abstract1.product_instance_class.XiaomiRouter;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:45:00
 * 小米工厂，实现产品工厂接口，可以生产小米手机和小米路由
 */
public class XiaomiFactory implements ProductFactory{
    @Override
    public PhoneProduct productPhone() {
        return new XiaomiPhone();
    }

    @Override
    public RouterProduct productRouter() {
        return new XiaomiRouter();
    }
}
