package nio.pactice;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-04 19:46:00
 */
public class Practice {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 10; i++) {
           new Thread(ticket).start();
        }
    }
}

class Ticket implements Runnable{
//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static List<String> list =  new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
            list.add("AA");
        }
        System.out.println(list);
    }
}
