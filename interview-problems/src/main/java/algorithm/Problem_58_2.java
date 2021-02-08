package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 20:10:00
 * 左旋转字符串
 * https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec?tpId=13&tqId=11196&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class Problem_58_2 {
    public static void main(String[] args) {
        System.out.println(new Problem_58_2().LeftRotateString("abcXYZdef", 3));
    }

    /**
     * 示例数据："abcXYZdef",3
     * 先把abc反转，再把XYZdef反转，然后总体反转
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(str.length()==0 || str==null){
            return "";
        }
        char[] chars = str.toCharArray();
        reverse(chars,0,n-1);
        reverse(chars,n,str.length()-1);
        //最后整体反转
        reverse(chars,0,str.length()-1);
        return new String(chars);
    }

    public void reverse(char[] chars,int i,int j){
        while (i<j){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
