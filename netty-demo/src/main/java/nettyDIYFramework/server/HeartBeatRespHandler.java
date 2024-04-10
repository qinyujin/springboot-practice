package nettyDIYFramework.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import nettyDIYFramework.util.SecurityCenter;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-08 19:32:00
 * 配合ReadTimeoutHandler使用
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        if (null != message
                && message.getMyHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("收到心跳请求");
            MyMessage myMessage = buildHeartBeat();
            ctx.writeAndFlush(myMessage);
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //配合ReadTimeoutHandler中抛出的异常ReadTimeoutException进行处理
        if (cause instanceof ReadTimeoutException) {
            System.out.println("心跳超时");
            SecurityCenter.removeUser(ctx.channel().remoteAddress().toString());
            ctx.close();
        }
        super.exceptionCaught(ctx,cause);
    }

    public MyMessage buildHeartBeat() {
        MsgHeader header = new MsgHeader();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        MyMessage myMessage = new MyMessage();
        myMessage.setMyHeader(header);

        return myMessage;
    }
}
