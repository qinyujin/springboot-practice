package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-03 13:26:00
 * 正则表达式，可以参考剑指offer的19题
 * <p>
 * https://leetcode.cn/problems/regular-expression-matching/solution/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
 */
public class Problem_10 {
    public static void main(String[] args) {
        Problem_10 p = new Problem_10();
        System.out.println(p.isMatch("aa", "a"));
        System.out.println(p.isMatch("aa", "a*"));
        System.out.println(p.isMatch("ab", ".*"));
    }

    /*从右往左推导是否匹配，可以通过拆成小模式来推导。x*可以分成3种情况，0，1，n(>=2)。特别加入1是因为1比较好处理 示例串: s="aabbd" p="aab*d".设dp[i][j]存储字符
    串s[i-1]和字符串p[j-1]的匹配结果。为什么是i-1? 因为字符串下标从0开始，而这里dp[1][1]是表示的s的第一个字符和p第一个字符匹配，实际对应的就是s[0],p[0]
    1、condition:s[i-1]==p[j-1] || p[j-1]=='.' dp[i][j] = dp[i-1][j-1]   满足此条件，该位置匹配成功，整串的匹配结果取决于字串的匹配结果
    2、condition:s[i-1] != p[i-1] 此时，若p[i-1]为*还有可能匹配成功，因此分析a*的情况。p[j-1]为*的话还需要比较s[i-1]和p[j-2]的值是否相同
    2_1、s[i-1] == p[j-2] x* 可以分成3种情况：x出现0次、1、n(n>=2)次。继续具体分析下面3种情况
    2_1_1、x*->0 由于x出现0次，可看作把x*串在p串中移除掉。示例串变为:"aa(b*)d",因此此时需要比较的是字串s[i-1]==p[j-3] 即dp[i-1][j-1] = dp[i-1][j-3] <==> dp[i][j] = dp[i][j-2]
    2_1_2、x*->1 x出现1次，由于2_1条件就是 s[i-1]==p[j-2]，因此判断字段s[i-2]==p[j-3]? 即dp[i-1][j-1] = dp[i-2][j-3] <==> dp[i][j] = dp[i-1][j-2]
    2_1_3、x*->n x出现次数>=2，由2_1得s[i-1]==p[j-2]，因此可以同时移除一个末尾x。p串可以看成aab*(b)d，s串是aab(b)d。移除后现在的情况等同于2_1，即p的位置为*。
    因此可理解为移除一个，回到2_1再匹配.那么可以写成s[i-2]==p[j-1] 即 dp[i-1][j-1] = dp[i-2][j-1] <==> dp[i][j] = dp[i-1][j]
    2_2、s[i-1] != p[j-2] 此时*前的元素不相等，还有x*->0 的情况可以匹配，那么消除x*后s[i-1] == p[j-3] <==> dp[i][j] = dp[i][j-2]*/
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        //都为空串肯定匹配
        dp[0][0] = true;

        //如果s为空串，p不为空，只能是右端为*，然后走x*->0消除p的字串，最后p消除成为空串
        for (int i = 1; i <= n; i++)
            if (p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        //dp[i][j - 2]:x* count as 0    dp[i-1][j-2]:x* count as 1  dp[i-1][j]:x* count as n(n>=2)
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
