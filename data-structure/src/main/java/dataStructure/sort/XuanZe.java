package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-11-26 09:20:00
 * 选择排序
 */
public class XuanZe {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
        long begin = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("选择排序耗费时间:"+(end-begin));

        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i]+ " ");
        }
    }

    public static void selectSort(int[] arr){
        int min=0;
        int minIndex = 0;
        for (int i = 0; i < arr.length-1; i++) {
            //从每个位置后面来挑最小的数
            min = arr[i];
            minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<min){
                    min=arr[j];
                    minIndex = j;
                }
            }
            //找到最小数，放到开头
            if(minIndex!=i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
