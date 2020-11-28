package com.aimer.gateway;
/**
 * @author :覃玉锦
 * @create :2020-09-08 20:03:00
 */
public class Practice {
    public static void main(String[] args) {

        String regex = "[0-9]";
        String str = "/api/user/role/2";
        int i=1;
        String databasePath = str.replaceAll("\\d{1,}", "{id}");
        System.out.println("使用regex改造的地址："+databasePath);
    }
}
