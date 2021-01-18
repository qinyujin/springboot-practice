package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author :覃玉锦
 * @create :2021-01-05 10:44:00
 * 阻塞的io，网络io
 */
public class BlockingNIOTest {

    /*----------------------不带反馈的------------------------*/


    @Test
    public void client1() throws IOException {
        //1、获取通道，网络通道-传输 本地io通道-读取文件
        //客户端网络通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8899));
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //2、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取本地文件，通过网络通道进行传输
        while (inChannel.read(buf)!=-1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        //4、关闭通道
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void server1() throws IOException {
        //1、获取通道，同样是本地io通道和网络通道，服务端使用serversocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        //通道绑定端口
        ssc.bind(new InetSocketAddress(8899));
        //接收数据
        SocketChannel accept = ssc.accept();
        //2、创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (accept.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //3、关闭通道
        outChannel.close();
        ssc.close();
        accept.close();
    }

    /*----------------------带反馈的------------------------*/
    @Test
    public void client2() throws IOException {
        //1、获取通道
        FileChannel inChannel = FileChannel.open(Path.of("1.jpg"),StandardOpenOption.READ);
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8899));

        //2、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取本地文件传输到远端
        while (inChannel.read(buf)!=-1){
            //切换为读取模式
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        //不显示声明数据传输完毕的话，会导致阻塞
        sChannel.shutdownOutput();

        //4、接收服务端传回来的返回值
        while (sChannel.read(buf)!=-1){
            buf.flip();
            System.out.print(new String(buf.array(), 0, buf.limit()));
            buf.clear();
        }

        //5、通道关闭
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void server2() throws IOException {
        //1、获取通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Path.of("2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
          //指定端口
        ssc.bind(new InetSocketAddress(8899));
        //2、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取远程数据，写入本地文件
        SocketChannel sc = ssc.accept();
        while (sc.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //4、返回数据给客户端
        buf.put("数据传输完毕".getBytes());
        buf.flip();
        sc.write(buf);

        //5、关闭通道
        outChannel.close();
        ssc.close();
        sc.close();
    }
}
