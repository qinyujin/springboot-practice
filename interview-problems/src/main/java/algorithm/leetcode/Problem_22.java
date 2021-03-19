package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-04 12:58:00
 * 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 例：输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 输入：n = 1
 * 输出：["()"]
 */
public class Problem_22 {
    public static void main(String[] args) {
        Problem_22 p = new Problem_22();
        List<String> strings = p.generateParenthesis(3);
        System.out.println(strings);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrace(ans,new StringBuilder(),0,0,n);
        return ans;
    }

    /**
     *
     * @param ans 保存最终结果
     * @param cur 用于追加字符
     * @param open 左括号数量
     * @param close 右括号数量
     * @param max 传入的n，表示n对括号
     */
    public void backtrace(List<String> ans,StringBuilder cur,int open,int close,int max){
        //边界：当cur的长度达到max*2 即总括号数时
        if(cur.length() == max*2){
            ans.add(cur.toString());
            return;
        }
        //一半左括号
        if(open < max){
            cur.append('(');
            //继续下一个括号
            backtrace(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length()-1);
        }
        //为什么是小于open：一个右括号匹配一个左括号
        if(close < open){
            cur.append(')');
            backtrace(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}
