package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-25 09:27:00
 * 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Problem_3 {
    public static void main(String[] args) {
        Problem_3 p = new Problem_3();
        System.out.println(p.lengthOfLongestSubstring("abcabcbb")); //3
    }

    public int lengthOfLongestSubstring(String s) {
        int curLen = 0;
        int maxLen = 0;
        //字符前一个的位置。例如arabcacfr 如果到达第二个a，那么preIndex[a]就是0
        int[] preIndexs = new int[128];
        //初始为-1表示未访问到
        Arrays.fill(preIndexs,-1);
        for (int curI = 0; curI < s.length(); curI++) {
            //当前字符的前一个位置，直接存储ASCII码
            int preI = preIndexs[s.charAt(curI)];
            //如果当前字符未访问过，或者是当前字符和当前字符前一个的位置gap大于当前统计的字符长度，那么当前长度+1
            if(preI == -1 || curI-preI > curLen){
                curLen++;
            }
            else {
                //出现重复字符，统计上一个串最长长度
                maxLen = Math.max(maxLen,curLen);
                //例如arabcacfr，ar -> ra 所以新的长度是2-0 = 2
                curLen = curI-preI;
            }
            //更新or记录当前字符的位置。
            preIndexs[s.charAt(curI)] = curI;
        }
        //最后需要更新一次，如果curi到末尾
        maxLen = Math.max(maxLen,curLen);
        return maxLen;
    }
}
