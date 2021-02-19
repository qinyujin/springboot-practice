package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-02-14 15:41:00
 * 最长不含重复字符的子字符串
 * 输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。
 */
public class Problem_48 {
    public static void main(String[] args) {
        Problem_48 p = new Problem_48();
        //acfr -> 4
        System.out.println(p.longestSubStringWithoutDuplication("arabcacfr"));
    }

    /**
     * 一个个字符的判断，如果当前字符没有访问过，那么最长长度+1，如果访问过了，判断距离，如果当前的字符串长度小于当前字符和
     * 上一次当前字符的距离，那么还是+1.
     * @param str
     */
    public int longestSubStringWithoutDuplication(String str){
        int curLen = 0;
        int maxLen = 0;
        //字符前一个的位置。例如arabcacfr 如果到达第二个a，那么preIndex[a]就是0
        int[] preIndexs = new int[26];
        //初始为-1表示未访问到
        Arrays.fill(preIndexs,-1);
        for (int curI = 0; curI < str.length(); curI++) {
            //当前字符
            int c = str.charAt(curI)-'a';
            //当前字符的前一个位置
            int preI = preIndexs[c];
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
            preIndexs[c] = curI;
        }
        //最后需要更新一次，如果curi到末尾
        maxLen = Math.max(maxLen,curLen);
        return maxLen;
    }
}
