package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-26 09:22:00
 * 二叉搜索树的后序遍历序列
 * https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13&tqId=11176&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
 */
public class BinarySearchTreePost {
    public static void main(String[] args) {
        int[] sequence = {1,2,3,4,5};
        System.out.println(new BinarySearchTreePost().VerifySquenceOfBST(sequence));
    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        //二叉搜索树的后序序列最后一个数是root。
        //所以思路是：
        //1、找到root
        //2、找到第一个大于root的数
        //3、判断第一个数的右边是否都小于root（为什么不找左边，是因为找第一个大于的时候是顺序查找，保证左边都比第一个数大）
        //4、分别递归左字树和右子树
        if(sequence.length==0){
            return false;
        }
        return Verify(sequence,0,sequence.length-1);
    }

    public boolean Verify(int[] sequence,int start,int end){
        //找到根
        if(end - start <= 1){
            return true;
        }
        int root = sequence[end];
        int curIndex = start;
        //找到第一个大于root的数
        while (curIndex < end && sequence[curIndex] <= root){
            curIndex++;
        }
        for (int i = curIndex; i < end; i++) {
            if(sequence[i] < root){
                return false;
            }
        }
        return Verify(sequence,start,curIndex-1) && Verify(sequence,curIndex,end-1);
    }
}
