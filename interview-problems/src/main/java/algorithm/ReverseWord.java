package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 19:49:00
 * 反转单词的序列
 * https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseWord {
    public static void main(String[] args) {
        ReverseWord reverseWord = new ReverseWord();
        String str = "nowcoder. a am I";
        System.out.println(reverseWord.ReverseSentence(str));
    }

    public String ReverseSentence(String str) {
        //可以先旋转每个单词，然后把整个串反转。例如 nowcoder. a am I 反转每个单词 .redocwon ma I 这样将整个字符串反转即可
        //先把每个单词都反转
        char[] chars = str.toCharArray();
        int i=0,j=0;
        while (j<=chars.length){
            //末尾元素或者空格
            while (j<=chars.length-1 && chars[j]!=' '){
                j++;
            }
            reverse(chars,i,j-1);
            i=j+1;
            j++;
        }
        //每个单词都反转之后反转整个串
        reverse(chars,0,chars.length-1);
        return new String(chars);
    }

    /**
     *
     * @param chars 原始数组
     * @param i  左边下标
     * @param j 右边下标
     */
    public void reverse(char[] chars,int i,int j){
        while (i<j){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
