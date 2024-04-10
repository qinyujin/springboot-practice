package sperator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author :覃玉锦
 * @create :2024-03-30 15:17:00
 */
public class FixedLengthClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("client Accept[" + byteBuf.toString(CharsetUtil.UTF_8) + "]");
    }

    /*** 客户端被通知channel活跃后，做事*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf msg = null;
        for (int i = 0; i < 10; i++) {
            msg = Unpooled.buffer(DelimiterNettyClient.REQUEST.length());
            msg.writeBytes(DelimiterNettyClient.REQUEST.getBytes());
            ctx.writeAndFlush(msg);
        }
    }

    /*** 发生异常后的处理*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
