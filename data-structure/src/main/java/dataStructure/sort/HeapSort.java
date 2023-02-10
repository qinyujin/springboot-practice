package dataStructure.sort;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author :覃玉锦
 * @create :2020-12-10 16:15:00
 * 堆排序，利用大顶/小顶堆来实现的排序。这里的树都是以数组形式体现的
 * avg-o(nlogn) baddest-o(nlogn)
 * <p>
 * 除了手写实现堆，使用PriorityQueue也可以实现堆。默认为小顶堆，可以通过重写方法来实现大顶堆。
 */
public class HeapSort {
    public static void main(String[] args) {
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }*/
        int[] arr = {4, 6, 8, 5, 9};
        /*long begin = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length + "条数据堆排序耗费时间：" + (end - begin));*/

        //PriorityQueue类底层是堆排序实现的。
        System.out.println("===========PriorityQueue实现大/小顶堆===========");
        //小顶堆
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.print(next + " ");
        }
        System.out.println();
    }

    /**
     * 堆排序大体可以分成三个步骤：
     * 1、把一个数组调整成为堆（大顶/小顶）
     * 2、把这个堆堆顶元素放到数组尾
     * 3、把剩余元素依次调整
     * 2*n+1:n节点左子节点    2*n+2:n节点右子节点
     * length/2-1  树的最后一个非叶子节点
     */
    public static void heapSort(int[] arr) {
        int temp;
        //先把原始数组构造成为一个堆，从最后一个子节点往上构建(左->右，下->上)，升序大顶，降序小顶
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, arr.length, i);
        }
        //构造完了初始大顶堆后，开始把堆顶(arr[0])往最后移动，同时重新构建剩下的数组为堆
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, i, 0);
        }
    }

    /**
     * 把一个非叶子节点n及其子树调整为一个大顶堆
     * 2*n+1 为n节点的左子节点 2*n+2 为n节点的右子节点
     * <p>
     * arr+length确定具体调整的数组。
     *
     * @param arr    需要调整的树（数组）
     * @param length 每次调整的长度
     * @param i      非叶子节点的下标
     */
    public static void adjustHeap(int[] arr, int length, int i) {

        //初始值为左子节点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            //找出两个子树中较大的
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子树值大于顶的值，需要交换
            if (arr[k] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[k];
                //进行交换，把小的换到子树中
                arr[k] = temp;

                //把i指向子树，因为如果有子树，本次交换可能会导致破坏子树的大顶堆结构,这里i主要影响arr【i】
                i = k;
            } else {
                //子树小于顶，不需要调整
                break;
            }

        }
    }
}
