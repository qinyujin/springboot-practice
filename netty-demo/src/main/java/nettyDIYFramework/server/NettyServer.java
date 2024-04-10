package nettyDIYFramework.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;
import nettyDIYFramework.kryo.KryoDecode;
import nettyDIYFramework.kryo.KryoEncode;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-04-07 20:53:00
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            //性能监控,可选
//                            sc.pipeline().addLast(new MetricsHandler());

                            //粘包半包问题
                            sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            sc.pipeline().addLast(new LengthFieldPrepender(2));

                            //序列化
                            sc.pipeline().addLast(new KryoDecode());
                            sc.pipeline().addLast(new KryoEncode());

                            //心跳.15秒没有就抛异常ReadTimeoutException
                            sc.pipeline().addLast(new ReadTimeoutHandler(15));

                            //业务handler,登录LoginAuthRespHandler、心跳HeartBeatRespHandler、业务ServerBusiHandler
                            sc.pipeline().addLast(new LoginAuthRespHandler());
                            sc.pipeline().addLast(new HeartBeatRespHandler());
                            sc.pipeline().addLast(new ServerBusiHandler());
                        }
                    });

            ChannelFuture f = b.bind(new InetSocketAddress("127.0.0.1", 8899)).sync();
            f.channel().closeFuture().sync();
        } finally {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }

    }
}
