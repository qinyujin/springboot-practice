package algorithm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-01-28 20:59:00
 * 骰子求和
 * https://www.lintcode.com/problem/dices-sum/description
 * 扔 n 个骰子，向上面的数字之和为 S。给定 n，请列出所有可能的 S 值及其相应的概率。
 */
public class TouziSum {
    public static void main(String[] args) {
        int n = 1;
        System.out.println(new TouziSum().dicesSum(n));
    }

    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        final int face = 6;
        final int pointNum = face*n;
        int[][] dp = new int[n+1][pointNum+1];
        for (int i = 1; i <= face; i++) {
            //一个骰子每个面出现次数都是1
            dp[1][i] = 1;
        }
        //从第二个骰子开始统计
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= pointNum; j++) {
                for (int k = 1;k<=face && k<=j;k++){
                    dp[i][j] +=  dp[i-1][j-k];
                }
            }
        }
        final double totalNum = Math.pow(6,n);
        List<Map.Entry<Integer,Double>> retList = new ArrayList<>();
        for (int i = n; i <= pointNum; i++) {
            retList.add(new AbstractMap.SimpleEntry<>(i,dp[n][i]/totalNum));
        }
        return retList;

    }
}
