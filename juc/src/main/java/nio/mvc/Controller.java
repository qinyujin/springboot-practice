package nio.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author :覃玉锦
 * @create :2021-05-07 10:23:00
 */
@RestController
public class Controller {
    @Autowired
    private SocketService socketService;

    @GetMapping("server")
    public void chatServer(){
        try {
            socketService.server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("client")
    public void chatClient(){
        try {
            socketService.client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
