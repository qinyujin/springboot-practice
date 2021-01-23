package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 14:13:00
 * 数值的整数次方
 */
public class NumberCiFang {
    public static void main(String[] args) {
        double base =2;
        int exponent = 3;
        NumberCiFang ncf = new NumberCiFang();
        System.out.println(ncf.Power(base, exponent));
    }

    public double Power(double base, int exponent) {
        //0次方
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }
        boolean isNegtive=false;
        //次数是负的，结果按照1/x来处理
        if(exponent<0){
            exponent = -exponent;
            isNegtive = true;
        }
        //递归，exponent次方
        double pow = Power(base*base,exponent/2);
        if(exponent%2!=0){
            pow = pow*base;
        }
        return isNegtive ? (1/ pow):pow;
    }
}
