package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-14 11:11:00
 * <p>
 * https://leetcode.cn/problems/compare-version-numbers/
 */
public class Problem_165 {

    public static void main(String[] args) {
        Problem_165 p = new Problem_165();
        System.out.println(p.compareVersion("1.01", "1.001"));
    }

    //转化成int来比较，比如1.01 -> 11 == 1*10 + 1.过程中需要保证两个数的小数点相同，保证最后乘出来的结果位数一样.时间o(n+m)
    //空间o(n+m)
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        //以最长的为准
        for (int i = 0; i < split1.length || i < split2.length; i++) {
            int x = 0, y = 0;
            if (i < split1.length) {
                x = Integer.parseInt(split1[i]);
            }
            if (i < split2.length) {
                y = Integer.parseInt(split2[i]);
            }
            if (x > y) return 1;
            if (x < y) return -1;
        }
        return 0;
    }

    //遍历字符串的同时计算出sum结果，就不需要多余的空间来存储.时间o(n+m) 空间o(1)
    public int compareVersion2(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; i++) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            i++;

            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; j++) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            j++;

            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }
}
