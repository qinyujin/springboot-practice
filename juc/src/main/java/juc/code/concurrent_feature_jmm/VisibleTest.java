package juc.code.concurrent_feature_jmm;

/**
 * @author :覃玉锦
 * @create :2023-08-02 19:12:00
 * 可见性问题演示,refresh方法无法使线程1读取到flag值更新
 */
public class VisibleTest {
    private boolean flag = true;

    public void load() {
        System.out.println("线程1把flag加载到工作内存中");
        int count = 0;
        while (flag) {
            count++;
        }
        System.out.println("线程1读取到主内存中flag的变化");
    }

    public void refresh() {
        flag = false;
        System.out.println("线程2写flag的值到主内存");
    }

    public static void main(String[] args) throws InterruptedException {
        VisibleTest test = new VisibleTest();
        new Thread(() -> test.load()).start();

        Thread.sleep(1000);

        new Thread(() -> test.refresh()).start();
    }
}
