package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-21 18:16:00
 * 斐波那契数列
 * https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
 * n\leq 39n≤39
 */
public class Problem_10_1 {
    public static void main(String[] args) {
        Problem_10_1 ft = new Problem_10_1();
        System.out.println(ft.Fibonacci(4));
    }

    public int Fibonacci(int n) {
        int[] fib = fib();
        return fib[n];
    }

    private int[] fib(){
        int[] fib = new int[40];
        fib[1] = fib[2] = 1;
        for (int i = 3; i < 40; i++) {
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib;
    }
}
