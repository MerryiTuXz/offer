package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类，专门接收服务器端响应消息
 */
public class NioClientHandler implements Runnable {
    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0)
                    continue;
                // 获取可用channel集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    // selectionKey实例
                    SelectionKey selectionKey = iterator.next();
                    // 移除Set中的当前selectionKey
                    iterator.remove();
                    // 如果是 可读事件
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        // 要从selectionKey中获取到已就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 循环读取服务器端响应信息
        String response = "";
        while (socketChannel.read(byteBuffer) > 0) {
            // 切换buffer为读模式
            byteBuffer.flip();
            // 读取buffer中的内容
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }
        // 将channel再次注册到selector上，监听它的可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 将服务器端响应的信息打印到本地
        if (response.length() > 0) {
            System.out.println(response);
        }
    }
}
