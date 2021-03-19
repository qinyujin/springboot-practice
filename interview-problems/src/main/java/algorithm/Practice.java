package algorithm;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 * 第二题记得非法判断
 */
public class Practice {
    public static void main(String[] args) {
        Practice p = new Practice();
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

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();
        UnionFind unionFind = new UnionFind(2 * equationsSize);
        HashMap<String,Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        //把字符映射为id来进行并查集判断，合并各个字符
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            if(!hashMap.containsKey(var1)){
                hashMap.put(var1,id++);
            }
            if(!hashMap.containsKey(var2)){
                hashMap.put(var2,id++);
            }
            unionFind.union(hashMap.get(var1),hashMap.get(var2),values[i]);
        }

        int queriesSize = queries.size();
        //查询
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);
            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);
            if(id1 ==null || id2==null){
                res[i] = -1.0d;
            }
            else {
                res[i] = unionFind.isConnected(id1,id2);
            }
        }
        return res;
    }

    private class UnionFind{
        private int[] parent;
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        //合并
        public void union(int x,int y,double value){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)return;
            parent[rootX] = rootY;
            weight[rootX] = value * weight[y] / weight[x];
        }

        //查找到x的父节点id值
        public int find(int x){
            if(x != parent[x]){
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return weight[x] / weight[y];
            }
            else {
                return -1.0d;
            }
        }
    }
}
