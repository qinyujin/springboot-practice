package algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-03-25 09:30:00
 * LRU缓存机制
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class Problem_146 {
    public static void main(String[] args) {
        Problem_146 p = new Problem_146(2);
        p.put(1,1);
        p.put(2,2);
        System.out.println(p.get(1));
        p.put(3,3);
        System.out.println(p.map); //{1=1,3=3}
        System.out.println(p.get(2)); //-1
        p.put(4,4);
        System.out.println(p.map);//{4=4,3=3}
        System.out.println(p.get(1));
        System.out.println(p.get(3));
        System.out.println(p.get(4));
    }

    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        public Node(){};

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    class DoubleLinkedList{
        Node head;
        Node tail;

        //移除链表尾元素
        public void removeNode(Node node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = null;
            node.pre = null;
        }

        public void addHead(Node node){
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        public DoubleLinkedList() {
            //为了避免head为空，给head和tail赋值一个空的节点
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }
    }

    private int capacity;
    //结构可以使用LinkedHashMap，这里也是类似的模拟了数组+链表的形式，数组使用的map
    private Map<Integer, Node> map;
    private DoubleLinkedList linkedList;
    //在题目中修改为LRUCache
    public Problem_146(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.linkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        //get不需要移除，但是需要调整node位置为表头。
        if(map.size()==0)return -1;
        if(map.containsKey(key)){
            Node node = map.get(key);
            //移动到表头
            linkedList.removeNode(node);
            linkedList.addHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        //思路：如果包含这个key，那么把该key移动到链表表头
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            map.put(key,node);
            linkedList.removeNode(node);
            linkedList.addHead(node);
        }
        //如果不包含这个key，那么需要判断是否达到了容量，如果达到容量需要移除表尾元素
        else {
            if(map.size()==capacity){
                //如果容量满了，移除表尾然后加入到链表
                Node node = new Node(key,value);
                map.remove(linkedList.tail.pre.key);
                map.put(key,node);
                linkedList.removeNode(linkedList.tail.pre);
                linkedList.addHead(node);
            }
            else {
                Node node = new Node(key, value);
                map.put(key,node);
                linkedList.addHead(node);
            }
        }
    }
}
