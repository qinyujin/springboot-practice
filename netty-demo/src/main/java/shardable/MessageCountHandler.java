package shardable;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author :覃玉锦
 * @create :2024-03-29 20:51:00
 * 增加shardable注解可以让该handler在不同channel中共享，当然也要主要线程安全的处理，所以这里采用的的atomatic原子类来保证线程安全
 */
@ChannelHandler.Sharable
public class MessageCountHandler extends ChannelDuplexHandler {

    private AtomicLong inCount = new AtomicLong(0);

    private AtomicLong outCount = new AtomicLong(0);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到报文数:" + inCount.incrementAndGet());
        super.channelRead(ctx, msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发出报文数:" + outCount.incrementAndGet());
        super.flush(ctx);
    }
}
