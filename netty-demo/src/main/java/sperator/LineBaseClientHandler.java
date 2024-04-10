package sperator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author :覃玉锦
 * @create :2024-03-30 14:40:00
 */
public class LineBaseClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String msg = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("Client received:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "yujin" + System.getProperty("line.separator");//结尾加上回车分割符
        for (int i = 0; i < 10; i++) {
            ByteBuf buffer = Unpooled.buffer(msg.length());
            buffer.writeBytes(msg.getBytes());
            ctx.writeAndFlush(buffer);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
