package dataStructure.tree;

/**
 * @author :覃玉锦
 * @create :2020-12-10 09:51:00
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder();
    }
}

/**
 * 使用数组形式存储二叉树
 */
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //不用传参
    public void preOrder(){
        this.preOrder(0);
        System.out.println();
    }

    /**
     * 顺序存储二叉树形式的前序遍历
     * @param index
     */
    public void preOrder(int index){
        if(arr==null || arr.length == 0){
            System.out.println("树为空！");
            return;
        }

        System.out.print(arr[index]+" ");
        //递归遍历左子树
        if((2*index+1)<=arr.length-1)
        preOrder(2*index+1);

        //递归遍历右子树
        if((2*index+2)<=arr.length-1)
            preOrder(2*index+2);
    }
}