package algorithm.offer;

import java.util.PriorityQueue;

/**
 * @author :覃玉锦
 * @create :2021-01-27 17:55:00
 * 数据流中的中位数
 * https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * 思路：取排序好的中位数，并且偶数
 */
public class Problem_41_1 {
    public static void main(String[] args) {
        Problem_41_1 problem411 = new Problem_41_1();
        for (int i = 1; i<= 3; i++) {
            problem411.Insert(i);
        }
        System.out.println(problem411.GetMedian());
    }

    //例如 {1,2,3,4,5,6,7,8} 八个数的中位数是（4+5）/2，而4是左边的最大，5是右边最小。因此左边部分使用大顶堆，右边小
    //并且需要保证右边全部大于左边，这里可以通过先放入左边，然后把左边的顶放入右边来实现

    //大顶堆
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2) -> o2-o1);
    //小顶堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    private int N = 0;

    public void Insert(Integer num) {
        //偶数需要放入右边，由于要保证右边的所有数都大于左边，可以先放入左边
        if(N%2==0){
            left.add(num);
            right.add(left.poll());
        }
        //奇数同理
        else {
            right.add(num);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian() {
        if(N%2==0){
            return (left.peek() + right.peek())/2.0;
        }
        else {
            return (double) right.peek();
        }
    }
}
