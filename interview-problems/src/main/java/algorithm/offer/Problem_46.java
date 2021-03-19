package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 11:31:00
 * 把数字翻译成字符串
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 */
public class Problem_46 {
    public static void main(String[] args) {
        //12258 -> 5
        System.out.println(new Problem_46().translateNum(26));
    }

    //12258 ->第一位1有一种，第二位2有2种：2和12 第三位2有三种1 2 2/12 2/1 22 第四位有 4种1 2 2 5/12 2 5/1 22 5/1 2 25
    /*public int translateNum(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            //一位数的情况，如果当前位是0，那么就不纳入计算。
            if (one != 0)
                dp[i] += dp[i - 1];
            if (s.charAt(i - 2) == '0')
                continue;
            int two = Integer.valueOf(s.substring(i - 2, i));
            //两位数的情况
            if (two <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }*/

    /**
     * 递归公式：
     * f[i] = f[i-1] （前一位也就是i-2为0）
     * f[i] = f[i-1] + f[i-2] （前一位不为0，并且两位数小等于26，因为1-26最大是26）
     * @param num
     * @return
     */
    public int translateNum(int num) {
        if(num<=0)return -1;
        String s = String.valueOf(num);
        int[] dp = new int[s.length()+1];
        dp[0] = dp[1] = 1 ;
        for (int i = 2; i <= s.length(); i++) {
            int one = Integer.valueOf(s.substring(i-1,i));
            //个位数不是0，说明应该加上
            if(one!=0){
                dp[i] += dp[i-1];
            }
            //十位数不是0且在26的范围内，说明应该加上
            if(s.charAt(i-2)=='0'){
                continue;
            }
            int two = Integer.valueOf(s.substring(i-2,i));
            if(two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];
    }
}
