package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 13:00:00
 * 丑数
 * https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class UglyNumber {
    public static void main(String[] args) {
        System.out.println(new UglyNumber().GetUglyNumber_Solution(7));
    }

    public int GetUglyNumber_Solution(int index) {
        //1-6都是丑数，其余的可以通过公式
        if(index<=6){
            return index;
        }
        int[] dp = new int[index];
        int i2=0,i3=0,i5=0;
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            int next2 = dp[i2]*2,next3 = dp[i3]*3,next5 = dp[i5]*5;
            //取其中最小的
            dp[i] = Math.min(next2,Math.min(next3,next5));
            //取谁谁++
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
