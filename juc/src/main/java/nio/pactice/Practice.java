package nio.pactice;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-01-04 19:46:00
 * <p>
 * 45
 */
public class Practice {
    //1、本地通道实现文件的复制（非直接缓冲区或直接缓冲区）
    @Test
    public void test1() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        //1、非直接缓冲区实现文件的复制
        /*while (inChannel.read(buf)>0) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }*/

        //2、直接缓冲区实现文件的复制
        //2.1 内存映射文件来实现
        /*MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        outBuf.put(inBuf);*/

        //2.2 通道间传输数据
        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel,0,inChannel.size());
    }

    //网络通道实现数据的传输 阻塞nio
    @Test
    public void client1() throws IOException {
        //1、tcp通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的姓名:");
        String name = scanner.next();
        while (scanner.hasNext()) {
            String next = scanner.next();
            buf.put((new Date().toString() + "\n" + name + ":" + next).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.close();
    }

    @Test
    public void server1() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(8899));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel accept = ssChannel.accept();
        while (accept.read(buf) > 0) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, buf.limit()));
            buf.clear();
        }
        ssChannel.close();
        accept.close();
    }

    @Test
    public void clent2() throws IOException {
        //tcp 非阻塞nio
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        sChannel.configureBlocking(false);
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("请输入您的姓名:");
        String name = scanner.next();
        while (scanner.hasNext()) {
            String next = scanner.next();
            buf.put((new Date().toString() + "\n" + name + ":" + next).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        sChannel.close();
    }

    @Test
    public void server2() throws IOException {
        //非阻塞接收
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress(8899));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                if (sk.isAcceptable()) {
                    SocketChannel accept = ssChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else {
                    SocketChannel sc = (SocketChannel) sk.channel();
                    //读取数据
                    while (sc.read(buf) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, buf.limit()));
                        buf.clear();
                    }
                }
                iterator.remove();
            }
        }

        ssChannel.close();
    }

    @Test
    public void client3() throws IOException {
        //udp nio非阻塞
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        ByteBuffer buf =ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的姓名:");
        String name = scanner.next();
        while (scanner.hasNext()){
            String next = scanner.next();
            buf.put((new Date().toString() + "\n" + name + ":" + next).getBytes());
            buf.flip();
            dChannel.send(buf,new InetSocketAddress("127.0.0.1",8899));
            buf.clear();
        }

        dChannel.close();
    }

    @Test
    public void server3() throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        dChannel.bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        dChannel.register(selector,SelectionKey.OP_READ);
        //udp是一次性全部接收
        ByteBuffer buf = ByteBuffer.allocate(1024*1024);
        while (selector.select()>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if(sk.isReadable()){
                    dChannel.receive(buf);
                    buf.flip();
                    System.out.println(new String(buf.array(), 0, buf.limit()));
                }
            }
            iterator.remove();
        }

        dChannel.close();
    }
}
