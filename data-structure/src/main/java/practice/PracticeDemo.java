package practice;

/**
 * @author :覃玉锦
 * @create :2020-12-23 13:26:00
 */
public class PracticeDemo {
    public static void main(String[] args) {
        MyStack stack = new MyStack(4);
        stack.push(0,1);
        stack.push(0,2);
        stack.push(1,3);
        stack.push(1,4);
        int pop = stack.pop(1);
        System.out.println(pop);
        stack.push(1,5);
        System.out.println();

        //AB*C*  
    }
}

class MyStack{
    private int maxSize;
    private int[] stack;
    private int[] top = new int[2];
    private int[] bot = new int[2];

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        bot[0] = -1;
        bot[1] = maxSize;
        top[0] = bot[0];
        top[1] = bot[1];
    }

    public boolean isEmpty(int i){
        return top[i] == bot[i];
    }

    public boolean isFull(){
        return top[0]+1==top[1] || top[0]==top[1]-1;
    }

    /**
     * 根据索引返回对应栈顶元素
     * @return
     */
    public int pop(int i){
        if(isEmpty(i)){
            return -1;
        }
        if(i==0){
            return stack[top[i]--];
        }
        else {
            return stack[top[i]++];
        }
    }

    public int peek(int i){
        if(!isEmpty(i)){
            return this.stack[top[i]];
        }
        return -1;
    }

    public void push(int i,int value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        if(i==0){
            stack[++top[i]] = value;
        }
        else {
            stack[--top[i]] = value;
        }
    }
}