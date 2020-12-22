package algorithm;

/**
 * @author :覃玉锦
 * @create :2020-12-16 16:22:00
 * 分治。
 * 汉诺塔游戏，有三个底座，需要把A底座的所有盘移动到C盘，并且小的不能放在大的上面
 * 分解思路：不论多少个盘子，都可以分解成基础的两步：
 * 1、把上面的盘子全部从A堆移动到B堆。
 * 2、把最底部的盘子从A移动到C堆
 * 3、把B堆移动到C堆
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(4,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c){
        //只有一个盘
        if(num ==1){
            System.out.println("把1个盘从"+a+"=>"+c);
        }
        else {
            //1、把A的最上面移到B
            hanoiTower(num-1,a,c,b);
            //2、把A移动到C
            System.out.println("把"+(num)+"个盘"+"从"+a+"=>"+c);
            //3、把B的移动到C
            hanoiTower(num-1,b,a,c);
        }
    }
}
