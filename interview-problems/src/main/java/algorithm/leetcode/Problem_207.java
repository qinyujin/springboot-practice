package algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :覃玉锦
 * @create :2021-03-12 19:13:01
 * 课程表
 * https://leetcode-cn.com/problems/course-schedule/
 *
 * 需要构造一个有向图，并且结合顶点指向的边集合、顶点入度等来判断
 */
public class Problem_207 {
    public static void main(String[] args) {
        Problem_207 p = new Problem_207();
        int numCourses = 2;
        int[][] prerequisites = {
                {1,0},
//                {0,1}
        };
        boolean b = p.canFinish(numCourses, prerequisites);
        System.out.println(b);
    }

    //存储边，例如0 ->{1,2,3} 表示0顶点指向1，2，3
    List<List<Integer>> edges;
    //顶点入度，例如 indeg[0] == 2 说明有两个点指向0
    int[] indeg;

    //入度为0说明不需要先修课程或者先修已经修完了。那么可以加入队列即学习本门课程
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        //初始化边信息和各顶点入度信息
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        //利用队列来完成广搜
        Queue<Integer> queue = new LinkedList<Integer>();
        //先行课程的入度是0
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        //符合的节点数
        int visited = 0;
        while (!queue.isEmpty()) {
            //相当于当前课程已经修完了，出队列，并且统计次数
            ++visited;
            int u = queue.poll();
            //获取当前节点的所有边，即指向的节点0
            for (int v: edges.get(u)) {
                //维护该节点的入度
                --indeg[v];
                //如果该节点的入度为0，加入到队列
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        //如果所有的顶点都出队列，说明都修读了。
        return visited == numCourses;
    }
}
