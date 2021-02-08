package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:42:00
 * 把字符串转换成整数
 * https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tqId=11202&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 */
public class Problem_67 {
    public static void main(String[] args) {
        String str = "-124543";
        System.out.println(new Problem_67().StrToInt(str));
    }

    public int StrToInt(String str) {
        if(str.length()==0 || str==null)return 0;
        int ret =0;
        boolean isNegative = str.charAt(0) == '-';
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='+' ||str.charAt(i)=='-'){
                continue;
            }
            if(str.charAt(i) <'0' || str.charAt(i)>'9'){
                return 0;
            }
            ret = ret*10 + (str.charAt(i)-'0');
        }
        return isNegative?-ret:ret;
    }
}
