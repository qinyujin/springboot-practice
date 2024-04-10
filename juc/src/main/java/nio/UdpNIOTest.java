package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-01-05 15:11:00
 * udp的非阻塞nio 客户端和服务端实现
 * datagramChannel的发送和接收：调用的是send 和 receive方法
 */
public class UdpNIOTest {

    @Test
    public void client() throws IOException {
        //udp协议的通道是数据报
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            buf.put((new Date().toString() + "\n"+next).getBytes());
            buf.flip();
            dc.send(buf,new InetSocketAddress("127.0.0.1",8899));
            buf.clear();
        }

        dc.close();
    }

    @Test
    public void server() throws IOException {
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);

        dc.bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        dc.register(selector, SelectionKey.OP_READ);
        while (selector.select()>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                //udp不需要建立连接，不用处理接收类型的key.
                if(sk.isReadable()){
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    //如果使用read方法，需要客户端调用connect建立连接,receive不需要,所以udp一般都用这个
                    dc.receive(buf);
                    buf.flip();
                    System.out.println(new String(buf.array(), 0, buf.limit()));
                    buf.clear();
                }
            }
            iterator.remove();
        }
        dc.close();;
    }
}
