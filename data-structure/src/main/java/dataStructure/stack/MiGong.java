package dataStructure.stack;

/**
 * @author :覃玉锦
 * @create :2020-11-20 14:25:00
 * 迷宫回溯、递归问题
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        initMap(map);
        System.out.println("迷宫：");
        printMap(map);
//        map[6][5]为终点位置
        setWay(map, 1, 1);
        System.out.println("路线：");
        printMap(map);

        //求最短路径：提供不同的策略，计算二位数组中2最少的策略即最短路径
    }

    public static void initMap(int[][] map){
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
    }

    public static void printMap(int[][] map){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 模拟找路线，1代表墙，2代表走过的路，3代表走不通
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        //走通
        if(map[6][5]==2){
            return true;
        }
        //下 右 左 上
        else {
            if(map[i][j]==0){
                map[i][j]=2;
                //继续走
                if(setWay(map, i+1, j)){
                    return true;
                }
                else if(setWay(map, i, j+1)){
                    return true;
                }
                else if (setWay(map,i-1 , j)){
                    return true;
                }
                else if(setWay(map, i,j-1 )){
                    return true;
                }
                //上下左右都不行，因此这个位置走不通
                else {
                    map[i][j]=3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
