package nettyDIYFramework.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import nettyDIYFramework.util.AsyncBusiProcess;
import nettyDIYFramework.util.EncryptUtils;
import nettyDIYFramework.vo.MessageType;
import nettyDIYFramework.vo.MsgHeader;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-08 20:05:00
 */
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) throws Exception {
        //校验header的body的md5编码
        String headerMd5 = myMessage.getMyHeader().getMd5();
        String bodyMd5 = EncryptUtils.encryptObj(myMessage.getBody());
        if (!headerMd5.equals(bodyMd5)) {
            System.out.println("md5校验失败,headMD5:" + headerMd5 + ",bodyMD5:" + bodyMd5);
            MyMessage message = buildBusResp("md5校验失败");
            channelHandlerContext.writeAndFlush(message);
            channelHandlerContext.close();
        } else {
            //单工通信可以不用马上回复，这里可以异步+线程池执行任务
            if (myMessage.getMyHeader().getType() == MessageType.ONE_WAY.value()) {
                AsyncBusiProcess.submitTask(() -> {
                    System.out.println("模拟任务处理." + myMessage.getBody());
                });
            } else {
                //双工通信，给个回复
                System.out.println("双工通信，需要应答.收到消息:" + myMessage.getBody());
                channelHandlerContext.writeAndFlush(buildBusResp("ok"));
            }
        }
    }

    public MyMessage buildBusResp(String msg) {
        MsgHeader header = new MsgHeader();
        header.setType(MessageType.SERVICE_RESP.value());
        MyMessage myMessage = new MyMessage();
        myMessage.setMyHeader(header);
        myMessage.setBody(msg);

        return myMessage;
    }
}
