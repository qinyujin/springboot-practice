package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-12-21 11:50:00
 * 普利姆算法，求最小生成树算法之一。
 * 本例使用一个图来举例，最小连通图（每个顶点都连接，并且权值是整体最小的）
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new  char[]{'A','B','C','D','E','F','G'};
        int vertexs = data.length;
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MGraph graph = new MGraph(vertexs);
        MinTree tree = new MinTree();
        tree.createGraph(graph,vertexs,data,weight);
        tree.showGraph(graph);
        System.out.println("开始构建最小生成树：");
        tree.prim(graph,0);
    }
}

/**
 * 最小生成树
 */
class MinTree {
    public void createGraph(MGraph graph,int vertexs,char data[],int[][] weight){
        for (int i = 0; i < vertexs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertexs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法得到最小生成树
     * @param graph 图
     * @param v 顶点下标
     */
    public void prim(MGraph graph,int v){
        int minWeight;
        //标记是否访问过，这里可以理解为顶点集
        boolean[] visited = new boolean[graph.vertexs];
        visited[v] = true;
        //两个顶点下标
        int h1=-1,h2=-1;
        //每次确定一条边，该边为权值最小边,n个顶点只有n-1条边，所以这里从1开始
        for (int k = 1; k < graph.vertexs; k++) {
            minWeight = 10000;

            //i代表访问过的结点，j代表未访问过的结点
            for (int i = 0; i < graph.vertexs; i++) {
                for (int j = 0; j < graph.vertexs; j++) {
                    //找出最小边
                    if(visited[i] && !visited[j] && minWeight > graph.weight[i][j]){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //找到了最小边
            System.out.println("顶点"+graph.data[h1]+"到顶点"+graph.data[h2]+" 权值："+minWeight);
            visited[h1] = true;
            visited[h2] = true;
        }
    }
}

class MGraph{
    int vertexs;
    char[] data;
    //图的邻接矩阵，值为对应边的权值
    int[][] weight;

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}