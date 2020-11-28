package dataStructure;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-11-06 14:25:00
 */
public class MyQueue {
    private int maxSize;//maxSize-1 为最多数据数
    private int front;  //队首，第一个元素的下标，初始值为0
    private int rear;   //队尾，最后一个元素的后一位，留一位为了规范，初始值为0
    int[] arr;

    public MyQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满！");
            return;
        }
        arr[rear]=n;
        rear = (rear+1)%maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            System.out.println("队列为空！");
            return -1;
        }
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空！");
            return;
        }
        for (int i = front; i < front+size(); i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public int headQueue(){
        if(isEmpty())return -1;
        return arr[front];
    }

    public int size(){
        //这一步操作主要为了取绝对值，因为差值有可能为负数
        return (rear +maxSize- front)%maxSize;
    }
}

class QueueTest{
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("输入s展示队列数据");
            System.out.println("输入a添加数据进入队列");
            System.out.println("输入g从队列中取数据");
            System.out.println("输入h查看队列首的数据");
            System.out.println("输入e退出");
            char in = scanner.next().charAt(0);
            switch (in){
                case 's':
                    System.out.println("队列数据：");
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入添加的数：");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    int v= queue.getQueue();
                    System.out.println("数据："+v);
                    break;
                case 'h':
                    System.out.println("队首数据：");
                    System.out.println(queue.headQueue());
                    break;
                case 'e':
                    loop=false;
                    break;
            }
        }
    }
}