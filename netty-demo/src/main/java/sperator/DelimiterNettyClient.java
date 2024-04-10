package sperator;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-03-30 14:39:00
 */
public class DelimiterNettyClient {
    public static final String DELIMITER = "#%";

    //定长
    public static final String REQUEST = "hello netty.";

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
                            //回车换行符
//                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            socketChannel.pipeline().addLast(new LineBaseClientHandler());
                            //自定义
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(DELIMITER.getBytes())));
//                            socketChannel.pipeline().addLast(new DelimiterClientHandler());
                            //定长
                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(DelimiterNettyServer.RESPONSE.length()));
                            socketChannel.pipeline().addLast(new FixedLengthClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(new InetSocketAddress("127.0.0.1", 8899)).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
