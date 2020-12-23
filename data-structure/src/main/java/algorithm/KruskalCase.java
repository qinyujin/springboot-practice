package algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author :覃玉锦
 * @create :2020-12-21 14:47:00
 * 克鲁斯卡尔算法求最小生成树
 */
public class KruskalCase {
    //边的个数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //最大值表示两个顶点之间不能联通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix = new int[][]{
                      /*A   B   C   D   E  F  G*/
                /*A*/{  0, 12,INF,INF,INF, 16, 14},
                /*B*/{ 12,  0, 10,INF,INF,  7,INF},
                /*C*/{INF, 10,  0,  3,  5,  6,INF},
                /*D*/{INF,INF,  3,  0,  4,INF,INF},
                /*E*/{INF,INF,  5,  4,  0,  2,  8},
                /*F*/{ 16,  7,  6,INF,  2,  0,  9},
                /*G*/{ 14,INF,INF,INF,  8,  9,  0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs,matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    public KruskalCase( char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;

        for (int i = 0; i < matrix.length; i++){
            for (int j = i+1; j < matrix.length; j++) {
                //统计有效边个数
                if(matrix[i][j]!=INF){
                    this.edgeNum++;
                }
            }
        }
    }

    /**
     * 克鲁斯卡尔算法构建一个最小生成树，思路如下：
     * 1、把所有边按照权值进行排序
     * 2、从小到大构建一棵树
     * 3、如果构建的过程中出现了回路，则跳过该边
     *
     * 如何判断出现了回路：如果两个顶点的终点相同即判断为构成了回路
     */
    public void kruskal(){
        EData[] edges = getEdges();
        //1、排序所有的边
        sortEdges(edges);
        //存放最小生成树,形式：['A','B',2] ['B','C',4]
        EData[] results = new EData[edgeNum];
        int[] ends = new int[edgeNum];
        int index = 0;
        //2、从小到大开始取，判断是否出现回路
        for (int i = 0; i < edgeNum; i++) {
            //第一个顶点
            int v1 = getPosition(edges[i].start);
            //第二个顶点
            int v2 = getPosition(edges[i].end);

            //第一次返回的是E F对应的下标 m = 4 n = 5
            int m = getEnd(ends,v1);
            int n = getEnd(ends,v2);

            //判断是否形成回路，若没有形成回路，则n就是m的终点
            //没有形成回路
            if(m!=n){
                ends[m] = n;
                //这一条边加入最小生成树
                results[index++] = edges[i];
            }
        }

        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }

    public void print(){
        System.out.printf("邻接矩阵为：\n");
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                System.out.printf("%12d",this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 按照权值来排序边
     */
    public void sortEdges(EData[] edges){
        Arrays.sort(edges, new Comparator<EData>() {
            @Override
            public int compare(EData o1, EData o2) {
                return Integer.compare(o1.weight,o2.weight);
            }
        });
    }

    /**
     * 获取所有图中的所有边
     * @return
     */
    public EData[] getEdges(){
        EData[] edges = new EData[this.edgeNum];
        int index = 0;
        for (int i = 0; i < this.vertexs.length; i++) {
            for (int j = i+1; j < this.vertexs.length; j++) {
                if(this.matrix[i][j]!=INF){
                    edges[index++] = new EData(this.vertexs[i],this.vertexs[j],this.matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取顶点下标
     * @param i 顶点字符形式
     * @return 对应下标，找不到返回-1
     */
    public int getPosition(char i){
        for (int j = 0; j < this.vertexs.length; j++) {
            if(this.vertexs[j] == i){
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取顶点的终点
     * @param ends 终点数组，记录所有顶点对应的终点下标
     * @param i 顶点下标
     * @return 顶点终点的下标
     */
    public int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            //往下一个顶点开始找，直到找到最后一个联通的顶点，即找到了终点。
            i = ends[i];
        }
        return i;
    }
}

class EData{
    //边的一个点
    char start;
    //边的另一个点
    char end;
    //边的权值
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
