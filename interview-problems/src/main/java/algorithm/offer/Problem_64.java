package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:12:00
 * 求1+2.。。n
 * https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1?tpId=13&tqId=11200&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Problem_64 {
    public static void main(String[] args) {
        System.out.println(new Problem_64().Sum_Solution(5));
    }

    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n>0) && ((sum += Sum_Solution(n-1)) >0);
        return sum;
    }
}
