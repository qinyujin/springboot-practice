package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 18:16:00
 */
public class FibonacciTest {
    public static void main(String[] args) {
        FibonacciTest ft = new FibonacciTest();
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
