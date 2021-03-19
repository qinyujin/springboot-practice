package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 13:45:00
 * 任务调度器
 * https://leetcode-cn.com/problems/task-scheduler/
 */
public class Problem_621 {
    public static void main(String[] args) {
        Problem_621 p = new Problem_621();
        char[] tasks = {'A','A','A','B','B','B'}; //8
        System.out.println(p.leastInterval(tasks, 2));
    }

    // 桶思想找出规律
    // https://leetcode-cn.com/problems/task-scheduler/solution/tong-zi-by-popopop/
    // (公式是桶个数-1)*(n+1)+最后一行元素数
    public int leastInterval(char[] tasks, int n) {
        int[] temp = new int[26];
        int countMaxTask = 0;
        int maxTask=0;
        for(char c:tasks){
            temp[c-'A']++;
            //桶个数，设置为出现次数的最大值
            maxTask = Math.max(temp[c-'A'],maxTask);
        }
        for(int i=0;i<26;i++){
            if(temp[i]==maxTask){
                //最后一行的元素个数，如果一个任务的数量和桶个数一样，那么就说明它也占用最后一行
                countMaxTask++;
            }
        }
        return Math.max(tasks.length,(maxTask-1)*(n+1)+countMaxTask);
    }
}
