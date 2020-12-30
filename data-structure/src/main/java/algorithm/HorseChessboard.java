package algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author :覃玉锦
 * @create :2020-12-22 17:15:00
 * 马踏棋盘算法
 * 棋盘上给一个马棋子初始位置，让马走完整个棋盘，求这个过程
 */
public class HorseChessboard {
    //棋盘行数
    private static int X;
    //棋盘列数
    private static int Y;
    //记录棋盘某个位置是否已经走过
    private static boolean[] visited;
    //判断棋盘是否走完
    private static boolean finished;
    public static void main(String[] args) {
        X = 6;
        Y = 6;
        int row = 2;
        int column = 5;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X*Y];
        long begin = System.currentTimeMillis();
        traversalChessboard(chessboard,row,column,1);
        long end = System.currentTimeMillis();
        System.out.println("优化耗费时间；"+(end-begin));
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 贪心优化思路：每一次有多种选择，在这些选择中，我们按照下一步选择个数来排序，从小的开始
     * @param ps
     */
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取噢o1下一步所有位置个数
                int count1 = next(o1).size();
                //o2位置个数
                int count2 = next(o2).size();
                if(count1<count2){
                    return -1;
                }
                else if(count1==count2) {
                return 0;
                }
                else {
                    return 1;
                }
            }
        });
    }

    /**
     * 马踏棋盘算法
     * 1、将当前位置设置为已访问，然后根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中ArrayList，最多有8个位置，每走一步step+1
     * 2、遍历集合中所有能走的位置，如果可以走通就继续，走不通就回溯
     * 3、判断马儿是否完成了任务，使用step和应该走的步数对比，没有到达数量则表示没有完成任务，将整个棋盘置0
     * @param chessboard 棋盘，二维数组形式
     * @param row 马初始行位置
     * @param column 马初始列
     * @param step 步数
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        //记录当前位置已访问
        visited[row*X+column] = true;
        //下一步可以走就走
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);
            if(!visited[p.y*X + p.x]){
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //走完了，判断是否真的完成
        if(step<X*Y && !finished){
            chessboard[row][column] = 0;
            visited[row*X + column] = false;
        }
        else {
            finished = true;
        }
    }

    /**
     * 求出当前位置的所有可以走的位置集合
     * @param cur 当前位置
     * @return 可走的集合
     */
    public static ArrayList<Point> next(Point cur){
        ArrayList<Point> points = new ArrayList<>();

        Point p1 = new Point();
        //5位置
        if((p1.x = cur.x-2)>=0 &&(p1.y = cur.y-1)>=0){
            points.add(new Point(p1));
        }

        //6位置
        if((p1.x = cur.x-1)>=0 &&(p1.y = cur.y-2)>=0){
            points.add(new Point(p1));
        }

        //7位置
        if((p1.x = cur.x+1)<X &&(p1.y = cur.y-2)>=0){
            points.add(new Point(p1));
        }

        //0位置
        if((p1.x = cur.x+2)<X &&(p1.y = cur.y-1)>=0){
            points.add(new Point(p1));
        }

        //1位置
        if((p1.x = cur.x+2)<X &&(p1.y = cur.y+1)<Y){
            points.add(new Point(p1));
        }

        //2位置
        if((p1.x = cur.x+1)<X &&(p1.y = cur.y+2)<Y){
            points.add(new Point(p1));
        }

        //3位置
        if((p1.x = cur.x-1)>=0 &&(p1.y = cur.y+2)<Y){
            points.add(new Point(p1));
        }

        //4位置
        if((p1.x = cur.x-2)>=0 &&(p1.y = cur.y+1)<Y){
            points.add(new Point(p1));
        }
        return points;
    }
}


