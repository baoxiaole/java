import com.sun.xml.internal.stream.util.BufferAllocator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 多路复用IO服务器
 * bxl
 * 2019/2/14 14:08
 */
public class SockerServer1 {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(83));
        Selector selector = Selector.open();
        //注意、服务器通道只能注册SelectionKey.OP_ACCEPT事件
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);

        try {
            while (true){
                //如果条件成立，说明本次询问selector，并没有获取到任何准备好的、感兴趣的事件
                //java程序对多路复用IO的支持也包括了阻塞模式 和非阻塞模式两种。
                if(selector.select(100) == 0){
                    //================================================
                    //      这里视业务情况，可以做一些然并卵的事情
                    //================================================
                    continue;
                }
                //这里就是本次询问操作系统，所获取到的“所关心的事件”的事件类型（每一个通道都是独立的）
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()){
                    SelectionKey readyKey = selectionKeys.next();
                    //这个已经处理的readyKey一定要移除。如果不移除，就会一直存在在selector.selectedKeys集合中
                    //待到下一次selector.select() > 0时，这个readyKey又会被处理一次
                    selectionKeys.remove();
                    SelectableChannel selectableChannel = readyKey.channel();
                    if(readyKey.isValid() && readyKey.isAcceptable()){
                        System.out.println("==========Channel通道已经准备好==========");
                        /*
                         * 当server socket channel通道已经准备好，就可以从server socket channel中获取socketchannel了
                         * 拿到socket channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情。
                         * 否则无法监听到这个socket channel到达的数据
                         * */
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectableChannel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        registerSocketChannel(socketChannel,selector);
                    }else if(readyKey.isValid() && readyKey.isConnectable()){
                        System.out.println("==========socket channel 建立连接==========");
                    }else if(readyKey.isValid() && readyKey.isReadable()){
                        System.out.println("==========socket channel 数据准备完成，可以去读取==========");
                        readSocketChannel(readyKey);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }

    /**
     * 在server socket channel接收到/准备好 一个新的 TCP连接后。就会向程序返回一个新的socket Channel。
     * 但是这个新的socket channel并没有在selector“选择器/代理器”中注册，所以程序还没法通过selector通知这个socket channel的事件。
     * 于是我们拿到新的socket channel后，要做的第一个事情就是到selector“选择器/代理器”中注册这个socket channel感兴趣的事件
     * @param socketChannel
     * @param selector
     * @throws IOException
     */
    private static void registerSocketChannel(SocketChannel socketChannel , Selector selector) throws IOException {
        socketChannel.configureBlocking(false);
        //socket通道可以且只可以注册三种事件SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
    }

    private static void readSocketChannel(SelectionKey readyKey) throws Exception{
        SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
        //获取客户端使用的端口
        InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
        Integer resourcePort = sourceSocketAddress.getPort();
        //拿到这个socket channel使用的缓存区，准备读取数据.重要的就是三个元素capacity,position和limit。
        ByteBuffer contextBytes = (ByteBuffer)readyKey.attachment();
        //将通道的数据写入到缓存区，注意是写入到缓存区。
        //由于之前设置了ByteBuffer的大小为2048 byte，所以可以存在写入不完的情况
        int readLen = -1;
        try {
            readLen = clientSocketChannel.read(contextBytes);

        }catch (Exception e){
            //这里抛出了异常，一般就是客户端因为某种原因终止了。所以关闭channel就行了
            e.printStackTrace();
            clientSocketChannel.close();
            return;
        }
        //如果缓存区中没有任何数据（但实际上这个不太可能，否则就不会触发OP_READ事件了）
        if(readLen == -1){
            System.out.println("缓存区没有数据？？");
            return;
        }
        //将缓存区从写状态切换为读状态（实际上这个方法是读写模式互切换）。
        //这时java nio框架中的这个socket channel的写请求将全部等待。
        contextBytes.flip();
        //注意中文乱码的问题，这里用URLDecoder/URLEncoder，进行解编码。
        byte[] messageBytes = contextBytes.array();
        String messageEncode = new String(messageBytes,"UTF-8");
        String message = URLDecoder.decode(messageEncode,"UTF-8");

        //如果收到了“over”关键字，才会清空buffer，并回发数据；
        //否则不清空缓存，还要还原buffer的“写状态”
        if(message.indexOf("over") != -1){
            contextBytes.clear();
            System.out.println("端口:" + resourcePort + "客户端发来的信息======message : " + message);

            //======================================================
            //          当然接受完成后，可以在这里正式处理业务了
            //======================================================

            //回发数据，并关闭channel
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", "UTF-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        }else{
            System.out.println("端口:" + resourcePort + "客户端信息还未接受完，继续接受======message : " + message);
            //这时，limit和capacity的值一致，position的位置是realLen的位置
            contextBytes.position(readLen);
            contextBytes.limit(contextBytes.capacity());
        }
    }
}
