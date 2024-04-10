package nettyDIYFramework.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;
import nettyDIYFramework.kryo.KryoDecode;
import nettyDIYFramework.kryo.KryoEncode;
import nettyDIYFramework.util.EncryptUtils;
import nettyDIYFramework.util.MakeMsgID;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-04-08 20:40:00
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            //连接写空闲检测
                            sc.pipeline().addLast(new CheckWriteIdleHandler());

                            //粘包半包
                            sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535
                                    , 0, 2, 0, 2));
                            sc.pipeline().addLast(new LengthFieldPrepender(2));

                            //序列化
                            sc.pipeline().addLast(new KryoDecode());
                            sc.pipeline().addLast(new KryoEncode());

                            //连接读空闲检测
                            sc.pipeline().addLast(new ReadTimeoutHandler(15));

                            //登录LoginAuthReqHandler、心跳HearBeatReqHandler、业务ClientBusiHandler
                            sc.pipeline().addLast(new LoginAuthReqHandler());
                            sc.pipeline().addLast(new HearBeatReqHandler());

                        }
                    });

            ChannelFuture f = b.connect(new InetSocketAddress("127.0.0.1", 8899)).sync();
            System.out.println("成功建立连接");

            sendBusiMsg(f);

            f.channel().closeFuture().sync();
        } finally {
            if (group != null) {
                group.shutdownGracefully();
            }
        }
    }

    /**
     * 发送业务数据
     *
     * @param f
     */
    private static void sendBusiMsg(ChannelFuture f) {
        String sendMsg = "v";

        MyMessage msg = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setMsgID(MakeMsgID.getID());
        msgHeader.setType(MessageType.SERVICE_REQ.value());
        msgHeader.setMd5(EncryptUtils.encryptObj(sendMsg));
        msg.setMyHeader(msgHeader);
        msg.setBody(sendMsg);
        f.channel().writeAndFlush(msg);
    }
}
