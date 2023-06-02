package algorithm.leetcode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-03-11 10:37:01
 * 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class Problem_49 {
    public static void main(String[] args) {
        Problem_49 p = new Problem_49();
        String[] strings = {
                "eat", "tea", "tan", "ate", "nat", "bat"
        };
        List<List<String>> lists = p.groupAnagrams(strings);
        System.out.println(lists);
    }

    //思路：例如 abc acb cba 三个词排序后都是abc，那么可以用hash，然后排序后的为key来判断
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            //排序后作为key，如果已经有了这个key，那么加入到list中去
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<String>(){{add(str);}});
            }
            else {
                List<String> strings = map.get(key);
                strings.add(str);
            }
        }
        for (List<String> value : map.values()) {
            lists.add(value);
        }
        return lists;
    }
}
