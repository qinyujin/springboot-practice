package algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 */
public class Practice {
    public static void main(String[] args) {
//        Practice p = new Practice();
    }

    private int size;

    private int min;

    private HashMap<Integer, Node> cache;

    private HashMap<Integer, LinkedHashSet<Node>> freqMap;

    public Practice(int capacity) {
        size = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (size == 0) return;
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            freqInc(node);
        } else {
            if (size == cache.size()) {
                Node node = removeNode();
                cache.remove(node.key);
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            addNode(node);
        }
    }

    //移动位置
    public void freqInc(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) {
            min = freq+1;
            freqMap.remove(freq);
        }
        node.freq++;

        LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(node);
    }

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
        //移除最少频次，最久未使用
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        cache.remove(deadNode.key);
        return deadNode;
    }

    public class Node {
        private int key;

        private int value;

        private int freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}