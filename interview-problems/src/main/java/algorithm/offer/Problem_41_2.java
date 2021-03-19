package algorithm.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :覃玉锦
 * @create :2021-01-27 20:05:00
 * 字符流中第一个不重复的字符
 */
public class Problem_41_2 {
    public static void main(String[] args) {
        Problem_41_2 fc = new Problem_41_2();
        char[] chars = "google".toCharArray();
        for (char aChar : chars) {
            fc.Insert(aChar);
        }
        System.out.println(fc.FirstAppearingOnce());
    }

    //用于记录每个字符出现的次数
    private int[] cCount = new int[256];
    private Queue<Character> queue = new LinkedList<>();
    public void Insert(char ch)
    {
        cCount[ch]++;
        if(cCount[ch] > 1){
            queue.remove(ch);
        }
        else {
            queue.add(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        return queue.isEmpty()? '#' : queue.peek();
    }
}
