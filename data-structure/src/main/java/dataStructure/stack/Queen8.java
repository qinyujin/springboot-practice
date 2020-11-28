package dataStructure.stack;

/**
 * @author :覃玉锦
 * @create :2020-11-21 18:38:00
 * 八皇后问题：在一个8×8的棋盘上，任意两个皇后不会在同一列，同一行，同一斜线
 */
public class Queen8 {
    //八个皇后
    private static int MAX = 8;
    //使用一维数组来表示棋盘：下标表示第n个皇后，arr[n]表示皇后所在列
    private static int[] arr = new int[MAX];
    private static int count=0;
    private static int judgetCount=0;
    public static void main(String[] args) {
        check(0);
        System.out.println("有"+count+"种解法!");
        System.out.println("判断执行的次数："+judgetCount);
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    public static void check(int n){
        if(n==MAX){
            print();
            return;
        }

        //每个皇后都从第一列开始放置，如果冲突则放下一个
        for (int i = 0; i < MAX; i++) {
            arr[n]=i;
            //如果没有冲突，放下一个,有冲突，放下一列
            if(judget(n)){
                check(n+1);
            }
        }
    }

    /**
     * 判断第n个皇后是否与前面的皇后冲突
     * @param n
     * @return
     */
    public static boolean judget(int n){
        judgetCount++;
        for (int i = 0; i < n; i++) {
            //只需判断是否同一列/同一对角线
            if(arr[n]==arr[i] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印解法
     */
    public static void print(){
        for (int i = 0; i < MAX; i++) {
            System.out.print(arr[i]+" ");
        }
        count++;
        System.out.println();
    }
}
