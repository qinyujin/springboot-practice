package nettyDIYFramework.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import nettyDIYFramework.vo.MyMessage;

/**
 * @author :覃玉锦
 * @create :2024-04-07 21:03:00
 */
public class KryoEncode extends MessageToByteEncoder<MyMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyMessage myMessage, ByteBuf byteBuf) throws Exception {
        KryoSerializer.serialize(myMessage, byteBuf);
        channelHandlerContext.flush();
    }
}
