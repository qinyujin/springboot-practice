package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:19:00
 * 不用加减乘除来做加法
 * https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=11201&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class AddWithNotOperate {
    public static void main(String[] args) {
        System.out.println(new AddWithNotOperate().Add(4, 6));
    }

    /**
     * a^b为不进位的加法，(a&b)<<1为进位
     * 01 ^ 10 -> 11
     *
     * 0100       0010    1010
     * 0110    -> 1000 -> 0000
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        return num2==0?num1: Add(num1^num2,(num1&num2)<<1);
    }
}
