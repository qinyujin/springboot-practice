package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-12-21 19:48:00
 * 迪杰斯特拉算法求解最短路径问题 图论
 * 可以求出某一个顶到到其他顶点的最短路径
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] vertexes = new char[]{'A','B','C','D','E','F','G'};
        //表示不可以连接
        final int N = 65535;
        int[][] matrix = new int[][]{
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}
        };

        Graph graph = new Graph(vertexes,matrix);
        graph.showGraph();
        graph.dijkstra(6);
        graph.showDijkstra();
    }
}

class Graph{
    //顶点
    private char[] vertexes;
    //邻接矩阵
    private int[][] matrix;

    private VisitedVertex vv;

    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void showDijkstra(){
        vv.show();
    }

    public void showGraph(){
        for (int[] link : this.matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法
     * @param index 出发点
     */
    public void dijkstra(int index){
        vv = new VisitedVertex(vertexes.length,index);
        //处理第一个结点
        update(index);
        //处理剩下的结点
        for (int i = 1; i < vertexes.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新index顶点下标到周围顶点的距离和周围顶点的前驱结点
     * @param index
     */
    private void update(int index){
        int len;
        //取到每一行的顶点，即某个顶点的所有邻接顶点
        for (int j = 0; j < vertexes.length; j++) {
            //len 是从出发点到index的距离和从index到j的距离之和
            len = vv.getDis(index) + matrix[index][j];
            //如果index顶点没有被访问过并且index对应的距离大于len，就更新dis数组和前驱数组
            if(!vv.in(j) && vv.getDis(j) > len){
                //把下一层的前驱更新为index
                vv.updatePre(j,index);
                //更新dis
                vv.updateDis(j,len);
            }
        }
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex{
    //记录各个顶点是否访问过，1访问过，0未访问过，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个结点的下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * @param length 有多少个顶点
     * @param index 从哪个顶点开始
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis,65535);
        //出发点视为已访问
        this.already_arr[index] = 1;
        //除了它自己，其他的距离初始化为最大
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return this.already_arr[index] == 1;
    }

    /**
     * 更新距离
     * @param index 哪一个顶点
     * @param len 更新为多少
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre结点的前驱为index
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 获取出发点到顶点距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问结点，比如G完后，就是A点作为新的访问结点
     * @return
     */
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            //在未访问的结点中找到值最小的
            if(already_arr[i]==0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //该结点标记为已访问，并且返回回去
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        char[] vertex = new char[]{'A','B','C','D','E','F','G'};
        System.out.println("already_arr:");
        for (int i : already_arr) {
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("pre_visited:");
        for (int i : pre_visited) {
            System.out.print(i+" ");
        }
        System.out.println();

        int index = 0;
        System.out.println("dis:");
        for (int di : dis) {
            System.out.print(di+"("+vertex[index++]+")"+" ");
        }
        System.out.println();
    }
}
