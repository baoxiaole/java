import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * 这里模拟客户端的多线程，一个SocketClientRequestThread模拟一个客户端请求
 * bxl
 * 2019/2/14 10:00
 */
public class SocketClientRequestThread implements Runnable {

    /**
     * countDownLatch是java提供的同步计数器。
     * 当计数器数值减为0时，所有受其影响而等待的线程将会被激活。这样保证模拟并发请求的真实性
     */
    private CountDownLatch countDownLatch;

    /**
     * 线程编号
     */
    private Integer clientIndex;

    public SocketClientRequestThread(CountDownLatch countDownLatch,Integer clientIndex){
        this.countDownLatch = countDownLatch;
        this.clientIndex = clientIndex;
    }

    @Override
    public void run() {
        Socket socket = null;
        OutputStream clientRequest = null;
        InputStream clientResponse = null;
        try{
            socket = new Socket("localhost",83);
            clientRequest = socket.getOutputStream();
            clientResponse = socket.getInputStream();
            //等待，直到完成所有线程的启动，然后所有线程一起发送请求
            this.countDownLatch.await();
            //发送请求信息
            clientRequest.write(("这是第"+ this.clientIndex + "个客户端的请求。").getBytes());
            clientRequest.flush();
            System.out.println("第" + this.clientIndex + "个客户端的请求发送完成，等待服务器返回信息");
            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            int realLen;
            String message = "";
            while((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1) {
                message += new String(contextBytes , 0 , realLen);
            }
            System.out.println("第" + this.clientIndex + "个客户端接收到来自服务器的信息:" + message);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(clientRequest != null) {
                    clientRequest.close();
                }
                if(clientResponse != null) {
                    clientResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
