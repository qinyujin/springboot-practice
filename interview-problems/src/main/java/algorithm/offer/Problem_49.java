package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 13:00:00
 * 丑数
 * https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Problem_49 {
    public static void main(String[] args) {
        System.out.println(new Problem_49().GetUglyNumber_Solution(7));
    }

    public int GetUglyNumber_Solution(int index) {
        //1-6都是丑数，其余的可以通过公式
        if(index<=6){
            return index;
        }
        int[] dp = new int[index];
        //记录因数为2的位置，3、5的位置
        int i2=0,i3=0,i5=0;
        //第一个丑数是1
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            //从三个对应的下标开始，这里比如dp[i2]对应的是2因子的最大丑数。next为下一个丑数。因为是增序，所以
            //要从中取最小的一个。其他的留给下一次来判断
            int next2 = dp[i2]*2,next3 = dp[i3]*3,next5 = dp[i5]*5;
            dp[i] = Math.min(next2,Math.min(next3,next5));
            //保留哪个丑数，对应的因子下标就往前走。
            if(dp[i]==next2){
                i2++;
            }
            if(dp[i]==next3){
                i3++;
            }
            if(dp[i]==next5){
                i5++;
            }
        }
        return dp[index-1];
    }
}
