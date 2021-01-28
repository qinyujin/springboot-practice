/**
 * @author :覃玉锦
 * @create :2021-01-26 21:29:00
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,2,2,5,4,2};
        System.out.println(new Test().find(arr));
    }

    public int find(int[] array){
        int majorval = array[0];
        for (int i = 1,cnt = 1; i < array.length; i++) {
            cnt = array[i] == majorval ? cnt+1 : cnt -1 ;
            if(cnt == 0){
                majorval = array[i];
                cnt = 1;
            }
        }
        //拿到出现最多的值
        int cnt = 0;
        for (int i : array) {
            if(i == majorval){
                cnt++;
            }
        }
        return cnt > array.length/2 ? majorval : 0;
    }
}
