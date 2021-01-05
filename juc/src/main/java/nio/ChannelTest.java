package nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-04 17:13:00
 * 一开始传输数据的方式是通过cpu给的io接口，应用程序调用io接口。后来改进为给内存分配一个DMA（相当于独立处理器）来处理io的
 * 操作，此时即旧的java io。再后来把DMA改成通道Channel。
 * 通道相当于铁路，数据传输则主要依靠于火车（缓冲区）
 *
 * java为Channel提供的主要接口有：
 * 1、fileChannel 用于读取、写入、映射和操作文件的通道。
 * 2、socketChannel 通过 TCP 读写网络中的数据。
 * 3、serverSocketChannel 可以监听新进来的 TCP 连接，对每一个新进来
 * 的连接都会创建一个 SocketChannel。
 * 4、datagramChannel 通过 UDP 读写网络中的数据通道。
 *
 * 获取通道：
 * 1、java对支持通道的类提供了getChannel（）方法
 *            本地io：
 *            fileInputStream/fileOutputStream
 *            RandomAccessFile
 *
 *            网络io：
 *            socket
 *            serverSocket
 *            datagramSocket
 *
 * 2、jdk1.7中的nio.2 FileChannel 针对各个通道提供了静态方法 open()
 * 3、jdk1.7中的nio.2 的files工具类的newByteChannel()
 *
 * 通道之间的数据传输
 * transferFrom（）
 * transferTo（）
 *
 * 分散读取和聚集写入：
 * 通道的的数据可以读取到多个缓冲区中，多个缓冲区的数据也可以写入到一个通道。通道的read、write的参数有buffer数组，传入
 * buffer数组即可
 */
public class ChannelTest {

    /**
     * 字符集
     */
    @Test
    public void test6() throws IOException {
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
    public void test5() throws IOException {
        RandomAccessFile in = new RandomAccessFile(new File("1.txt"),"r");
        RandomAccessFile out = new RandomAccessFile(new File("2.txt"),"rw");
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        ByteBuffer[] bufs = {buf1,buf2};
        //参数是buffer数组，分散读入两个buffer缓冲区
        inChannel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //聚集写，参数是buffer数组，则可以把缓冲区数组中的数据写道通道上
        outChannel.write(bufs);
        inChannel.close();
        in.close();
    }

    /**
     * 使用Files提供的通道newByteChannel
     */
    @Test
    public void test4() throws Exception{
        SeekableByteChannel inChannel = Files.newByteChannel(Path.of("1.zip"), StandardOpenOption.READ);
        SeekableByteChannel outChannel = Files.newByteChannel(Path.of("2.zip"), StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        //使用非直接缓冲区来进行传输
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf)!=-1){
            //开启读模式
            buf.flip();
            outChannel.write(buf);
            //写完之后清空缓存区
            buf.clear();
        }

        inChannel.close();
        outChannel.close();
    }

    /**
     * 通道之间通信，transferTo/From 相当于直接缓冲区
     */
    @Test
    public void test3() throws Exception{
        FileChannel inChannel = FileChannel.open(Path.of("1.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Path.of("2.zip"), StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        //由于是in，需要使用to把数据传送给out通道
//        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * 本地io通道复制文件（直接缓冲区方式）
     * 注意，文件的复制本身是很快的，但程序不会那么快就结束，原因是程序需要等gc回收无效引用之后才会停止。
     * 14625-2847
     * 内存占用85左右
     */
    @Test
    public void test2() throws Exception{
        long begin = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Path.of("1.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Path.of("2.zip"), StandardOpenOption.WRITE,
                StandardOpenOption.READ
                , StandardOpenOption.CREATE);

        //直接缓冲区
        //通过map映射拿到in和out的缓冲区
        MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //拿到缓冲区之后从in get，写道out里
        byte[] bytes = new byte[inBuf.limit()];
        inBuf.get(bytes);
        outBuf.put(bytes);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end-begin));
    }

    /**
     * 本地io通道实现复制文件，非直接缓冲区
     * 17768-11739
     * 内存占用 60左右
     */
    @Test
    public void test1() throws Exception {
        long begin = System.currentTimeMillis();
        //获取通道三种方法（1 fileInput/outputStream 的getChannel （2 FileChannel的open方法 （3 Files的newByteChannel方法
        FileInputStream fis = new FileInputStream("1.zip");
        FileOutputStream fos = new FileOutputStream("2.zip");

        //1、获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //2、配合缓冲区使用
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf)!=-1){
            //切换成读取模式
            buf.flip();
            outChannel.write(buf);
            //清空缓冲区
            buf.clear();
        }

        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end-begin));
    }
}
