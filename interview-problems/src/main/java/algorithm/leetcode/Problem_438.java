package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-19 10:05:00
 * 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class Problem_438 {
    public static void main(String[] args) {
        Problem_438 p = new Problem_438();
        System.out.println(p.findAnagrams("cbaebabacd", "abc")); // [0,6]
        System.out.println(p.findAnagrams("abab", "ab")); // [0,1,2]

        System.out.println(p.findAnagrams_2("cbaebabacd", "abc"));
    }

    //cbaebabacd abc ->0, 6 0:cba 6:bac
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (p.length() > s.length()) return res;
        //统计字母数量
        int[] scnt = new int[26];
        int[] pcnt = new int[26];
        //把字符对应位置统计一下
        for (int i = 0; i < m; i++) {
            scnt[s.charAt(i) - 'a']++;
            pcnt[p.charAt(i) - 'a']++;
        }
        //0下标有相等的
        if (Arrays.equals(scnt, pcnt)) res.add(0);
        for (int i = m; i < n; i++) {
            //窗口范围:[i-m,i]，这里统计了数量
            scnt[s.charAt(i - m) - 'a']--;
            scnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(scnt, pcnt)) res.add(i - m + 1);
        }
        return res;
    }

    //滑动窗口。思路是通过把p构造成int[] target 来统计各字符所需数量，当int[] windows 滑动的过程中满足条件则说明找到了位置
    public List<Integer> findAnagrams_2(String s, String p) {
        int[] target = new int[26];
        int[] windows = new int[26];
        int left = 0, right = 0;
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray()) {
            target[c-'a']++;
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            windows[c - 'a']++;
            while (windows[c - 'a'] > target[c - 'a']) {
                char d = s.charAt(left);
                left++;
                windows[d - 'a']--;
            }
            if (right - left == p.length()) {
                res.add(left);
            }
        }

        return res;
    }
}
