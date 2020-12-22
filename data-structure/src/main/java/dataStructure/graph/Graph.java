package dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author :覃玉锦
 * @create :2020-12-16 09:07:00
 * 图论
 */
public class Graph {
        //顶点集合
        private ArrayList<String> vertexes = new ArrayList<>();
        //边集合
        private int[][] edges;
        //边数量
        private int numOfEdges;
        //记录顶点是否访问过
        private boolean[] isVisited;

    public static void main(String[] args) {
//        int n = 5;
        int n = 8;
//        String[] vertexes = {"A","B","C","D","E"};
        String[] vertexes = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(n);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        /*graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);*/

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println("广度优先");
        graph.bfs();
    }

    public Graph(int n) {
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public void initIsVisited(){
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
    }

    /**
     * 一个结点的广度优先遍历
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited,int i){
        int u;
        int w;
        LinkedList queue = new LinkedList();
        System.out.print(getVertexByIndex(i)+"=>");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = (Integer)queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w!=-1){
                if(!isVisited[w]){
                    System.out.print(getVertexByIndex(w)+"=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }

    /**
     * 所有结点都广度
     */
    public void bfs(){
        initIsVisited();
        for (int i = 0; i < vertexes.size(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
        System.out.println();
    }

    /**
     * 深度优先遍历 DeepFirstSearch
     * 对于一个图中的结点来说，按照结点->结点第一个邻接点->结点第一个邻接点的第一个邻接点来遍历。结合图来看就是深度优先
     * 以二维数组来举例，这个方法就是遍历一行。
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited,int i){
        //输出当前结点
        System.out.print(getVertexByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        //能找到第一个邻接点
        while (w!=-1){
            //未遍历过
            if(!isVisited[w]){
                //把邻接点作为基结点继续去递归
                dfs(isVisited, w);
            }
            //遍历过，往它下一个邻接点走
            w = getNextNeighbor(i,w);
        }
    }

    /**
     * 把五个结点全部遍历
     * 以列的角度来遍历
     */
    public void dfs(){
        initIsVisited();
        for (int i = 0; i < vertexes.size(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
        System.out.println();
    }

    /**
     * 找到v1行的从v2开始的下一个邻接点
     * @param v1 行
     * @param v2 列
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexes.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 找index行的第一个邻接点
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexes.size(); i++) {
            //找到第一个邻接点
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据下标返回顶点
     * @param i
     * @return
     */
    public String getVertexByIndex(int i){
        return vertexes.get(i);
    }

    /**
     * 根据v1、v2返回权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 用二位数组形式展示当前的图
     */
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 插入一个顶点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexes.add(vertex);
    }

    /**
     * 插入一条边
     * @param v1 顶点1的下标
     * @param v2 顶点2的下标
     * @param weight 插入的值
     */
    public void insertEdge(int v1,int v2,int weight){
        //无向图，一条边记录两个顶点
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
