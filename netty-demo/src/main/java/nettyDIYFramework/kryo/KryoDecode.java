package nettyDIYFramework.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import nettyDIYFramework.vo.MyMessage;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2024-04-07 21:03:00
 */
public class KryoDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        MyMessage message = (MyMessage) KryoSerializer.deserialize(byteBuf);
        list.add(message);
    }
}
