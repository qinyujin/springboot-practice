package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 */
public class Practice {
    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        Practice practice = new Practice();
        int[] duplication = new int[1];
        practice.duplicate(arr,arr.length,duplication);
        System.out.println(duplication[0]);
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            if(visited[numbers[i]]){
                duplication[0] = numbers[i];
                return true;
            }
            visited[numbers[i]] = true;
        }
        return false;
    }
}
