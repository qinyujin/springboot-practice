package nio.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author :覃玉锦
 * @create :2021-05-07 10:20:00
 */
@SpringBootTest
class IApplicationTest {
    @Autowired
    private SocketService socketService;

    @Test
    void test() throws IOException {
    }
}