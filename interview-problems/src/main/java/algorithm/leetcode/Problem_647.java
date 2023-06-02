package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 14:18:00
 * 回文字串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class Problem_647 {
    public static void main(String[] args) {
        Problem_647 p = new Problem_647();
        System.out.println(p.countSubstrings("aaa"));
    }

    /**
     * 为什么有 2 * len - 1 个中心点？
     * aba 有5个中心点，分别是 a、b、c、ab、ba
     * abba 有7个中心点，分别是 a、b、b、a、ab、bb、ba
     */
    //中心扩展法
    public int countSubstrings(String s) {
        int ans = 0;
        //中心点可以分为奇数和偶数两种情况，如aba的b,abba的bb都是中心点，因此通过left和right来通用的处理
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int left = center / 2;
            //处理奇数和偶数的情况
            int right = left + center % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}
