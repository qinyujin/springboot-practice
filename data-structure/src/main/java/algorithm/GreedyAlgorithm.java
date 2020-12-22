package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-12-21 08:35:00
 * 贪心算法，每一步都选取当前最优解
 * 示例问题：集合覆盖问题
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String,HashSet<String>> broadcast = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcast.put("k1",hashSet1);
        broadcast.put("k2",hashSet2);
        broadcast.put("k3",hashSet3);
        broadcast.put("k4",hashSet4);
        broadcast.put("k5",hashSet5);

        ArrayList<String> allAreas = new ArrayList<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        ArrayList<String> greedy = greedy(broadcast, allAreas);
        System.out.println(greedy);
    }

    /**
     * 使用贪心算法得出可以覆盖所有地区的最少广播台,每次选取未选择地区最多的广播台
     * @param broadcast 广播台集合，形如： k1         "北京","上海","深圳"
     * @return 广播台集合
     */
    public static ArrayList<String> greedy(Map<String, HashSet<String>> broadcast,ArrayList<String> allAreas){
        HashSet<String> tempSet = new HashSet<>();
        ArrayList<String> selects = new ArrayList<>();
        String maxKey;
        while (allAreas.size() != 0){
            maxKey = null;
            for (String key : broadcast.keySet()) {
                tempSet.clear();
                //取出每一个广播对应地区
                tempSet.addAll(broadcast.get(key));
                //取出每一个广播地区和所有地区的交集，放入tempSet
                tempSet.retainAll(allAreas);
                //maxKey指向最大未覆盖地区的广播
                if(tempSet.size()>0 &&
                        (maxKey==null || tempSet.size() > broadcast.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //找到最大的坐标后，从areas中移除，添加到selects里
            selects.add(maxKey);
            allAreas.removeAll(broadcast.get(maxKey));
        }
        return selects;
    }
}
