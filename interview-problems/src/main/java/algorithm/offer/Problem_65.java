package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:19:00
 * 不用加减乘除来做加法
 * https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=11201&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Problem_65 {
    public static void main(String[] args) {
        System.out.println(new Problem_65().Add(4, 6));
    }

    /**
     * 用5+17=22举例
     * 5->101 17->10001
     * 5+17：
     * 10001+101 不进位 -> 10100
     * 加上进位 10100 -> 10110=22
     * 因此可以使用二进制来计算加，方法为先想加，拿到进位，然后加上进位即可。
     * 如何计算不进位数：0,0->0 1,1->0 1,0->1 0,1->1 所以使用异或^
     * 如何得到进位：0,0->no 0,1->no 1,0->no 1,1->yes 所以使用与和左移，只有1,1组合左移才会进位。这里举例:
     * 10001&101 << 1 = 10即进位结果为10
     * @param num1 结果
     * @param num2 进位
     * @return
     */
    public int Add(int num1,int num2) {
        //如果num2为0即没有进位了，那么返回num1.否则计算：num1+num2不进位，同时num2计算出进位，下一次递归就num1+num2
        //就相当于把进位加上了
        return num2==0?num1: Add(num1^num2,(num1&num2)<<1);
    }
}
