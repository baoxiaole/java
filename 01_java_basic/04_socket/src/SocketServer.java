import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * bxl
 * 2019/2/14 09:39
 */
public class SocketServer {

    public static void main(String[] args)throws Exception{
        SocketServer socketServer = new SocketServer();
        socketServer.socketServer();
    }

    public void socketServer() throws Exception{
        ServerSocket server = new ServerSocket(83);
        try{
            while (true){
                Socket socket = server.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Integer sourcePort = socket.getPort();
                int maxLen = 2048;
                byte[] contextByte = new byte[maxLen];
                //这里会被阻塞，直到有数据准备好
                int readLen = in.read(contextByte,0,maxLen);
                //读取信息
                String message = new String(contextByte,0,readLen);
                System.out.println("服务端收到来自于端口："+sourcePort+"的信息："+message);
                out.write("回发响应信息！".getBytes());
                out.close();
                in.close();
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(server != null){
                server.close();
            }
        }
    }
}
