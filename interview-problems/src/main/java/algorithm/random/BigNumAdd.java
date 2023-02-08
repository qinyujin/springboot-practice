package algorithm.random;

/**
 * @author :覃玉锦
 * @create :2021-03-22 12:00:00
 * 大数相加
 */
public class BigNumAdd {

    public static void main(String[] args) {
        System.out.println(bigNumAdd("987654321987654321", "987654321987654321")); //1975308643975308642
    }

    public static String bigNumAdd(String num1,String num2){
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        int len = num1.length() > num2.length() ? num1.length() : num2.length();
        int carry = 0;
        StringBuilder res = new StringBuilder();
        //已经反转
        for (int i = 0; i < len; i++) {
            int n1 = i >= num1.length() ? 0 : Integer.valueOf(num1.charAt(i)+"");
            int n2 = i >= num2.length() ? 0 : Integer.valueOf(num2.charAt(i)+"");
            int sum = n1+n2+carry;
            carry = sum/10;
            res.append(sum%10);
        }
        if(carry!=0)res.append(carry);
        return res.reverse().toString();
    }
}
