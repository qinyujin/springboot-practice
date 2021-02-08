package algorithm;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-26 11:55:00
 * 输出字符串全排列
 * https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * <p>
 * 全排列问题
 */
public class Problem_38 {
    public static void main(String[] args) {
        //算法笔记例子
        Problem_38 ss = new Problem_38();
        /*ss.n = 3;
        ss.visited = new boolean[ss.n + 1];
        ss.p = new int[ss.n + 1];*/
//        ss.generate(1);

        //剑指offer
        ArrayList<String> abc = ss.Permutation("ab");
        System.out.println(abc);
    }

    private int n;

    private int[] p;

    boolean[] visited;

    /**
     * 通过填位数来处理
     *
     * @param index 处理到了第index位
     */
    private void generate(int index) {
        //递归的边界，当填满了之后可以输出
        if (index == n + 1) {
            for (int i = 1; i <= n; i++) {
                System.out.print(p[i] + " ");
            }
            System.out.println();
            return;
        }

        //递归的思想：从1到n填入数组。
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                p[index] = i;
                generate(index + 1);
                visited[i] = false;
            }
        }
    }

    private ArrayList<String> list = new ArrayList<>();

    /**
     * 剑指offer 38
     *
     * @param str
     * @return
     */
    private ArrayList<String> Permutation(String str) {
        backtracking(str.toCharArray(), new boolean[128], new StringBuilder());
        return list;
    }

    /**
     * 回溯递归，多位拆分成1+n位处理
     */
    private void backtracking(char[] str, boolean[] visited, StringBuilder stringBuilder) {
        //递归结束的条件，即所有的字符组合完毕
        if (stringBuilder.length() == str.length) {
            if (!list.contains(stringBuilder.toString())) {
                list.add(stringBuilder.toString());
            }
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                stringBuilder.append(str[i]);
                backtracking(str, visited, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                visited[i] = false;
            }
        }
    }
}
