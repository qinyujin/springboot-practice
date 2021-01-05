package juc;

/**
 * @author :覃玉锦
 * @create :2020-12-29 23:07:00
 * CAS代码思路
 */
public class CompareAndSwapTest {
    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    //获取Value，有可能获取到不同的值
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = this.value;
        //预期值和当前值相等，交换
        if(oldValue==expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        //判断是否交换成功，如果没有交换成功则还是旧的值
        return expectedValue==compareAndSwap(expectedValue,newValue);
    }
}