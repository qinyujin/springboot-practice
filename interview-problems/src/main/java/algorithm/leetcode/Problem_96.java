package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 11:17:00
 * 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/solution/
 */
public class Problem_96 {
    public static void main(String[] args) {
        Problem_96 p =new Problem_96();
        System.out.println(p.numTrees(2));
    }

    //思路：一个根的二叉树数量求解可以分解成求左子树和右子树。可以使用dp
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        //根或者没有根都是一种情况
        dp[0]=dp[1] = 1;
        //整个过程是一个笛卡儿积，所以应该是左子树数量乘上右子树数量
        for (int i = 2; i <= n; i++) {
            //子树从[1,i]区间来取，[1,j]是左,[j,1]是右，dp[i]为这两者笛卡儿积
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
