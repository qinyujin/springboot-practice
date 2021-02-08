package algorithm;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-01-25 19:28:00
 * 判断出栈序列是否可能
 * https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class Problem_31 {
    public static void main(String[] args) {
        int[] push = {1,2,3,4,5};
        int[] pop = {4,3,5,1,2};
        System.out.println(new Problem_31().IsPopOrder(push, pop));
    }

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        //1,2,3,4,5 -> 4,5,3,2,1    1,2,3,4,5 -> 4,3,5,1,2
        //如何才算符合：按照出栈的序列来入栈
        if(pushA.length==0 || popA.length==0){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            //把A的全部入栈判断，入一个判断一个
            stack.push(pushA[i]);
            while (!stack.isEmpty() && popA[index] == stack.peek()){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}
