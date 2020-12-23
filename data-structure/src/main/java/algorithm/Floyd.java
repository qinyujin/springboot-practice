package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-12-22 15:31:00
 * 弗洛伊德算法求最小路径
 * 和迪杰斯特拉的区别：弗洛伊德求得是每个顶点到其他顶点最短距离
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertexes = {'A','B','C','D','E','F','G'};
        final int N = 65535;
        int[][] matrix = new int[][]{
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}
        };

        FGraph graph = new FGraph(vertexes.length, matrix, vertexes);
        graph.show();
        graph.floyd();
        System.out.println("******************************************************          after floyd");
        graph.show();
    }
}

class FGraph{
    //顶点数组
    private char[] vertexes;
    //保存从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组
    private int[][] dis;
    //保存到达目标顶点的前驱顶点
    private int[][] pre;

    public FGraph(int length,int[][] matrix,char[] vertexes) {
        this.vertexes = vertexes;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                System.out.print(vertexes[pre[i][j]]+" ");
            }
            System.out.println();
            for (int j = 0; j < pre.length; j++) {
                System.out.print("("+vertexes[i]+"到"+vertexes[j]+"的最短路径是"+dis[i][j]+")");
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法计算各个顶点的所有最短路径
     */
    public void floyd(){
        int len;
        //中间结点
        for (int k = 0; k < dis.length; k++) {
            //出发结点
            for (int i = 0; i < dis.length; i++) {
                //终点
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k]+dis[k][j];
                    if(len < dis[i][j]){
                        dis[i][j] = len;
                        //前驱换成中间结点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}