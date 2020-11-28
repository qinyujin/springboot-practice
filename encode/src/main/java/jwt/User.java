package jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-09-10 09:37:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uid;
    private int workId;
    private List<Integer> rids;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        String s = timeRask();
        System.out.println(s);
    }

    public static String timeRask(){
        if((System.currentTimeMillis() - Long.parseLong("1599729411307"))>1000*60){
            return "超时！";
        }
        else return "未超时";
    }
}
