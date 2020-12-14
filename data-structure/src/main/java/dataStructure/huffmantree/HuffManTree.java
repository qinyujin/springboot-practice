package dataStructure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-12-11 14:21:00
 * 赫夫曼树：对于带有权值的叶子节点，权值大的离根节点越近。
 */
public class HuffManTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffManTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root==null){
            System.out.println("根节点为空");
        }
        else {
            root.preOrder();
        }
    }

    /**
     * 构建一颗赫夫曼树
     *
     * @param arr 需要构建成赫夫曼树的带权叶子节点
     * @return 返回树根节点
     */
    public static Node createHuffManTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        //先把arr放入list类型中，方便后序操作
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        //循环到只剩一个节点，这个节点就是所有叶子节点之和，即构建赫夫曼树完成
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //1、取到两个最小的权值，合并为一个节点，添加进去
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            //设置一下左孩子和右孩子
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.add(parent);
            //删掉这两个
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }

        return nodes.get(0);
    }
}

/**
 * 节点，带权值
 */
class Node implements Comparable<Node> {
    public int value;

    public Node left;

    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //升序
        return this.value - o.value;
    }
}