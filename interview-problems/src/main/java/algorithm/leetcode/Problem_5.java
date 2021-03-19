package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-03 12:55:00
 * 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class Problem_5 {
    public static void main(String[] args) {
        String str = "ac";
        Problem_5 p = new Problem_5();
        String s = p.longestPalindrome(str);
        System.out.println(s);
    }

    /**
     * 思路：中心扩散法：给定一个中心位置，开始扩散寻找回文子串。在这个过程中保留最长串即可
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        //回文串至少两个
        if(s.length() <2)return s;
        int maxLen = 0;
        String maxStr = s.substring(0,1);
        for (int i = 0; i < s.length(); i++) {
            //奇数回文串作用传等值即可。
            String oddStr = centSpread(s,i,i);
            String evenStr = centSpread(s,i,i+1);
            String mStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if(mStr.length() > maxLen){
                maxLen = mStr.length();
                maxStr = mStr;
            }
        }
        return maxStr;
    }

    /**
     * 拿到left和right，通过扩散方法寻找回文串。
     * 当left=right时，说明是奇数回文串。不等说明是偶数回文串
     * @param str
     * @param left
     * @param right
     * @return
     */
    public String centSpread(String str,int left,int right){
        int i = left,j=right;
        while (i>=0 && j<str.length()){
            if(str.charAt(i) != str.charAt(j))break;
            else {
                //扩散移动
                i--;
                j++;
            }
        }
        //拿到了回文串的下标，这里的下标都是过界的，如abba i=-1，j=4 .需要substring(0,4)。
        return str.substring(i+1,j);
    }
}
