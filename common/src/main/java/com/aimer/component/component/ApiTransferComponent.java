package com.aimer.component.component;

/**
 * @author :覃玉锦
 * @create :2020-09-08 13:40:00
 */
public class ApiTransferComponent {
    public static void main(String[] args) {
        int[] arr = new int[]{34,5,22,-98,6,-76,0,-3};

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {

            }
        }
    }

    /*public static String transferPath(String reqPath){
        char[] chars = reqPath.toCharArray();
        int begin;
        int end;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]>='0' && chars[i]<='9'){
                begin=i;
                if((i+1)<=chars.length && chars[i+1]=='/'){
                    return null;
                }
            }
        }
        return null;
    }

    public static List<String> findNumSubString(String reqPath){
        char[] chars = reqPath.toCharArray();
        List<String> list = new ArrayList<>();
        String temp = new String();
        int num=0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]>='0' && chars[i]<='9'){
                temp+=chars[i];
                if((chars[i]>='0' && chars[i]<='9')&&chars[+1]=='/'){

                }
            }
        }
    }*/
}
