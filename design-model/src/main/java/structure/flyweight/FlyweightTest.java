package structure.flyweight;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-01-19 14:51:00
 * 享元模式主要是对对象进行共享。有一种缓存的思想。可以使用map来实现
 */
public class FlyweightTest {
    public static void main(String[] args) {
        //需要经常使用一个对象
        ShopFactory shopFactory = new ShopFactory();
        HualianShop shop1 = (HualianShop) shopFactory.getShop("华联超市");
        shop1.statu();
        shop1.setOpen(true);
        shop1.statu();

        Shop shop2 = shopFactory.getShop("华联超市");
        shop2.statu();
    }
}

interface Shop {
    void statu();
}

@Data
class HualianShop implements Shop {
    private boolean isOpen;

    @Override
    public void statu() {
        if (isOpen) {
            System.out.println("开");
        } else {
            System.out.println("关");
        }
    }
}

//享元模式 使用工厂来管理对象
class ShopFactory {
    private static Map<String, Shop> map = new HashMap<>();

    public Shop getShop(String name) {
        if(map.get(name)==null){
            HualianShop shop = new HualianShop();
            map.put(name,shop);
            return shop;
        }
        else {
            return map.get(name);
        }
    }
}
