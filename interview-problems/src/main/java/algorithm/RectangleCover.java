package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 18:22:00
 * 矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 */
public class RectangleCover {
    public static void main(String[] args) {
        RectangleCover rc = new RectangleCover();
        System.out.println(rc.rectCover(3));
    }

    public int rectCover(int target) {
        if(target==0){
            return 0;
        }
        if(target==1){
            return 1;
        }
        else if(target==2){
            return 2;
        }
        else {
            int[] arr = new int[target+1];
            arr[1] = 1;
            arr[2] = 2;
            for (int i = 3; i <= target; i++) {
                arr[i] = arr[i-1]+arr[i-2];
            }
            return arr[target];
        }
    }
}
