package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author :覃玉锦
 * @create :2021-01-05 15:26:00
 * 管道是单向的，作用范围是线程之间.通常用于两线程之间单向传输
 * Pipe.SinkChannel   负责发送数据
 * Pipe.SourceChannel 负责接收数据
 */
public class PipeTest {
    @Test
    public void test1() throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("通过管道发送数据".getBytes());
        buf.flip();
        sink.write(buf);

        Pipe.SourceChannel source = pipe.source();
        source.read(buf);
        buf.flip();
        System.out.println(new String(buf.array(), 0, buf.limit()));

        sink.close();
        source.close();
    }
}
