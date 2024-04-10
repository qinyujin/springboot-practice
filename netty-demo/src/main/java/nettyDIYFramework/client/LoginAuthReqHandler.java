package nettyDIYFramework.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-08 21:21:00
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发送登录请求");
        ctx.writeAndFlush(buildLogReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        if (null != message.getMyHeader()
                && message.getMyHeader().getType() == MessageType.LOGIN_RESP.value()) {
            System.out.println("收到响应:" + message.getBody());
            byte body = (byte) message.getBody();
            if (body != 0) {
                System.out.println("登录失败");
                ctx.close();
            } else {
                System.out.println("登录成功,在pipeline中移除认证handler，后续不用再认证");
                ctx.channel().pipeline().remove(this);
            }
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public MyMessage buildLogReq() {
        MsgHeader header = new MsgHeader();
        header.setType(MessageType.LOGIN_REQ.value());
        MyMessage myMessage = new MyMessage();
        myMessage.setMyHeader(header);

        return myMessage;
    }
}
