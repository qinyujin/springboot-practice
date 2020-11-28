package com.meiju;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-10-09 17:29:00
 */
public class MFunction {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String next = input.next();
        int inputWeek = Integer.parseInt(next);
        System.out.println(inputWeek);
        Week[] values = Week.values();

        System.out.println(values[inputWeek-1]);
        printWeek(values[inputWeek-1]);
    }

    static void printWeek(Week week){
        switch (week){
            case Friday:
                System.out.println("周五");
                break;
            case Monday:
                System.out.println("周一");
                break;
            case Sunday:
                System.out.println("周天");
                break;
            case Saturday:
                System.out.println("周天");
                break;
            case Thursday:
                System.out.println("周四");
                break;
            case Wednesday:
                System.out.println("周三");
                break;
            case baiTuesday:
                System.out.println("周二");
                break;
            default:
                System.out.println("错误输入!");
                break;
        }
    }
}
