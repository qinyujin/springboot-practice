package algorithm;

import algorithm.offer.ListNode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Practice {
    public static void main(String[] args) {
        System.out.println(function(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));


    }

    public static List<List<String>> function(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                List<String> strings = new ArrayList<>();
                strings.add(str);
                map.put(key, strings);
            } else {
                List<String> list = map.get(key);
                list.add(str);
            }
        }

        List<List<String>> resArr = new ArrayList<>();
        for (List<String> value : map.values()) {
            resArr.add(value);
        }
        return resArr;
    }

    public static ListNode buildListNodes(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        for (int i = 0; i < arr.length; i++) {
            tail.next = new ListNode(arr[i]);
            tail = tail.next;
        }

        return head.next;
    }
}
