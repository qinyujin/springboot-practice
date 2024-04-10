package shardable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author :覃玉锦
 * @create :2024-03-29 21:00:00
 */
public class EchoServerMCHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        System.out.println("Server received:" + msg1.toString(CharsetUtil.UTF_8));

        ctx.writeAndFlush(Unpooled.copiedBuffer("server received!", CharsetUtil.UTF_8));
    }
}
