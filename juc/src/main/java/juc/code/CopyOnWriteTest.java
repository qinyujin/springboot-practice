package juc.code;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author :覃玉锦
 * @create :2020-12-29 23:26:00
 * CopyOnWrite添加操作多时效率低，因为每次都会复制。并发迭代多时可以选择
 */
public class CopyOnWriteTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new CopyOnWriteDemo()).start();
        }
    }
}

class CopyOnWriteDemo implements Runnable{
    //这种集合不能迭代时添加/修改
//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    //copyOnwrite集合，写入后复制，在写入的时候会复制生成一个新的集合，并且在新集合上操作。
    private static List<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String next = it.next();
            System.out.println(next);

            list.add("AA");
        }
    }
}