package dataStructure.stack;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-11-11 17:26:00
 * 使用栈实现计算器功能
 */
public class Culculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表达式：");
        String exp = scanner.next();
        NumStack numStack = new NumStack(4);
        OpeStack opeStack = new OpeStack(4);
        int culcu = culcu(exp,numStack,opeStack);
        System.out.println("计算结果为："+culcu);
    }

    /**
     * 简单表达式栈计算，限制一位数，没有括号，四个运算符
     * @param exp
     * @return
     */
    public static int culcu(String exp,NumStack numStack,OpeStack opeStack){
        System.out.println("传入的表达式为："+exp);
        int size=exp.length();
        char[] chars = exp.toCharArray();
        for (int i = 0; i < size; i++) {
            //数字
            if(chars[i]>='0' && chars[i]<='9'){
                numStack.push(chars[i]-'0');
            }
            //操作符
            else {
                //遇到优先级较低的，计算后入栈
                if(chars[i]=='+' || chars[i]=='-'){
                    if(opeStack.getTop()!=null && (opeStack.getTop()=='*' || opeStack.getTop()=='/')){
                        //取两个数字、一个操作符运算，结果入数字栈,操作完后把优先级低的操作符入栈
                        int n2 = numStack.pop();
                        int n1 = numStack.pop();
                        Character op1 = opeStack.pop();
                        if(op1.equals('/')){
                            numStack.push(n1/n2);
                            opeStack.push(chars[i]);
                            continue;
                        }
                        else {
                            numStack.push(n1 * n2);
                            opeStack.push(chars[i]);
                            continue;
                        }
                    }
                }
                opeStack.push(chars[i]);
            }
        }
        //数栈还有
        int result =0;
        while (!numStack.isEmpty()){
            if(!opeStack.isEmpty()){
                int n2 = numStack.pop();
                int n1 = numStack.pop();
                Character opt = opeStack.pop();
                if(opt.equals('-')){
                    numStack.push(n1-n2);
                }
                else {
                    numStack.push(n2+n1);
                }
            }
            //只剩一个数出结果
            if(numStack.getTop()==0)
            result= numStack.pop();
        }
        return result;
    }
}

class NumStack{
    private int maxSize;
    private int[] stack;
    private int top=-1;

    public int getTop(){
        return top;
    }

    public NumStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        if(top==maxSize-1)return true;
        return false;
    }

    public boolean isEmpty(){
        if(top==-1)return true;
        return false;
    }

    public void push(int n){
        if(isFull()){
            System.out.println("栈已满！");
            return;
        }
        top++;
        stack[top]=n;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("栈空！");
            return -1;
        }
        int rtValue = stack[top];
        top--;
        return rtValue;
    }

    public void show(){
        if(isEmpty()){
            System.out.println("栈空！");
            return;
        }
        System.out.println("数栈:");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i]+" ");
        }
        System.out.println();
    }
}

class OpeStack{
    private int maxSize;
    private char[] stack;
    private int top=-1;

    public OpeStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new char[maxSize];
    }

    public boolean isFull(){
        if(top==maxSize-1)return true;
        return false;
    }

    public boolean isEmpty(){
        if(top==-1)return true;
        return false;
    }

    public Character pop(){
        if(isEmpty()){
            System.out.println("栈空！");
            return null;
        }
        char rtValue = stack[top];
        top--;
        return rtValue;
    }

    /**
     * 获取栈顶元素，不pop
     * @return
     */
    public Character getTop(){
        if(isEmpty()){
            System.out.println("栈空！");
            return null;
        }
        return stack[top];
    }

    public void push(char n){
        if(isFull()){
            System.out.println("栈已满！");
            return;
        }
        top++;
        stack[top]=n;
    }

    public void show(){
        if(isEmpty()){
            System.out.println("栈空！");
            return;
        }
        System.out.println("操作栈:");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i]+" ");
        }
        System.out.println();
    }
}