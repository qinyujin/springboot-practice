package algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-04 09:56:00
 * 电话号码字符组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 拿小灵通按键手机来举例。2有abc字符，3有def字符。。。一直到9有wxyz。现在从[2,9]之间取数字组合，求所有字符组合。
 * 如2,3 即[abc][def]所有字符的两个组合，有ad,ae,af,bd.....
 */
public class Problem_17 {
    public static void main(String[] args) {
        Problem_17 p = new Problem_17();
        List<String> strings = p.letterCombinations("23");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) System.out.print(iterator.next() + " ");
    }

    /**
     * 全排列变种。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if(digits==null || digits.length()==0)return combinations;
        HashMap<Character, String> map = new HashMap<>() {
            {
                put('2', "abc");
            }

            {
                put('3', "def");
            }

            {
                put('4', "ghi");
            }

            {
                put('5', "jkl");
            }

            {
                put('6', "mno");
            }

            {
                put('7', "pqrs");
            }

            {
                put('8', "tuv");
            }

            {
                put('9', "wxyz");
            }
        };
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
        //1、边界条件的判断。如果index已经达到了位置说明完成了拼接。
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        //2、逻辑处理，遍历每一个字符串，同时在每一步都往下递归
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
}
