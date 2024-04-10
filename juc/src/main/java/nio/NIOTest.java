package nio;

import org.junit.Test;

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
 * @create :2021-01-05 11:32:00
 * 非阻塞式io nio的点：
 * 1、通道 Channel 负责连接或者读写的事
 *        java.nio.channels.Channel 接口
 *             |--SelectableChannel
 *             |--SocketChannel
 *             |--ServerSocketChannel
 *             |--DatagramChannel
 *
 *             |--Pipe.SinkChannel
 *             |--Pipe.SourceChannel
 *
 * 2、缓冲区 buffer 读取数据都在这个部分进行了，也就是专门用来数据操作的部分
 *
 * 3、选择器 Selector 关注各种事件(需要通过注册来添加事件)，并且通知到channel，让channel负责处理
 */
public class NIOTest {

    @Test
    public void client() throws IOException {
        //1、获取通道
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));

        //2、把网络通道设置为非阻塞状态
        sc.configureBlocking(false);

        //3、分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //4、传个时间和输入内容给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println("您的输入：" + next);
            buf.put((new Date().toString() + "\n" + next).getBytes());
            buf.flip();
            sc.write(buf);
            buf.clear();

        }

        sc.close();
    }

    @Test
    public void server() throws IOException {
        //1、初始化通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //2、切换到非阻塞模式
        ssc.configureBlocking(false);

        //3、绑定端口
        ssc.bind(new InetSocketAddress(8899));

        //4、初始化选择器
        Selector selector = Selector.open();

        //5、ServerSocketChannel主要管理连接事务，所以注册连接事件，表示关心客户端连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //6、返回有多少channel就绪，会阻塞到至少有一个。就绪的意思指的是对应channel是否准备好处理相应的io事件
        while (selector.select() > 0) {
            //7、拿到selectKey来准备处理这些事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //8、获取准备就绪的事件
                SelectionKey key = iterator.next();

                //9、连接类型的事件
                if (key.isAcceptable()) {
                    //10、如果是连接类型，获取接收通道，设置非阻塞，注册到选择器
                    //建立连接，通过serverSocket创建的一个socket来管读写
                    SocketChannel sc = ssc.accept();
                    System.out.println("已建立连接");
                    sc.configureBlocking(false);
                    //已经建立了连接，只需要关注读事件
                    sc.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    //11、如果不是接收类型则是读取数据，获取
                    SocketChannel sc = (SocketChannel) key.channel();

                    //12、分配缓冲区
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    //13、把数据从通道写到buffer里，sc.read并不是读取到sc里，而是读取到buf里
                    while ((len = sc.read(buf)) > 0) {
                        //切换模式
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));

                        //准备进行下一次读取
                        buf.clear(); // 清空所有缓冲区
//                        buf.compact(); // 清空已读取数据
                    }
                }
                //14、取消选择键,Selector不会自动移除selectKey，不手动处理会导致重复
                iterator.remove();
            }
        }
    }
}
