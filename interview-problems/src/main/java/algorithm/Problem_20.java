package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 17:47:00
 * 表达数字的字符串
 * https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2?tpId=13&tqId=11206&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Problem_20 {
    public static void main(String[] args) {
        Problem_20 stn = new Problem_20();
        char[] str = "123.45e+6".toCharArray();
        System.out.println(stn.isNumeric(str));
    }

    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
