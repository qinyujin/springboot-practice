package algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author :覃玉锦
 * @create :2021-01-27 16:51:00
 * 最小的k个数
 * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tqId=11182&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 * 思路都是用堆排序。
 */
public class Problem_40 {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
//        System.out.println(new MinKNum().GetLeastNumbers_Solution(arr, 3));
        System.out.println(new Problem_40().GetLeastNumbers_Solution2(arr, 3));
    }

    /**
     * 解法1，手写堆排
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if(input == null || k > input.length){
            return new ArrayList<>();
        }
        heapSort(input);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input,int k){
        if(input == null || k > input.length){
            return new ArrayList<>();
        }
        //使用大顶堆，堆大于k时，把堆顶（最大值）弹出来。可以使堆只保留k个最小的。
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2)->o2-o1);
        for (int i : input) {
            queue.add(i);
            if(queue.size() > k){
                queue.poll();
            }
        }
        return new ArrayList<>(queue);
    }

    public void heapSort(int[] arr){
        //先把原始数组调整为大顶堆，然后把第一个元素（Max）放到数组末尾，然后对剩下的继续调整
        for(int i = arr.length/2-1;i>=0; i--){
            adjustHeap(arr,arr.length,i);
        }
        int temp;
        for(int i = arr.length-1;i >=0;i--){
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //交换了根节点，只用调整根节点
            adjustHeap(arr,i,0);
        }
    }

    //大顶堆调整
    public void adjustHeap(int[] arr,int length,int k){
        int temp = arr[k];

        //从当前节点的左子节点开始
        for (int i = 2*k+1; i < length; i = 2*i+1) {
            //把i指向孩子中大的数
            if(i+1 < length && arr[i] < arr[i+1]){
                i++;
            }
            //需要交换
            if(arr[i] > temp){
                arr[k] = arr[i];
                //调整当前节点有可能导致子节点的大顶堆结构被破坏
                k = i;
            }
            else {
                break;
            }
            //当前节点的值放入到子树中
            arr[k] = temp;
        }
    }
}
