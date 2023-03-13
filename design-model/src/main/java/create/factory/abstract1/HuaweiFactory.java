package create.factory.abstract1;

import create.factory.abstract1.product_categories.PhoneProduct;
import create.factory.abstract1.product_categories.RouterProduct;
import create.factory.abstract1.product_instance_class.HuaweiPhone;
import create.factory.abstract1.product_instance_class.HuaweiRouter;

/**
 * @author :覃玉锦
 * @create :2021-01-18 12:46:00
 */
public class HuaweiFactory implements ProductFactory {
    @Override
    public PhoneProduct productPhone() {
        return new HuaweiPhone();
    }

    @Override
    public RouterProduct productRouter() {
        return new HuaweiRouter();
    }
}
