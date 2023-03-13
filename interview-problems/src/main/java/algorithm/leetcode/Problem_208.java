package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-13 13:09:00
 * 实现前缀树
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Problem_208 {
    public static void main(String[] args) {
        Problem_208 p = new Problem_208();
        p.insert("apple");
        System.out.println(p.search("apple")); // true
        System.out.println(p.search("app")); //false
        System.out.println(p.startsWith("app")); // true
        p.insert("app");
        System.out.println(p.search("app")); // true
    }

    //字典树
    class TrieNode{
        private boolean isEnd;
        private TrieNode[] next;

        public TrieNode() {
            isEnd = false;
            //26个字母(当前仅小写英文的情况)
            next = new TrieNode[26];
        }
    }

    //初始化root
    public Problem_208() {
        root = new TrieNode();
    }

    private TrieNode root;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            //插入
            if(node.next[c-'a']==null){
                node.next[c-'a'] = new TrieNode();
            }
            //如果不为空则不需要处理。重复利用即可
            node = node.next[c-'a'];
        }
        //最后一个字符设置标志位
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //全部匹配才返回，返回最后一个的isEnd即可
        TrieNode node = this.root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            node = node.next[c-'a'];
            if(node == null)return false;
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //前缀匹配也一样的
        TrieNode node = this.root;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            node = node.next[c-'a'];
            if(node==null)return false;
        }
        return true;
    }
}
