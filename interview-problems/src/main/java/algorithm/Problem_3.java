package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 09:19:00
 * 3. 数组中重复的数字
 * 题目描述：
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的
 * ，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * 用例：
 * Input:
 * {2, 3, 1, 0, 2, 5}
 * Output:
 * 2
 *
 * 解题思路：
 * 要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
 * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
 * 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复：
 */
public class Problem_3 {
    public static void main(String[] args) {
        int[] input = {2,3,1,0,2,5,3};
        int[] duplication = new int[1];
        Problem_3 problem3 = new Problem_3();
        if(problem3.duplicate(input,input.length,duplication)){
            System.out.println(duplication[0]);
        }
        else {
            System.out.println("false");
        }
    }

    public boolean duplicate(int[] numbers, int length, int[] duplication){
        //判断对应下标是否存在对应数字，如果当前数字不是在对应下标，交换。
        if(numbers==null || length==0)return false;
        for (int i = 0; i < length; i++) {
            if(numbers[i] != i){
                if(numbers[numbers[i]] == numbers[i]){
                    duplication[0] = numbers[i];
                    return true;
                }
                int temp = numbers[numbers[i]];
                numbers[numbers[i]] = numbers[i];
                numbers[i] = temp;
                i--;
                continue;
            }
        }
        return false;
    }
}
