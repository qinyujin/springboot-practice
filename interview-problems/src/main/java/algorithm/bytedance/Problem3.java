package algorithm.bytedance;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-03-07 22:12:00
 * 书放入书架和不能放入书架。并查集
 */
public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] books = new int[n][3];
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            //思考能否以o(n)来完成
            for (int j = 0; j < 3; j++) books[i][j] = sc.nextInt();
            //可以使用两个set表示书架1和书架2.按照逻辑来完成
            int num1 = books[i][1];
            int num2 = books[i][2];
            if(books[i][0]==1){
                //放入书架1
                //什么情况下不能放入书架1：当1或3有一个在书架2.
                if(set2.contains(num1) || set1.contains(num2)) {
                    count++;
                    break;
                }
                //如果可以放入1，就放入
                set1.contains(num1);
                set1.contains(num2);
            }
            else if(books[i][0] ==2){
                //书架2不能放入的情况：两个书都在书架1上，那么就不能放入书架2了
                if(set1.contains(num1) && set1.contains(num2)){
                    count++;
                    break;
                }
                //放入书架2，需要放入书架1没有的书，
                if(!set1.contains(num1))set2.add(num1);
                if(!set1.contains(num2))set2.add(num2);
            }
        }
        System.out.println(count);
    }
}
