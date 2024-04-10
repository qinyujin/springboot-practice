package nettyDIYFramework.client;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author :覃玉锦
 * @create :2024-04-08 21:12:00
 */
public class CheckWriteIdleHandler extends IdleStateHandler {

    public CheckWriteIdleHandler() {
        //三个参数分别是读、写、读写。这里只关注写只用传第二个参，设置成超时时间15秒的一半
        super(0, 8, 0);
    }
}
