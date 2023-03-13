package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 16:13:00
 * 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * 例如ADOBECODEBANC 找到含有ABC的最短子串，那么找到BANC
 */
public class Problem_76 {
    public static void main(String[] args) {
        Problem_76 p = new Problem_76();
        System.out.println(p.minWindow("a", "a"));
        //"aaflslflsldkalskaaa"
        //"aaa"
    }

    //思路：使用滑动窗口，通过左指针和右指针来实现。当窗口内包含了所有的目标字符，那么尝试缩短窗口，即左指针右移
    //如果窗口不包含所有子字符，那么右指针移动。如何判断当前窗口是否包含t的所有字符？
    //可以使用一个int[]来统计，假设 t:"ABC" 那么使用数组need[]来统计所需要的t中字符个数。
    //当窗口为0时，need数组：{"A:1","B:1","C:1"} 当窗口包含了所有字符时need状态：{"A:0","B:0","C:0"}
    //如果need中字符的数量为负数，那么说明多余了这个字符，之后是可以去掉的。

    //总体可分三步：
    //1、窗口右扩，字符进入窗口
    //2、当窗口含有所有t的字符后，即needCnt=0，窗口左往右移，条件是need[s.charAt[l]]<0 因为非t的字符在need中都是负数，
    //即多余的。t字符都是0
    //3、当移动完了，那么就说明现在的l和r构成的窗口是一个子串。记录当前串的长度、开始位置，然后l++返回第一步接着往下找
    public String minWindow(String s, String t) {
        int l = 0, r = 0, min = Integer.MAX_VALUE;
        int[] need = new int[128];
        //统计还需要的字符个数，避免每次都去遍历need数组
        int needCnt = t.length();
        //记录最短字符串的开始位置
        int start = 0;
        //统计需要的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //遍历所有的字符，这里只用管右边，因为左边的操作在里面实现
        while (r < s.length()) {
            //1、添加元素到窗口中
            char c = s.charAt(r);
            //如果需要当前字符，维护need数组和所需数量Cnt
            if (need[c] > 0) needCnt--;
            need[c]--;
            //如果包含了所有所需元素，接下来就是缩小的过程
            if (needCnt == 0) {
                //2、左下标右移
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;
                    l++;
                }
                //need[l]==0 说明当前元素不能再拿走，记录此时串长度
                if (r - l + 1 < min) {
                    min = r - l + 1;
                    start = l;
                }
                //3、此时需要向右走，所以把l++
                need[s.charAt(l)]++;
                l++;
                needCnt++;
            }
            r++;
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
