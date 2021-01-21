package structure.adapter;

/**
 * @author :覃玉锦
 * @create :2021-01-19 11:07:00
 * 对象适配器，通过用类变量的形式取代继承
 */
public class Adapter2 implements Voltage5{
    private Voltage220 voltage220;

    public Adapter2(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    @Override
    public void charging() {
        int output = voltage220.output();
        int i = output/44;
        System.out.println("对象适配器把"+output+"适配为"+i);
    }

    public static void main(String[] args) {
        Voltage220 voltage220 = new Voltage220();
        Adapter2 adapter2 = new Adapter2(voltage220);
        adapter2.charging();
    }
}
