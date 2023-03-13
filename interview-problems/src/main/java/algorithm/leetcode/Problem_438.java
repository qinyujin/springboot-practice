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
    }

    //cbaebabacd abc ->0, 6 0:cba 6:bac
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = s.length(),m = p.length();
        if (p.length() > s.length()) return res;
        //统计字母数量
        int[] scnt = new int[26];
        int[] pcnt = new int[26];
        //把字符对应位置统计一下
        for (int i = 0; i < m; i++) {
            scnt[s.charAt(i)-'a']++;
            pcnt[p.charAt(i)-'a']++;
        }
        //0下标有相等的
        if(Arrays.equals(scnt, pcnt))res.add(0);
        for (int i = m; i < n; i++) {
            //窗口范围:[i-m,i]，这里统计了数量
            scnt[s.charAt(i-m)-'a']--;
            scnt[s.charAt(i)-'a']++;
            if(Arrays.equals(scnt,pcnt))res.add(i-m+1);
        }
        return res;
    }
}
