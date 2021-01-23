/**
 * @author :覃玉锦
 * @create :2021-01-22 21:46:00
 */
public class Test {
    public static void main(String[] args) {
        String str = "asdfashelloasf";
        String temp = "fashiishell";

        System.out.println(maxSubstring(str, temp));
    }

    public static String maxSubstring(String str1, String str2) {
        String max = "", min = "", current = "";
        //根据长度得出max和min两个字符串
        if (str1.length() < str2.length()) {
            min = str1;
            max = str2;
        } else {
            min = str2;
            max = str1;
        }
        int len = min.length();

        //子串长度从最大的开始
        for (int i = 0; i < len; i++) {
            //end=len-i为子串的长度，是递减的，相当于从长度大的子串开始找起。begin和end同步移动，因为子串肯定是连续的
            //如果找到符合的子串就return。因为是长度递减的寻找，所以一定是找到最大的
            for (int begin = 0, end = len - i; end < len; begin++, end++) {
                current = min.substring(begin, end+1);
                if(max.contains(current)){
                    return current;
                }
            }
        }
        return null;
    }
}
