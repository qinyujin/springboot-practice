package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 10:53:00
 * 数字序列中的某一位数字
 * 数字以 0123456789101112131415... 的格式序列化到一个字符串中，求这个字符串的第 index 位。
 * <p>
 * 序列是从0-n增序。
 */
public class Problem_44 {
    public static void main(String[] args) {
        Problem_44 s = new Problem_44();
        System.out.println(s.digitAtIndex(8));
        System.out.println(s.digitAtIndex(10));
        System.out.println(s.digitAtIndex(11));
    }

    public int digitAtIndex(int index) {
        if (index < 0) return -1;
        int digits = 1;
        while (true) {
            int numbers = countOfIntegers(digits);
            if (index < numbers * digits) {
                return digitAtIndex(index, digits);
            }
            index -= digits * numbers;
            digits++;
        }
    }

    //返回n位数有多少个数字，比如1有10（0-9） 2有90个（10-99） 3有900个（100-999）
    public int countOfIntegers(int n) {
        if (n == 1) {
            return 10;
        }
        double pow = Math.pow(10.0, (double) n - 1);
        return (int) (9 * pow);
    }

    public int digitAtIndex(int index, int digits) {
        int number = beginNumber(digits) + index / digits;
        int indexFromRight = digits - index % digits;
        for (int i = 1; i < indexFromRight; ++i) {
            number /= 10;
        }
        return number % 10;
    }

    //返回n位数的开始位，比如1返回0 2返回10 3返回100
    public int beginNumber(int digit) {
        if (digit == 1) {
            return 0;
        }
        return (int) Math.pow(10, digit - 1);
    }
}
