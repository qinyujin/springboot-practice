package shardable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-03-29 21:09:00
 */
public class EchoClientMC {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientMCHandler());
                        }
                    });

            ChannelFuture f = b.connect(new InetSocketAddress("127.0.0.1", 8899)).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
