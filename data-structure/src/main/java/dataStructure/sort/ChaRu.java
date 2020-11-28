package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-11-26 14:08:00
 * 插入排序
 */
public class ChaRu {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        long begin = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("插入排序八万条数据耗时："+(end-begin));
    }

    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i-1;
            //下标不越界并且插入值小于前面的值则数组后移
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                //后移
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;
        }
    }
}
