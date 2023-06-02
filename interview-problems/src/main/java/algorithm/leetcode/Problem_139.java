package algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-12 14:25:00
 * 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 */
public class Problem_139 {
    public static void main(String[] args) {
        Problem_139 p = new Problem_139();
        String s = "catsandog";
        List<String> wordDict = new ArrayList<String>() {{
            add("cats");
            add("dog");
            add("sand");
            add("and");
            add("cat");
        }};
        System.out.println(p.wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i]:s[0,i]可以组成单词,在wordDict中能找到
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        //仅用于初始化
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            //[0,j] 和 [j,i] 两个区间，[0,j]区间能否组成单词由dp[j]决定
            for (int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
