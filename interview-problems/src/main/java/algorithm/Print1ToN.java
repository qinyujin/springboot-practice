package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 15:23:00
 */
public class Print1ToN {
    public static void main(String[] args) {
        Print1ToN pt = new Print1ToN();
        pt.print1ToMaxOfNDigits(3);
    }

    public void print1ToMaxOfNDigits(int n) {
        char[] numbers = new char[n];
        print1ToMaxOfNDigits(numbers,0);
    }

    /**
     *
     * @param numbers 字符串存储数组
     * @param digit 位数
     */
    public void print1ToMaxOfNDigits(char[] numbers,int digit){
        //终止条件：位数达到最高
        if(digit == numbers.length){
            printNumber(numbers);
            return;
        }
        for (int i = 0; i < 10; i++) {
            numbers[digit] = (char)(i+'0');
            //压入栈中，最低位在栈顶，最先把1-9打印
            print1ToMaxOfNDigits(numbers,digit+1);
        }
    }

    public void printNumber(char[] numbers){
        int index = 0;
        //不打印0
        while (index<numbers.length && numbers[index] =='0'){
            index++;
        }
        while (index<numbers.length){
            System.out.print(numbers[index++]);
        }
        System.out.println();
    }
}
