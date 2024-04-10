package nettyDIYFramework.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-08 21:38:00
 */
public class ClientBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) throws Exception {
        System.out.println("业务应答消息:" + myMessage.toString());
    }
}
