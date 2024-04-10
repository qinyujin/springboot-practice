package sperator;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-03-30 14:38:00
 * 回车换行符、自定义分割符、定长分割符
 */
public class DelimiterNettyServer {
    //自定义分割符
    public static final String DELIMITER = "#%";

    //定长
    public static final String RESPONSE = "welcome to neety's world!";

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            //负责处理连接，只需要一个线程
            bossGroup = new NioEventLoopGroup(1);
            //处理读写，可以多线程
            workerGroup = new NioEventLoopGroup();

            //启动入口类
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // childHandler是因为需要用到serverSocketChannel产生的socketChannel来读写
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //回车换行符
//                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            socketChannel.pipeline().addLast(new LineBaseSeverHandler());
                            //自定义分割符
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(DELIMITER.getBytes())));
//                            socketChannel.pipeline().addLast(new DelimiterServerHandler());
                            //定长分割
                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(DelimiterNettyClient.REQUEST.length()));
                            socketChannel.pipeline().addLast(new FixedLengthServerHandler());
                        }
                    });

            //监听连接，sync阻塞到连接成功
            ChannelFuture f = b.bind(new InetSocketAddress("127.0.0.1", 8899)).sync();
            //futureClose.sync阻塞
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
