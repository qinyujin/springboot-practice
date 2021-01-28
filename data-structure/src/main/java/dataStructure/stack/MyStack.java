package dataStructure.stack;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-11-11 15:55:00
 * 栈，先入后出
 */
public class MyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出当前程序");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            key = scanner.next();
            switch (key){
                case "show":
                    myStack.show();
                    break;
                case "exit":
                    loop=false;
                    break;
                case "push":
                    System.out.println("请输入：");
                    int n = scanner.nextInt();
                    myStack.push(n);
                    break;
                case "pop":
                    System.out.println("栈顶元素："+myStack.pop());
                    break;
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }

    private int maxSize;
    private int[] stack =null;
    private int top=-1;//栈顶，默认为-1

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[maxSize];
    }

    public boolean isFull(){
        if(top==maxSize-1){
            return true;
        }
        else return false;
    }

    public boolean isEmpty(){
        if(top==-1){
            return true;
        }
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
        for (int i = 0; i <= top; i++) {
            System.out.println("第"+i+"号元素为："+stack[i]);
        }
    }
}