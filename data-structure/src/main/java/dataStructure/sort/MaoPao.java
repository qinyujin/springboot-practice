package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-11-25 21:32:00
 * 冒泡排序的优化算法
 * avg-o(n^2) baddest-o(n^2)
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        long begin = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(arr.length+"条数据不优化的冒泡排序耗时："+(end-begin));

        long begin2 = System.currentTimeMillis();
        bubbleSort2(arr);
        long end2 = System.currentTimeMillis();
        System.out.println(arr.length+"条数据优化的冒泡排序耗时："+(end2-begin2));
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag =false;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1-i; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            /*if(!flag){
                break;
            }*/
        }
    }

    public static void bubbleSort2(int[] arr){
        int temp = 0;
        boolean flag =false;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1-i; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            //没有发生交换，说明有序
            if(!flag){
                break;
            }
        }
    }
}