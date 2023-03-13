package algorithm;

import algorithm.offer.ListNode;

import java.util.Arrays;
import java.util.List;

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
//        Scanner in1 = new Scanner(System.in);
//        String line = in1.nextLine();
//        Scanner in2 = new Scanner(line);
//        List<Integer> inputs = new ArrayList<>();
//        while (in2.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            inputs.add(in2.nextInt());
//        }
//
//        int[] ints = new int[inputs.size()];
//        int i = 0;
//        for (Integer input : inputs) {
//            ints[i++] = input;
//        }
//        Practice p = new Practice();
//        p.function2(ints);

        Practice p = new Practice();

        int i1 = p.function3(2, 2, new int[]{200, 200}, new boolean[3], 1, 0);
        System.out.println(p.max);
    }

    public int function(List<Integer> inputs) {
        int[] dp = new int[inputs.size()];
        Arrays.fill(dp, 0);

        if (!inputs.contains(0)) return 0;

        for (int i = 1; i < inputs.size(); i++) {
            if (inputs.get(i - 1) == 1 && inputs.get(i) == 0) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        int max = 0;
        for (int i = inputs.size() - 2; i >= 0; i--) {
            if (inputs.get(i + 1) == 1 && inputs.get(i) == 0) {
                dp[i] = dp[i] + 1;
            }
            if (dp[i] > max) max = dp[i];
        }

        return max;
    }

    public void function2(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("0");
            return;
        }
        int left = 0, right = 0, area = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int j = nums.length - 1 - i;
            if (nums[i] > nums[i + 1] && nums[i] > nums[left]) {
                left = i;
            }
            if (nums[j] > nums[j - 1] && nums[j] > nums[right]) {
                right = j;
            }

            //左或右指针还需要移动
            int minHeight = Math.min(nums[left], nums[right]);
            if (nums[left] != nums[right]) {
                if (minHeight == nums[left]) {
                    while (left + 1 < nums.length && nums[left + 1] > minHeight) {
                        left++;
                    }
                }
                if (minHeight == nums[right]) {
                    while (right - 1 >= 0 && nums[right - 1] > minHeight) {
                        right--;
                    }
                }
            }

            if (left + 1 < right) {
                int tempArea = 0;
                for (int k = left + 1; k < right; k++) {
                    int height = minHeight - nums[k];
                    tempArea += height;
                }
                area = Math.max(area, tempArea);
            }
        }

        if (area == 0) {
            System.out.println("0");
            return;
        }

        System.out.println(left + " " + right + ":" + area);
    }

    private int max = -1;

    public int function3(int n, int m, int[] nValues, boolean[] visited, int curN, int curSum) {
        if (curN > n) return 0;

        for (int i = 0; i <= n; i++) {
            curSum = 0;
            if (!visited[i]) {
                visited[i] = true;
                int calculateValue = getCalculateValue(nValues[curN - 1], i);
                curSum += calculateValue;
                max = Math.max(max, curSum);
                function3(n, m, nValues, visited, curN + 1, curSum);
                visited[i] = false;
                curSum -= calculateValue;
            }
        }

        return 0;
    }

    public int getCalculateValue(int value, int personNum) {
        switch (personNum) {
            case 0:
                return (int) (value * 0.8);
            case 1:
                return value;
            case 2:
                return (int) (value * 1.1);
            case 3:
                return (int) (value * 1.2);
            case 4:
                return (int) (value * 1.3);
            default:
                break;
        }
        return value;
    }

    /**
     * arr transfer to listNode
     *
     * @param arr
     * @return
     */
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