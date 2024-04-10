package shardable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * @author :覃玉锦
 * @create :2024-03-29 21:09:00
 */
public class EchoClientMCHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int count = new Random().nextInt(10) + 1;
        //分割符.如 "hello/n"
        String request = "hello" + System.getProperty("line.separator");
        System.out.println("发送次数：" + count);
        for (int i = 0; i < count; i++) {
            ByteBuf buffer = Unpooled.buffer(request.length());
            buffer.writeBytes(request.getBytes());
            ctx.writeAndFlush(buffer);
        }
    }
}
