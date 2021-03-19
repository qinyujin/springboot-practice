package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-21 11:19:00
 * 替换空格
 * https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 将一个字符串中的空格替换成 "%20"。
 */
public class Problem_5 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("A B");
        Problem_5 space = new Problem_5();
        System.out.println(space.replaceSpace(stringBuilder));
    }

    public String replaceSpace(StringBuilder str) {
        //牛客思路：采用填充思想来解决。先根据空格数来新开辟一个数组，然后从后往前遍历，如果遍历到空格则添加填充，如果没有遍历
        //到空格则正常填充

        if(str==null || str.length()<=0){
            return "";
        }
        //空格数量
        int count = 0,p1 = str.length()-1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') count++;
        }
        System.out.println(str.length());
        for (int i = 0; i < count; i++) {
            str.append("  ");
        }
        System.out.println(str.length());

        int p2 = str.length()-1;
        while (p1>=0 && p2>p1){
            char c = str.charAt(p1--);
            if(c==' '){
                str.setCharAt(p2--,'0');
                str.setCharAt(p2--,'2');
                str.setCharAt(p2--,'%');
            }
            else {
                str.setCharAt(p2--,c);
            }
        }
        return str.toString();
    }
}
