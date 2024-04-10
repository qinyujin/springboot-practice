package sperator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author :覃玉锦
 * @create :2024-03-30 14:59:00
 */
public class DelimiterClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public static final String DELIMITER = "#%";

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String req = "yujin" + DELIMITER;
        for (int i = 0; i < 10; i++) {
            ByteBuf msg = Unpooled.buffer(req.length());
            msg.writeBytes(req.getBytes());
            ctx.writeAndFlush(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
