package juc.code;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-02-09 16:00:00
 * LRU算法，可以使用jdk的linkedHashMap或者纯手写
 * <p>
 * 方式一：使用linkedHashMap，继承LinkedHashMap，然后重写构造方法和put方法
 *
 * 方式二，纯手写。使用哈希+链表队列来实现。可以参考AQS的等待队列。
 * 思路：参考AQS的双向链表等待队列，那么节点Node类型应该是有key、value和前后指针。然后应该有一个链表结构。因此需要Node和
 * DoubleLinkedList。链表应该是有head和tail。
 */
public class LRUDemo {
    //方式一，通过继承linkedHashMap和重写remove方法来实现
    static class jdkLinkedList<K, V> extends LinkedHashMap<K, V> {
        private int capacity;

        public jdkLinkedList(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return super.size() > capacity;
        }
    }

    //方式二，纯手写。使用哈希+双向链表队列来实现。可以参考AQS的等待队列。
    class Node<K, V> {
        K key;
        V value;
        Node prev;
        Node next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        //头插法
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(Node<K,V> node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next =null;
            node.prev = null;
        }

        //获取尾部元素
        public Node<K,V> getLastNode(){
            return tail.prev;
        }
    }

    private int capacity;
    //用于存取数据，注意一下value是Node类型，为什么使用Node？便于结合链表来操作。
    private Map<Integer,Node<Integer,Integer>> map;
    //链表主要为了方便判断来做一些辅助工作。当put和get的时候，把相应的数据放到头部，如果需要置换，那么把尾部的数据删除。
    private DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public LRUDemo(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        else {
            Node<Integer, Integer> node = map.get(key);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);

            return node.value;
        }
    }

    public void put(int key,int value){
        //如果该key已经有了，更新值
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }
        else {
            //如果位置满了才需要置换，把尾部的删掉再插入到头。如果没满就直接插入到头
            if(capacity==map.size()){
                Node<Integer, Integer> node = doubleLinkedList.getLastNode();
                doubleLinkedList.removeNode(node);
                map.remove(node.key);
            }

            //新增
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        //方式一
//        method1();
        //方式二
        method2();
    }

    private static void method1() {
        jdkLinkedList map1 = new jdkLinkedList(2);
        map1.put(1, 1);
        map1.put(2, 2);
        map1.get(1);
        map1.put(3, 3);
        System.out.println(map1.keySet());
        System.out.println(map1.get(2));
        map1.get(3);
        map1.put(4, 4);
        System.out.println(map1.keySet());
        map1.get(1);
        map1.get(3);
        map1.get(4);
    }

    private static void method2(){
        LRUDemo lruDemo = new LRUDemo(3);
        lruDemo.put(1,1);
        lruDemo.put(2,2);
        lruDemo.put(3,3);
        System.out.println(lruDemo.map.keySet());
        lruDemo.put(4,4);
        System.out.println(lruDemo.map.keySet());
        lruDemo.get(2);
        System.out.println(lruDemo.map.keySet());
        lruDemo.put(5,5);
        System.out.println(lruDemo.map.keySet());
    }
}
