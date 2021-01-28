package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-28 11:23:00
 * 把数组排成最小的数
 * https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tqId=11185&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 思路：在排序是考虑s1+s2<s2+s1是否成立，如果成立那么s1在前。
 */
public class ArrToMinNum {
    public static void main(String[] args) {
        int[] arr = {3,32,321};
        System.out.println(new ArrToMinNum().PrintMinNumber(arr));
    }

    public String PrintMinNumber(int [] numbers) {
        if(numbers==null || numbers.length==0)return "";
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] =String.valueOf(numbers[i]);
        }
        //如果s1+s2小于s2+s1则s1在前
        Arrays.sort(nums,(s1,s2) -> (s1+s2).compareTo(s2+s1));
        String ret = "";
        for (String num : nums) {
            ret+=num;
        }
        return ret;
    }
}
