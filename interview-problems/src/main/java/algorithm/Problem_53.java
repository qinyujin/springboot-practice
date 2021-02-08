package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 14:55:00
 * 数字在升序数组中出现的次数
 * https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11190&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 统计一个数字在升序数组中出现的次数。
 *
 * 思路：升序序列查找可以用二分
 */
public class Problem_53 {
    public static void main(String[] args) {
        Problem_53 problem53 = new Problem_53();
        int[] arr = {1,1,1,1,1,1,1,2,3,3,3,3,4,5};
        int k = 1;
        System.out.println(problem53.GetNumberOfK(arr, k));
    }

    public int GetNumberOfK(int [] array , int k) {
        int left = 0,right = array.length-1;
        int location = 0;
        while (left<=right){
            int mid = (left+right)/2;
            if(array[mid] > k){
                right = mid-1;
            }
            else if(array[mid] < k){
                left = mid+1;
            }
            else {
                //找到了之后，定位到第一个该数的下标
                while (mid>=0){
                    if(array[mid] == k){
                        location = mid;
                        mid--;
                    }
                    else {
                        location = mid+1;
                        break;
                    }
                }
                break;
            }
        }
        int count = 0;
        for (int i = location; i < array.length; i++) {
            if(array[i]!=k)break;
            count++;
        }
        return count;
    }
}
