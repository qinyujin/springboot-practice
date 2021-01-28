package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 13:17:00
 * 第一个只出现一次的字符
 * https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&tqId=11187&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 */
public class First1TimeStr {
    public static void main(String[] args) {
        System.out.println(new First1TimeStr().FirstNotRepeatingChar("google"));
    }

    public int FirstNotRepeatingChar(String str) {
        if(str==null || str.length()==0)return -1;
        //使用int数组来统计字符个数，最后把个数为1的拿出来
        int[] cCount = new int[256];
        for (int i = 0; i < str.length(); i++) {
            cCount[str.charAt(i)]++;
        }
        //统计完之后找到第一个即可
        for (int i = 0; i < str.length(); i++) {
            if(cCount[str.charAt(i)]==1){
                return str.indexOf(""+str.charAt(i));
            }
        }
        return -1;
    }
}
