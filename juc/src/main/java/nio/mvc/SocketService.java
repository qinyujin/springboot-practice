package nio.mvc;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-05-07 10:13:00
 */
@Service
public class SocketService {

    public void client() throws IOException {
        //1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));

        //2、把网络通道设置为非阻塞状态
        sChannel.configureBlocking(false);

        //3、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //4、传个时间和输入内容给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println("您的输入：" + scanner);
            buf.put((new Date().toString() + "\n" + scanner).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        sChannel.close();
    }

    public void server() throws IOException {
        //1、获取通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //2、切换到非阻塞模式
        ssc.configureBlocking(false);

        //3、绑定连接
        ssc.bind(new InetSocketAddress(8899));

        //4、获取选择器
        Selector selector = Selector.open();

        //5、将通道注册到选择器上，指定“监听接收事件”
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //6、轮询获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //7、获取选择器的所有选择键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //8、获取准备就绪的事件
                SelectionKey key = iterator.next();

                //9、判断事件的具体情况
                if (key.isAcceptable()) {
                    //10、如果是接收类型，获取接收通道，设置非阻塞，注册到选择器
                    SocketChannel accept = ssc.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else {
                    //11、如果不是接收类型则是读取数据，获取
                    SocketChannel sChannel = (SocketChannel) key.channel();

                    //12、分配缓冲区
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    //13、读取数据
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        sChannel.write(buf);
                        buf.clear();
                    }
                }
                //14、取消选择键
                iterator.remove();
            }
        }
    }
}
