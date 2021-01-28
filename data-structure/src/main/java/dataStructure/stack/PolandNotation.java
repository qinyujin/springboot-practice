package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2020-11-17 09:52:00
 * 逆波兰表达式（后缀表达式）的计算
 */
public class PolandNotation {
    public static void main(String[] args) {
        //(3+4)*5-6 使用空格分隔, 如果想做复杂点就把表达式解析为后缀表达式
     String exp = "1 2 3 + 4 * + 5 -";

     //把一个中缀表达式转换为后缀表达式，示例中缀 1+((2+3)*4)-5 转换为 123+4*+5-
        /*List<String> list = toList(exp);
        Integer culcu = culcu(list);
        System.out.println(culcu);*/

        String exp1 = "1+((3+2)*4)-5";
        String polandExp = toPolandExp(exp1);
        System.out.println("转换后的波兰表达式："+polandExp);
        List<String> polandList = toList(polandExp);
        System.out.println(culcu(polandList));
    }

    /**
     * 把中缀表达式转换为后缀表达式,简单写法，只支持一位数字，核心思想不变
     * @param midExp
     * @return
     */
    public static String toPolandExp(String midExp){
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        char[] chars = midExp.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]==')'){
                while (true){
                    if(s1.peek()=='('){
                        s1.pop();
                        break;
                    }
                    Character pop = s1.pop();
                    s2.push(pop);
                }
            }
            else if(chars[i]>='0' && chars[i]<='9'){
                s2.push(chars[i]);
            }
            else if(s1.isEmpty() || chars[i]=='(' || s1.peek()=='('){
                s1.push(chars[i]);
            }
            //如果操作符优先级高，入栈，如果上一个是左括号，入栈
            else if(getPrior(chars[i]) > getPrior(s1.peek())){
                s1.push(chars[i]);
            }
            else {
                Character pop = s1.pop();
                s2.push(pop);
                i--;
            }
        }
        while (true){
            if(s1.isEmpty())break;
            Character pop = s1.pop();
            s2.push(pop);
        }

        String result ="";
        while (true){
            if(s2.isEmpty())break;
            Character pop = s2.pop();
            if(s2.size()!=0)
            result+=pop+" ";
            else result+=pop;
        }
        StringBuilder sb = new StringBuilder(result);
        return sb.reverse().toString();
    }

    public static Integer getPrior(Character str){
        if(str.equals('+') || str.equals('-')){
            return 0;
        }
        else if(str.equals('*') || str.equals('/')){
            return 1;
        }
        else {
            throw new RuntimeException("操作符错误！");
        }
    }

    public static List<String> toList(String exp){
        List<String> list = new ArrayList<>();
        String[] s = exp.split(" ");
        for (String s1 : s) {
            list.add(s1);
        }
        return list;
    }

    public static Integer culcu(List<String> arr){
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            //正则表达式判断数字
            if(s.matches("\\d+")){
                //数字入栈
                stack.push(s);
            }
            else {
                if(s.equals("+")){
                    Integer n2 = Integer.parseInt(stack.pop());
                    Integer n1 = Integer.parseInt(stack.pop());
                    stack.push(""+(n1+n2));
                }
                else if(s.equals("-")){
                    Integer n2 = Integer.parseInt(stack.pop());
                    Integer n1 = Integer.parseInt(stack.pop());
                    stack.push(""+(n1-n2));
                }
                else if(s.equals("*")){
                    Integer n2 = Integer.parseInt(stack.pop());
                    Integer n1 = Integer.parseInt(stack.pop());
                    stack.push(""+(n1*n2));
                }
                else if(s.equals("/")){
                    Integer n2 = Integer.parseInt(stack.pop());
                    Integer n1 = Integer.parseInt(stack.pop());
                    stack.push(""+(n1/n2));
                }
                else throw new RuntimeException("操作符异常！");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
