package create.prototype;

import lombok.Data;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-01-18 15:27:00
 * 实现 Cloneable 类之后再实现 clone 方法。默认是浅克隆。
 */
@Data
public class Video implements Cloneable{
    private String name;
    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅克隆，克隆出来的createTime对象的地址和之前的对象是一样的。
//        return super.clone();

        //深克隆，把引用类型也克隆
        Video obj = (Video) super.clone();
        obj.createTime = (Date) this.createTime.clone();
        return obj;
    }
}
