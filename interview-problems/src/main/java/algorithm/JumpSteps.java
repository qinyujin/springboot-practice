package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 18:34:00
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class JumpSteps {
    public static void main(String[] args) {
        JumpSteps js = new JumpSteps();
        System.out.println(js.JumpFloor2(3));
    }

    public int JumpFloor(int target) {
        if(target==0 || target==1 || target==2){
            return target;
        }
        else {
            int[] arr = new int[target+1];
            //1和2都只有一种跳法
            arr[1] = 1;
            arr[2] = 2;
            for (int i = 3; i <= target; i++) {
                arr[i] = arr[i-1]+arr[i-2];
            }
            return arr[target];
        }
    }

    /**
     * 变态版跳阶梯：
     * 从1到n有n种跳法
     * @param target
     * @return
     */
    public int JumpFloor2(int target){
        //f[n] = f[n-1]+f[n-2].....      f[0] = f[1] = 1
        int[] arr = new int[target+1];
        arr[0] = arr[1] = 1;
        for (int i = 2; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                arr[i] += arr[j];
            }
        }
        return arr[target];
    }
}
