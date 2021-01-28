/**
 * @author :覃玉锦
 * @create :2021-01-22 21:46:00
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new Test().test("12258"));
    }

    public int test(String s){
        if(s==null || s.length()==0)return -1;
        int[] dp = new int[s.length()+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int one = Integer.valueOf(s.substring(i-1,i));
            if(one!=0){
                dp[i]+=dp[i-1];
            }
            if(s.substring(i-2,i)=="0"){
                continue;
            }
            int two = Integer.valueOf(s.substring(i-2,i));
            if(two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];
    }
}
