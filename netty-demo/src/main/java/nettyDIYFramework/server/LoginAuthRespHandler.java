package nettyDIYFramework.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import nettyDIYFramework.util.SecurityCenter;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

import java.net.InetSocketAddress;

/**
 * @author :覃玉锦
 * @create :2024-04-08 19:13:00
 * 登录认证，包括重复登录校验、请求类型校验、ip白名单校验
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        //是否是登录请求
        if (null != message.getMyHeader()
                && message.getMyHeader().getType() == MessageType.LOGIN_REQ.value()) {
            System.out.println("接收到认证请求");

            String remoteAdr = ctx.channel().remoteAddress().toString();

            //重复登录校验
            if (SecurityCenter.isDupLog(remoteAdr)) {
                MyMessage retMsg = buildResp((byte) -1);
                System.out.println("重复登录,地址:" + remoteAdr);
                ctx.writeAndFlush(retMsg);
                ctx.close();
            } else {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = inetSocketAddress.getHostString();
                //ip白名单校验
                if (!SecurityCenter.isWhiteUser(ip)) {
                    MyMessage retMsg = buildResp((byte) -1);
                    System.out.println("请求不在白名单中");
                    ctx.writeAndFlush(retMsg);
                    ctx.close();
                } else {
                    SecurityCenter.addLoginUser(remoteAdr);
                    System.out.println(remoteAdr + ",认证通过");
                    MyMessage retMsg = buildResp((byte) 0);
                    ctx.writeAndFlush(retMsg);
                }
            }

            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String remoteAdr = ctx.channel().remoteAddress().toString();
        SecurityCenter.removeUser(remoteAdr);
        cause.printStackTrace();
        ctx.close();
    }

    public MyMessage buildResp(byte resCode) {
        MsgHeader header = new MsgHeader();
        header.setType(MessageType.LOGIN_RESP.value());
        MyMessage res = new MyMessage();
        res.setMyHeader(header);
        res.setBody(resCode);
        return res;
    }
}
