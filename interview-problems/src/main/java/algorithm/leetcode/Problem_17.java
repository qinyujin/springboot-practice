package algorithm.leetcode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-03-04 09:56:00
 * 电话号码字符组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/solution/hui-su-dui-lie-tu-jie-by-ml-zimingmeng/
 * 拿小灵通按键手机来举例。2有abc字符，3有def字符。。。一直到9有wxyz。现在从[2,9]之间取数字组合，求所有字符组合。
 * 如2,3 即[abc][def]所有字符的两个组合，有ad,ae,af,bd.....
 */
public class Problem_17 {
    public static void main(String[] args) {
        Problem_17 p = new Problem_17();
        System.out.println(p.letterCombinations("23"));
        System.out.println(p.queueMethod("23"));
    }

    /**
     * 全排列变种。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) return combinations;
        HashMap<Character, String> map = initLetterMappingTable();
        StringBuilder combination = new StringBuilder();
        backtrace(combinations, map, digits, 0, combination);
        return combinations;
    }

    /**
     * 通过对字符串的嵌套调用来完成排列组合。例如 abc def 可以先遍历abc，在每一步例如遍历到a时嵌套遍历下一个
     * 那么情况就是a(def) b(def) c(def)
     * 可以通过index 和digit来控制位数。
     *
     * @param combinations 字符串组合，如"ad" "ae"
     * @param digits       字符串的位数，这里的形式如： "23" -> 对应的两个字符串分别是 "abc" "def"
     * @param index        当前遍历到什么位置。如 目标 "ab" 现在 a ->index = 1
     * @param combination  用于拼接字符串
     */
    public void backtrace(List<String> combinations, HashMap<Character, String> map, String digits, int index, StringBuilder combination) {
        //2、边界条件的判断。如果index已经达到了位置说明完成了拼接。
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        //1、逻辑处理，遍历每一个字符串，同时在每一步都往下一位digit递归
        //如digits = "23" ， index=0 这里就可以取到2
        char digit = digits.charAt(index);
        //获取字符串，形如"abc"
        String letter = map.get(digit);
        for (int j = 0; j < letter.length(); j++) {
            //这里开始嵌套遍历。在遍历前字符串的基础上嵌套遍历下一个字符串
            combination.append(letter.charAt(j));
            backtrace(combinations, map, digits, index + 1, combination);
            //退回的时候需要删除，如"ad" 已经遍历完了，该到"ae" 了，需要删除d
            combination.deleteCharAt(index);
        }
    }

    /**
     * 队列流程：组合["abc","def"].  [a,b,c] poll->a->a+d、a+e、a+f add [b,c,ad,ae,af] continue...
     *
     * @param digit
     * @return
     */
    public List<String> queueMethod(String digit) {
        Queue<String> queue = new LinkedList<>();
        HashMap<Character, String> letterMappingTable = initLetterMappingTable();
        List<String> resArr = new ArrayList<>();

        for (int i = 0; i < digit.length(); i++) {
            String letters = letterMappingTable.get(digit.charAt(i));
            if (queue.size() == 0) {
                for (int k = 0; k < letters.length(); k++) {
                    queue.add(String.valueOf(letters.charAt(k)));
                }
            } else {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    String poll = queue.poll();
                    for (int j = 0; j < letters.length(); j++) {
                        queue.add(poll + letters.charAt(j));
                    }
                }
            }
        }

        for (String s : queue) {
            resArr.add(s);
        }

        return resArr;
    }

    public HashMap<Character, String> initLetterMappingTable() {
        HashMap<Character, String> letterMappingTable = new HashMap<>();
        letterMappingTable.put('2', "abc");
        letterMappingTable.put('3', "def");
        letterMappingTable.put('4', "ghi");
        letterMappingTable.put('5', "jkl");
        letterMappingTable.put('6', "mno");
        letterMappingTable.put('7', "pqrs");
        letterMappingTable.put('8', "tuv");
        letterMappingTable.put('9', "wxyz");

        return letterMappingTable;
    }
}
