package algorithm.leetcode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-03-18 15:00:00
 * 除法求值
 * https://leetcode-cn.com/problems/evaluate-division/
 */
public class Problem_399 {
    public static void main(String[] args) {
        Problem_399 p = new Problem_399();
        List<List<String>> equations = new ArrayList<>();
        ArrayList<String> s1 = new ArrayList<>();
        s1.add("a");
        s1.add("b");
        ArrayList<String> s2 = new ArrayList<>();
        s2.add("b");
        s2.add("c");
        equations.add(s1);
        equations.add(s2);

        double[] values = {2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        ArrayList<String> q1 = new ArrayList<>();
        q1.add("a");
        q1.add("c");
        ArrayList<String> q2 = new ArrayList<>();
        q2.add("b");
        q2.add("a");
        ArrayList<String> q3 = new ArrayList<>();
        q3.add("a");
        q3.add("e");
        ArrayList<String> q4 = new ArrayList<>();
        q4.add("a");
        q4.add("a");
        ArrayList<String> q5 = new ArrayList<>();
        q5.add("x");
        q5.add("x");

        queries.add(q1);
        queries.add(q2);
        queries.add(q3);
        queries.add(q4);
        queries.add(q5);

        double[] doubles = p.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(doubles));
    }

    //并查集
    //equations = [["a","b"],["b","c"]], values = [2.0,3.0],
    //queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationSize);
        Map<String,Integer> hashMap = new HashMap<>(2 * equationSize);
        int id = 0;
        //第一步：预处理，把变量的值和id进行映射，就可以使用int数组来表示变量。
        for (int i = 0; i < equationSize; i++) {
            List<String> equation =  equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if(!hashMap.containsKey(var1)){
                hashMap.put(var1,id);
                id++;
            }
            if(!hashMap.containsKey(var2)){
                hashMap.put(var2,id);
                id++;
            }
            //把id作为合并的值，并且传入权值
            unionFind.union(hashMap.get(var1),hashMap.get(var2),values[i]);
        }
        //第2步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);
            if(id1==null || id2==null){
                res[i] = -1.0;
            }
            else {
                res[i] = unionFind.isConnected(id1,id2);
            }
        }
        return res;
    }

    //并查
    private class UnionFind{
        //例如parent[0] = 1表示0节点的父节点是1
        private int[] parent;

        //节点指向父节点的权值
        private double[] weight;

        //初始化，所有节点指向自己parent=i。所以权值也是1
        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        //合并两个id值，并且改变权值
        public void union(int x,int y,double value){
            int rootX = find(x);
            int rootY = find(y);
            //如果两个节点根节点一样，说明不需要合并
            if(rootX == rootY)return;

            //把x父节点指向y父节点并且更新root权值
            parent[rootX] = rootY;
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         * @param x
         * @return x的根节点
         */
        public int find(int x){
            if(x!=parent[x]){
                int origin = parent[x];
                //参数为父亲节点一直往上找
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            //如果联通则返回他们的权值比
            if(rootX==rootY){
                return weight[x] / weight[y];
            }
            else {
                return -1.0d;
            }
        }
    }
}
