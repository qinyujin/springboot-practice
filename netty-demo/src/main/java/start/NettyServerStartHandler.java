package start;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.codec.Charsets;

/**
 * @author :覃玉锦
 * @create :2024-03-29 20:03:00
 * 有入站和出战两种handler.InBound是入站的
 */
public class NettyServerStartHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        System.out.println("server received msg:" + message.toString(Charsets.UTF_8));

        //返回给客户端数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("server have successful received data!", Charsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
