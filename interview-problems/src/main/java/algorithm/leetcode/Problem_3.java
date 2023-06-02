package algorithm.leetcode;

import java.util.HashMap;

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
        int left = 0, maxLen = 0;
        //char->index 用来缓存字符出现过的下标
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //出现重复字符
            if (map.containsKey(s.charAt(i))) {
                //当前字符出现重复，舍弃掉该字符，以下一个字符为起点找新的子串
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }

            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }
}
