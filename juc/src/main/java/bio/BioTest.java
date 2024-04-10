package bio;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author :覃玉锦
 * @create :2024-03-20 19:30:00
 * java bio通信编程.通过socket和serverSocket来进行网络通信，数据的传输是通过流来进行的
 */
public class BioTest {

    @Test
    public void client() throws IOException {
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            socket = new Socket();
            //连接服务端
            socket.connect(new InetSocketAddress("127.0.0.1", 8899));

            System.out.println("连接成功");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("准备发送数据");
            out.writeUTF("yujin");
            out.flush();

            //接收服务端的输出
            System.out.println(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket)
                socket.close();
            if (null != out)
                out.close();
        }
    }

    @Test
    public void server() throws IOException {
        String host = "127.0.0.1";
        int port = 8899;

        ServerSocket ss = new ServerSocket();
        //监听端口
        ss.bind(new InetSocketAddress(host, port));

        System.out.println("服务已启动,端口号:" + port);
        try {
            while (true) {
                //接收连接，采用单线程处理的模型，每个连接都用单独线程处理
                new Thread(new ServerTask(ss.accept())).start();
            }
        } finally {
            ss.close();
        }
    }

    class ServerTask implements Runnable {
        Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                String msg = in.readUTF();
                System.out.println("服务端接收到:" + msg);

                out.writeUTF("hello," + msg);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
