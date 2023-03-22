package algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author :覃玉锦
 * @create :2023-03-21 14:19:00
 * <p>
 * LFU缓存
 * https://leetcode.cn/problems/lfu-cache/
 *
 * 思路：丢弃元素时考虑频率最低、最久未使用的。两个维度可以考虑用map<频率,linkedHashSet<Node>> 来存储
 */
public class Problem_460 {

    public static void main(String[] args) {
        Problem_460 p = new Problem_460(2);
        p.put(1, 1);
        p.put(2, 2);
        System.out.println(p.get(1));
        p.put(3, 3);
        System.out.println(p.get(2));
        System.out.println(p.get(3));
        p.put(4, 4);
        System.out.println(p.get(1));
        System.out.println(p.get(3));
        System.out.println(p.get(4));
    }

    private HashMap<Integer, Node> cache;

    //频次->对应的双向链表       linkedHashSet本身就是一个双链表，并且按照先后顺序insert.相同频次的情况下可以确定先后
    private HashMap<Integer, LinkedHashSet<Node>> freqMap;

    private int size;

    private int min;

    public Problem_460(int capacity) {
        size = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //访问过后，调整位置
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (size == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (size == cache.size()) {
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
        }
    }

    //对于访问到的元素进行位置更新
    public void freqInc(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        //临界值
        if (freq == min && set.size() == 0) {
            min = freq + 1;
        }

        node.freq++;
        LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(node);
    }

    //频次为1的节点
    public void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(1, set);
        }
        set.add(node);
        min = 1;
    }

    public Node removeNode() {
        //需要删除最少频次、最久未使用到的
        LinkedHashSet<Node> set = freqMap.get(min);
        //删除头部节点
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }

    public class Node {
        private int key;

        private int value;

        //使用频率
        private int freq;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            freq = 1;
        }
    }
}
