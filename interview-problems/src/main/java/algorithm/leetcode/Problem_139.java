package algorithm.leetcode;

import java.util.ArrayList;
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
        List<String> wordDict = new ArrayList<String>(){{add("cats");add("dog");add("sand");add("and");add("cat");}};
        System.out.println(p.wordBreak(s, wordDict));
    }

    //思路：使用dp，如果s.startWith(wordDict[j],i) 那么说明dp[i]是true，即s可以拆分为到i
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            if(!dp[i])continue;
            for (String word : wordDict) {
                //如果i前面的字符串已经匹配(s.startWith(word,i))，说明此时j位置的结果取决于后面的字符
                if(i+word.length() <= s.length() && s.startsWith(word,i)){
                    dp[i+word.length()] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
