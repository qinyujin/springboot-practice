package structure.adapter;

/**
 * @author :覃玉锦
 * @create :2021-01-18 17:50:00
 * 通过适配器把220V转换成5V
 * 1、类   通过继承和实现
 * 2、对象 通过引入作为属性和实现
 */
public class Adapter extends Voltage220 implements Voltage5{

    @Override
    public void charging() {
        int oldVoltage = super.output();
        System.out.println("开始适配");
        int newVoltage = oldVoltage / 44;
        System.out.println("适配后的充电电压："+newVoltage);
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.charging();
    }
}
