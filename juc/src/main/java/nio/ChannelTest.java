package nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-04 17:13:00
 * 一开始传输数据的方式是通过cpu给的io接口，应用程序调用io接口。后来改进为给内存分配一个DMA（相当于独立处理器）来处理io的
 * 操作，此时即旧的java io。再后来把DMA改成通道Channel。
 * 通道相当于铁路，数据传输则主要依靠于火车（缓冲区）
 * <p>
 * java为Channel提供的主要接口有：
 * 1、fileChannel 用于读取、写入、映射和操作文件的通道。
 * 2、socketChannel 通过 TCP 读写网络中的数据。
 * 3、serverSocketChannel 可以监听新进来的 TCP 连接，对每一个新进来
 * 的连接都会创建一个 SocketChannel。
 * 4、datagramChannel 通过 UDP 读写网络中的数据通道。
 * <p>
 * 获取通道：
 * 1、java对支持通道的类提供了getChannel（）方法
 * 本地io：
 * fileInputStream/fileOutputStream
 * RandomAccessFile
 * 网络io：
 * socket
 * serverSocket
 * datagramSocket
 * 2、jdk1.7中的nio.2 FileChannel 针对各个通道提供了静态方法 open()
 * 3、jdk1.7中的nio.2 的files工具类的newByteChannel()
 * <p>
 * 通道支持的直接内存方式：
 * map方式:FileChannel的map方法可以获取map映射
 * sendFile方式:通过调方法 transferFrom()（服务端）、transferTo()（客户端）
 * <p>
 * 分散读取和聚集写入：
 * 可以用多个buffer来和一个channel使用。通道的read、write的参数有buffer数组，传入buffer数组即可
 */
public class ChannelTest {

    /**
     * 字符集
     */
    @Test
    public void charBuffer() throws IOException {
        Charset c = Charset.forName("GBK");
        //获取编码器和解码器
        CharsetEncoder ce = c.newEncoder();
        CharsetDecoder cd = c.newDecoder();

        CharBuffer cb = CharBuffer.allocate(1024);
        cb.put("我爱中国！");
        //对字符数据进行编码，先切换成读取模式，再编码可获得字节缓冲区
        cb.flip();
        ByteBuffer bBuf = ce.encode(cb);

        System.out.println(Arrays.toString(bBuf.array()));

        //解码，对字节缓冲区数据进行解码
        CharBuffer bBuf1 = cd.decode(bBuf);

        //相同字符集解码成功
        System.out.println(bBuf1.toString());

        //不同字符集出现乱码
        Charset c2 = Charset.forName("UTF-8");
        CharBuffer cb2 = c2.decode(bBuf);
        System.out.println(cb2.toString());
    }

    /**
     * 分散读取和聚集写入
     */
    @Test
    public void randomAccessFile() throws IOException {
        RandomAccessFile in = new RandomAccessFile(new File("1.txt"), "r");
        RandomAccessFile out = new RandomAccessFile(new File("2.txt"), "rw");
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        ByteBuffer[] bufs = {buf1, buf2};
        //参数是buffer数组，分散读入两个buffer缓冲区
        inChannel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        //聚集写，参数是buffer数组，则可以把缓冲区数组中的数据写道通道上
        outChannel.write(bufs);
        inChannel.close();
        in.close();
    }

    /**
     * fileChannel的直接缓冲区sendFile，使用方法transferTo/From实现
     */
    @Test
    public void fileChannelDirectBuffer_sendFile() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("1.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.zip"), StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        //零拷贝-sendFile模式
//        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * fileChannel使用直接缓冲区的map方法
     * 注意，文件的复制本身是很快的，但程序不会那么快就结束，原因是程序需要等gc回收无效引用之后才会停止。
     * 14625-2847
     * 内存占用85左右
     */
    @Test
    public void fileChannelDirectBuffer_map() throws Exception {
        long begin = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.zip"), StandardOpenOption.WRITE,
                StandardOpenOption.READ
                , StandardOpenOption.CREATE);

        //直接缓冲区
        //零拷贝-map映射模式
        MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        System.out.println("isDirect:" + inBuf.isDirect());
        //拿到缓冲区之后从in get，写道out里
        byte[] bytes = new byte[inBuf.limit()];
        inBuf.get(bytes);
        outBuf.put(bytes);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - begin));
    }

    /**
     * fileChannel采用jvm缓冲区
     * 17768-11739
     * 内存占用 60左右
     */
    @Test
    public void fileChannelJvmBuffer() throws Exception {
        long begin = System.currentTimeMillis();
        //获取通道三种方法（1 fileInput/outputStream 的getChannel （2 FileChannel的open方法 （3 Files的newByteChannel方法
        FileInputStream fis = new FileInputStream("1.zip");
        FileOutputStream fos = new FileOutputStream("2.zip");

        //1、获取通道
        //getChannel方式
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //newByteChannel方式
        /*SeekableByteChannel inChannel = Files.newByteChannel(Paths.get("1.zip"), StandardOpenOption.READ);
        SeekableByteChannel outChannel = Files.newByteChannel(Paths.get("2.zip"), StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);*/

        //open方法的方式参考fileChannelDirectBuffer_map

        //2、配合缓冲区使用
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //文件数据写入到缓冲区中
        while (inChannel.read(buf) != -1) {
            //切换成读取模式
            buf.flip();
            //从缓冲区中写道out通道里,也就是写入到硬盘
            outChannel.write(buf);
            //清空缓冲区
            buf.clear();
        }

        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - begin));
    }
}
