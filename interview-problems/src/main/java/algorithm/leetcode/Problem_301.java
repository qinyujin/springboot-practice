package algorithm.leetcode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-03-19 23:06:00
 * 删除无效的括号
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 */
public class Problem_301 {
    public static void main(String[] args) {
        Problem_301 p = new Problem_301();
        System.out.println(p.removeInvalidParentheses("()())()"));
    }

    //bfs
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if(s==null)return res;

        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        //用于跳出循环
        boolean found = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            //对每一个字符串单独搜索。没找到就删除字符串中每一个括号再次进行搜索.同时借助set去重处理
            for (int i = 0; i < size; i++) {
                String front = queue.poll();
                if(isValid(front)){
                    res.add(front);
                    found = true;
                }

                int frontLen = front.length();
                char[] charArray = front.toCharArray();
                for (int j = 0; j < frontLen; j++) {
                    //只对括号处理
                    if(charArray[j] != '(' && charArray[j] != ')')continue;
                    //把j的字符拿走
                    String next = new String(charArray,0,j) + new String(charArray,j+1,frontLen-j-1);
                    if(!visited.contains(next)){
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            //要求删除最少数量的括号，因此一旦搜索到了符合条件的就可以退出
            if(found)break;
        }
        return res;
    }

    public boolean isValid(String s){
        char[] chars = s.toCharArray();
        int count = 0;
        for (char c : chars) {
            if(c=='('){
                count++;
            }
            else if(c==')')count--;
            //左括号严格小等于右括号
            if(count < 0)return false;
        }
        return count == 0;
    }
}
