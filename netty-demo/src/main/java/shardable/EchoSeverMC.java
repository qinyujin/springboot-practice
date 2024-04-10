package shardable;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-03-29 20:56:00
 */
public class EchoSeverMC {
    public static final int PORT = 8899;

    public static void main(String[] args) throws InterruptedException {
        MessageCountHandler msgHandler = new MessageCountHandler();

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
                            //回车分割符
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //共享的handler不能每次都new一个，仅用一个实例即可
                            socketChannel.pipeline().addLast(msgHandler);
                            socketChannel.pipeline().addLast(new EchoServerMCHandler());
                        }
                    });

            //监听连接，sync阻塞到连接成功
            ChannelFuture f = b.bind(new InetSocketAddress("127.0.0.1", PORT)).sync();
            //futureClose.sync阻塞
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
