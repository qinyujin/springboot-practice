package algorithm;

/**
 * @author :覃玉锦
 * @create :2020-12-17 16:14:00
 * kmp字符串匹配算法
 */
public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] kmpNext = kmpNext(str2);
        int i = kmpSearch(str1, str2, kmpNext);
        System.out.println("字串下标："+i);
    }

    /**
     * kmp搜索算法
     * @param str1 源字符串
     * @param str2 字串
     * @param next kmp部分匹配表
     * @return 字串在str1中的下标，找不到返回-1
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j=0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                //同时i++;
                j++;
            }
            if(j==str2.length()){
                //返回在str1中的对应下标
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串的部分匹配表
     * @param dest 字串
     * @return 返回字串部分匹配值
     */
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        //i 和 j 代表两个下标，用来比较两个字符串
        for (int i = 1,j=0; i < dest.length(); i++) {
            //j大于0且不等，使用j = next[j-1]公式，kmp核心公式
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }

            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
