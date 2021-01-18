package structure.adapter;

/**
 * @author :覃玉锦
 * @create :2021-01-18 17:48:00
 * 220v的电压不能直接给手机充电，需要通过充电器（适配器）来把电压适配到5V
 */
public class Voltage220 {
    public int output(){
        int voltage = 220;
        System.out.println("交流电压220V");
        return voltage;
    }
}
