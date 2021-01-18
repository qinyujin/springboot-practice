package create.prototype;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-01-18 15:28:00
 * 原型模式一般是对对象进行复制，获得新对象然后基于新对象进行操作。这里示范一下Clonable接口和方法来实现复制
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Video v = new Video();
        Date date = new Date();
        v.setName("动作片");
        v.setCreateTime(date);

        Video v2 = (Video) v.clone();

        System.out.println(v);
        System.out.println(v2);
        System.out.println("======修改过后======");
        date.setTime(225151314);

        System.out.println(v);
        System.out.println(v2);
    }
}
