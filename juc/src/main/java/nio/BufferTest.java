package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author :覃玉锦
 * @create :2021-01-04 15:00:00
 * 缓冲区，nio中负责数据的存取。
 * 可以使用如ByteBuffer、CharBuffer。。。等来操作对应的缓冲区
 * 缓冲区的几个核心变量：
 * capacity：缓冲区最大的容量
 * limit：缓冲区能够操作的容量（limit之后都不能操作）
 * position：当前的位置，初始为0
 * mark：标记，配合reset使用，可以记录当前position位置，使用reset可以让position恢复。
 *
 * 缓冲区常用方法：
 * 1、put 存值，改变position
 * 2、flip 切换到读取模式，改变position、limit
 * 3、get 取值，改变position
 * 4、mark 记录position，配合reset使用
 * 5、reset 还原当前position到mark位置
 * 6、clear 清空缓冲区，但实质上只是变动指针，数据没动
 *
 * 两种缓冲区：
 * 1、非直接缓冲区：使用allocate分配的缓冲区。想要读取硬盘的数据，就得通过操作系统、应用程序两者之间的两个缓冲区，不论读写都得进行数据的复制，基于jvm
 * 内存进行操作
 * 2、直接缓冲区：使用allocateDirect分配的缓冲区去除掉了中间过程（中间商），不用再有数据的复制，直接在操作系统内存中操作。注意风险就是如果大量的使用了操作系统
 * 内存，可能会导致其他问题。
 * 区别：非直接缓冲区使用jvm的内存，速度较慢
 * 直接缓冲区使用操作系统内存，速度较快，有一定风险。
 */
public class BufferTest {

    @Test
    public void test1(){
        String str = "abcde";
        //1、给缓冲区分配容量
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("------------allocate()---------------");
        System.out.println("capacity:"+buf.capacity());
        System.out.println("limit:"+buf.limit());
        System.out.println("position:"+buf.position());

        //2、使用put存数据，存完后position指针会移动到对应位置
        buf.put(str.getBytes());
        System.out.println("------------put()---------------");
        System.out.println("capacity:"+buf.capacity());
        System.out.println("limit:"+buf.limit());
        System.out.println("position:"+buf.position());

        //3、使用flip切换到读取模式，这时候position还原到上一次的起点，limit到上一次的终点
        buf.flip();
        System.out.println("------------flip()---------------");
        System.out.println("capacity:"+buf.capacity());
        System.out.println("limit:"+buf.limit());
        System.out.println("position:"+buf.position());

        //4、使用get来读取数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println("------------get()---------------");
        System.out.println(new String(dst));
        System.out.println("capacity:"+buf.capacity());
        System.out.println("limit:"+buf.limit());
        System.out.println("position:"+buf.position());
    }

    @Test
    public void test2(){
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        System.out.println(buf.position());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println(buf.position());
        //读取了两个，当前position2，使用mark记录
        buf.mark();
        //又读取两个，当前position4，使用reset还原到2
        buf.get(dst,2,2);
        System.out.println(buf.position());

        buf.reset();
        System.out.println(buf.position());

        //clear，重置指针位置，但不改变数据
        buf.clear();
        System.out.println("------------------------");
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());
        System.out.println((char) buf.get());
    }

    @Test
    public void test3(){
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        //判断是否是直接缓冲区
        System.out.println(buf.isDirect());
    }
}
