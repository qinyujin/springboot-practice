package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:28:00
 * 构建乘积数组
 * https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46?tpId=13&tqId=11204&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 *
 * 思路:
 * 对于数组1如:{1,2,3,4,5} 乘积成为:{120,60,40,30,24} 每一位数除了本身都乘
 */
public class Problem_66 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] multiply = new Problem_66().multiply(arr);
        System.out.println();
    }

    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0,product = 1; i < A.length;product*=A[i],i++) {
            B[i] = product;
        }
        for (int i = A.length-1,product = 1; i >=0 ;product*=A[i],i--) {
            B[i] *= product;
        }
        return B;
    }
}
