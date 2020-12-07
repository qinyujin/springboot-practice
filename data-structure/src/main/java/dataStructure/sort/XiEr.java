package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-11-27 09:01:00
 * 希尔排序
 * avg-o(nlogn) baddest-o(n^s) 1<s<2
 */
public class XiEr {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        long begin = System.currentTimeMillis();
//        shellSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(arr.length+"条数据希尔排序(交换法)耗时："+(end-begin));

        long begin2 = System.currentTimeMillis();
        shellSort2(arr);
        long end2 = System.currentTimeMillis();
        System.out.println("希尔排序(后移法)耗时："+(end2-begin2));
    }

    /**
     * 交换法实现，效率不如简单插入排序，不建议使用
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp =0 ;
        //分组思想：每次分组为length/2，第一次5组，第二次5/2=2组...
        //每次分多少组
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            //把从gap往后的每一个数据都遍历处理
            for (int i = gap; i < arr.length; i++) {
                //增量排序，每次排序的数量逐渐增多
                for(int j=i-gap;j>=0;j-=gap){
                    //交换
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j+gap];
                        arr[j+gap]=arr[j];
                        arr[j]=temp;
                    }
                }
            }
        }
    }

    /**
     * 移动法实现希尔排序
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            //把从gap往后的每一个数据都遍历处理
            for (int i = gap; i < arr.length; i++) {
                //后移：相当于分组之后，每一组单独使用简单插入排序
                int insertIndex = i;
                int insertVal = arr[i];
                //从第i个向左开始，每gap个数进行判断,注意第一个判断条件
                if(insertVal < arr[insertIndex-gap]){
                    while (insertIndex-gap>=0 && insertVal<arr[insertIndex-gap]){
                        arr[insertIndex] = arr[insertIndex-gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertVal;
                }
            }
        }
    }
}
