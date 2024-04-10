package sperator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author :覃玉锦
 * @create :2024-03-30 14:55:00
 */
public class DelimiterServerHandler extends ChannelInboundHandlerAdapter {
    public static final String DELIMITER = "#%";

    private AtomicLong count = new AtomicLong(0);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        System.out.println("Server received:" + msg1.toString(CharsetUtil.UTF_8) + " count:" + count.incrementAndGet());

        String resp = "welcome," + msg1.toString(CharsetUtil.UTF_8) + DELIMITER;
        ctx.writeAndFlush(Unpooled.copiedBuffer(resp, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
