package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author :覃玉锦
 * @create :2021-01-05 10:44:00
 * 阻塞nio(这里是new io，用新的io接口来实现传统io通信方式,但是区别是数据传输基于缓冲区而不是流)
 * 演示了非阻塞的文件传输和阻塞的文件传输
 */
public class BlockingNewIOTest {

    @Test
    public void client() throws IOException {
        //1、获取通
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));

        //2、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取本地文件传输到远端
        while (inChannel.read(buf) != -1) {
            //切换为读取模式
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        //region 阻塞等待返回，去掉这段就不会阻塞了
        //不显示声明数据传输完毕的话，会导致阻塞
        sChannel.shutdownOutput();

        //4、接收服务端传回来的返回值
        while (sChannel.read(buf) != -1) {
            buf.flip();
            System.out.print(new String(buf.array(), 0, buf.limit()));
            buf.clear();
        }
        //endregion

        //5、通道关闭
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        //1、获取通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //指定端口
        ssc.bind(new InetSocketAddress(8899));
        //2、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取远程数据，写入本地文件
        SocketChannel sc = ssc.accept();
        while (sc.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //region 返回给客户端数据，也可以不返回
        //4、返回数据给客户端
        buf.put("数据传输完毕".getBytes());
        buf.flip();
        sc.write(buf);
        //endregion

        //5、关闭通道
        outChannel.close();
        ssc.close();
        sc.close();
    }
}
