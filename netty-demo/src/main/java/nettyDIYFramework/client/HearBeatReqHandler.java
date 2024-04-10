package nettyDIYFramework.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-08 21:31:00
 */
public class HearBeatReqHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //idleStateHandler监听写空闲，发相应事件
        if (evt == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT) {
            System.out.println("发送心跳请求");
            ctx.writeAndFlush(buildHeartBeatReq());
        }

        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        /*是不是心跳的应答*/
        if (message.getMyHeader() != null
                && message.getMyHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("收到服务器心跳应答，服务器正常");
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {
            System.out.println("服务器长时间无应答，关闭连接");
            ctx.close();
        }

        super.exceptionCaught(ctx, cause);
    }

    public MyMessage buildHeartBeatReq() {
        MsgHeader header = new MsgHeader();
        header.setType(MessageType.HEARTBEAT_REQ.value());

        MyMessage message = new MyMessage();
        message.setMyHeader(header);

        return message;
    }
}
