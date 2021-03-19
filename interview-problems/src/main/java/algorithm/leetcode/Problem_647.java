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

    //中心扩展法
    public int countSubstrings(String s) {
        int ans = 0;
        //由于是通过cent来计算left和right，所以把cent设置为2倍len
        for (int center = 0; center < 2*s.length()-1; center++) {
            int left = center/2;
            int right = left + center%2;
            while (left >= 0 && right <s.length() && s.charAt(left) == s.charAt(right)){
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}
