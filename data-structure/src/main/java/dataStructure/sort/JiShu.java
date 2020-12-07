package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-12-07 10:14:00
 * 基数排序,空间换时间，如果数据太多会导致空间（内存）不够使用，由于是桶对0-9的数字进行分类，所以不建议有负数的数组使用
 * avg-o(logrB) baddest-o(logrB)
 */
public class JiShu {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
//        int[] arr = {53,3,542,748,14,214};
        long begin = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(arr.length+"条数据基数排序耗费时间："+(end-begin));
    }

    /**
     * 基数排序（桶排序）。基于位数进行排序。空间换时间的典型算法
     * 实现思路：首先有10个桶，下标从0-9。第一次排序把每个元素按照个位数区分放入对应桶中，排完后把桶中元素放入到原数组，第二次排序
     * 按照十位数，不足十位按0补足
     * 以此类推，数据中直至最大位数为止。
     * @param arr
     */
    public static void radixSort(int[] arr){
        //最差情况下，一个桶把全部数据都放进去，所以是arr的length
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //最大的位数
        int maxLenth = (max+"").length();
        int index = 0;

        for (int k = 0,n=1; k < maxLenth; k++,n*=10) {
            //清零统计桶元素数的数组
            for (int i = 0; i < bucketElementCount.length; i++) {
                bucketElementCount[i]=0;
            }
            for (int i = 0; i < arr.length; i++) {
                //个位数
                int digit = arr[i]/n%10;
                //把这个数放入对应桶，同时统计该桶元素的数量
                bucket[digit][bucketElementCount[digit]++] = arr[i];
            }
            //桶中放入完毕后，把桶的数还给原数组
            index = 0;
            for (int i = 0; i < bucket.length; i++) {
                //如果桶里有数据
                if(bucketElementCount[i]!=0){
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        //放入原数组
                        arr[index++] = bucket[i][j];
                    }
                }
            }
        }
    }
}
