package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-12-10 16:15:00
 * 堆排序，利用大顶/小顶堆来实现的排序。这里的树都是以数组形式体现的
 * avg-o(nlogn) baddest-o(nlogn)
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]= (int)(Math.random()*800000);
        }
        /*int[] arr = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));*/

        long begin = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(arr.length+"条数据堆排序耗费时间："+(end - begin));
    }

    /**
     * 堆排序大体可以分成三个步骤：
     * 1、把一个数组调整成为堆（大顶/小顶）
     * 2、把这个堆堆顶元素放到数组尾
     * 3、把剩余元素依次调整
     * length/2-1  树的最后一个非叶子节点
     */
    public static void heapSort(int[] arr){
        int temp;
        //先把原始数组构造成为一个堆，升序大顶，降序小顶
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr, arr.length,i );
        }
        //构造完了初始大顶堆后，开始把最大的数往最后移动
        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, i, 0);
        }
    }

    /**
     * 把一个非叶子节点及其子树调整为一个大顶堆
     * 2*n+1 为左子节点 2*n+2 为右子节点
     * @param arr 需要调整的树（数组）
     * @param length 每次调整的长度
     * @param i 非叶子节点的下标
     */
    public static void adjustHeap(int[] arr,int length,int i){
        //临时保存非叶子节点的值
        int temp = arr[i];

        //初始值为左子节点
        for (int k = 2*i+1; k < length; k=k*2+1) {
            //找出两个子树中较大的
            if(k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            //如果子树值大于顶的值，需要交换
            if(arr[k] > temp){
                arr[i] = arr[k];
                //把i指向子树，因为如果右子树，本次交换可能会导致破坏子树的大顶堆结构,这里i主要影响arr【i】
                i = k;
            }
            else {
                //子树小于顶，不需要调整
                break;
            }
            //进行交换，把小的换到子树中
            arr[i] = temp;
        }
    }
}
