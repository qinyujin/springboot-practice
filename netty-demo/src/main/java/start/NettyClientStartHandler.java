package start;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.codec.Charsets;

/**
 * @author :覃玉锦
 * @create :2024-03-29 20:11:00
 * SimpleChannelInboundHandler封装好了释放buf缓冲区的代码,节省工作
 */
public class NettyClientStartHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("client received server's returned msg:" + byteBuf.toString(Charsets.UTF_8));
        channelHandlerContext.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,netty", Charsets.UTF_8));
    }
}
