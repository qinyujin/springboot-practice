package algorithm;

/**
 * @author :覃玉锦
 * @create :2020-12-16 19:23:00
 * 0-1背包 使用动态规划解决
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] weight = {1,4,3};
        //物品的价值
        int[] value = {1500,3000,2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = value.length;
        //最大价值
        int[][] v = new int[n+1][m+1];
        int[][] path = new int[n+1][m+1];

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //如果物品重量大于列（重量），就让对应最大价值为它。减一原因同下
                if(weight[i-1] > j){
                    v[i][j] = v[i-1][j];
                }
                //如果物品重量小于列（重量），比较（上一行的值）和（当前行物品+剩下重量对应的物品值）中较大的数就是结果
                else {
                    //减一是因为i从0开始 v[i-1][j] 上一行对应的数值 value[i-1] 当前行对应的物品价值 j - weight[i-1] 选完本行物品后还剩余的重量 v[i-1][j - weight[i-1]] 上一行对应剩余重量的物品最大价值
                    v[i][j] = Math.max(v[i-1][j],value[i-1] + v[i-1][j - weight[i-1]]);
                    if(v[i-1][j] < value[i-1] + v[i-1][j - weight[i-1]]){
                        path[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("************************");

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }

        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0 && j>0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j-= weight[i-1];
            }
            i--;
        }
    }
}
