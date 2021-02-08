import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2021-01-22 21:46:00
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        String json="{\"a\":{\"b\":[\"v\",2,{\"c\":0}]},\"d\":[1,null,3]}";
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> hashMap1 = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        Object[] objects={1,null,3};
        hashMap1.put("c",0);
        Object[] objects1={"v",2,hashMap1};
        hashMap2.put("b",objects1);
        hashMap.put("a",hashMap2);
        hashMap.put("d",objects);
    /*我爱了 我爱了 3年java开发能写这个也得思考一会吧
    这题来考一个大学没毕业的写java的码农 给谁谁不迷糊？？？211 985不敢说 普本不百度 有一个人写出来 我送他一根头发...（等我不百度这么强了...我还有头发吗...）
    我准备梳理梳理在我博客里给详解了...关注我博客园哦 小王只会写bug
    * */
        System.out.println(showMap(hashMap));
    }

    public static Set<String> showMap(Map<String, Object> map) {
        //TODO your code goes here...
        int a=0;
        HashSet<String> wangxuan = new HashSet<>();
        for (String s : map.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s);
            Object obj = map.get(s);
            if (obj instanceof String || obj instanceof Number){
                stringBuilder.append("=").append(obj);
                wangxuan.add(stringBuilder.toString());
            }else if(obj instanceof Object[]){
                showArray(wangxuan, stringBuilder, (Object[]) obj);
            }else {
                showMap(wangxuan,stringBuilder,(Map<String, Object>) obj);
            }

            a++;
        }
        return wangxuan;
    }

    private static void showArray(HashSet<String> strings, StringBuilder Jianzao, Object[] obj) {
        Object[] objects= obj;
        String str="";
        for (int i = 0; i < objects.length; i++) {
            str=Jianzao.toString();
            Jianzao.append("[").append(i).append("]");
            Object object = objects[i];
            if (object instanceof String || object instanceof Number || object==null){
                Jianzao.append("=").append(object);
                strings.add(Jianzao.toString());
            }else if (object instanceof Map){
                showMap(strings, Jianzao, (Map<String, Object>) object);
            }else {
                showArray(strings,Jianzao,(Object[]) object);
            }
            Jianzao=new StringBuilder(str);
        }
    }

    private static void showMap(HashSet<String> strings, StringBuilder stringBuilder, Map<String, Object> hashMap) {
        for (String ss : hashMap.keySet()) {
            stringBuilder.append(".").append(ss);
            Object object = hashMap.get(ss);
            if (object instanceof String || object instanceof Number){
                stringBuilder.append("=").append(object);
                strings.add(stringBuilder.toString());
            }else if (object instanceof Object[]){
                showArray(strings,stringBuilder,(Object[]) object);
            }else{
                showMap(strings,stringBuilder,(Map<String, Object>) object);
            }
        }
    }
}
