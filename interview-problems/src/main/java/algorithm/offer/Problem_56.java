package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 16:06:00
 * 数组中只出现一次的数字
 * https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13&tqId=11193&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 * 位运算：
 * 异或 ^ ：不同为1，相同为0
 * 与 & ：全1为1
 * 或 | ：有1为1
 */
public class Problem_56 {
    public static void main(String[] args) {
        int[] arr = {2,4,3,6,3,2,5,5};
        int[] num1 = new int[arr.length];
        int[] num2 = new int[arr.length];
        Problem_56 problem56 = new Problem_56();
        problem56.FindNumsAppearOnce(arr,num1,num2);
        System.out.println();
    }

    /**
     * {2,4,3,6,3,2,5,5}
     * 0100
     * 0110 -> 0010
     * 由于两个数不同，必有一位是0或1，找出这个位置，按照它来分子数组。如上例，那么倒数第二位是分离位。
     * 根据这一位为1或为0可以分成：
     * {2,3,6,3,2} 和 {4,5,5}
     * 两个子数组。然后对这两个子数组每位异或即可
     *
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        //以演示数据举例
        //需要使用0来异或运算
        int diff = 0;
        //由于相同数据会抵消，因此实际上是4和6的异或运算即0100 ^ 0110 -> 0010 由于一定有两个数不同，因此diff至少有一个1
        for (int num : array) {
            diff^=num;
        }
        //-diff = 1110  0010 & 1110 -> 0010=2
        //diff == 0010，这个操作主要是找到最后一位非0位，为什么一定有1：因为-diff有尾部+1的操作，一定有一位不同
        diff &=-diff;
        for (int num : array) {
            //0010 与运算，因为全1才为1，所以只有倒数第二位为0的才满足这个条件
            if((diff & num) == 0){
                //倒数第二位为1和为0的分开放到两个数组，同时进行异或运算把相同的抵消
                num1[0]^=num;
            }
            else {
                num2[0]^=num;
            }
        }
    }
}
