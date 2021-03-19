package algorithm.offer;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-28 20:17:00
 * 滑动窗口的最大值
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 */
public class Problem_59 {
    public static void main(String[] args) {
        int[] num = {2,3,4,2,6,2,5,1};
        int size = 3;
        System.out.println(new Problem_59().maxInWindows(num, size));
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if(size==0 || num.length==0){
            return new ArrayList<>();
        }
        int start = 0,end = size-1,max = 0;
        ArrayList<Integer> retList = new ArrayList<>();
        while (end < num.length){
            for (int i = start; i <= end; i++) {
                if(max < num[i]){
                    max = num[i];
                }
            }
            start++;
            end++;
            retList.add(max);
            max = 0;
        }
        return retList;
    }
}
