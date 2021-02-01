package com.rhy.note.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NonBlockSelectorSocketServer {
    public static void main(String[] args) throws IOException {
        //创建NIO ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //打开Selector处理channel，即创建epoll
        //linux底层命令：epoll_create
        Selector selector = Selector.open();
        //把ServerSocketChannel注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");
        while(true){
            //把注册的ServerSocketChannel通过Linux命令epoll_ctl与selector绑定
            selector.select();
            //获取selector中注册的全部事件中有新事件的SelectionKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    //连接就绪事件
                    ServerSocketChannel serverSocketChannelTemp = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                }else if(selectionKey.isReadable()){
                    //读就绪事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    // 如果有数据，把数据打印出来
                    if (len > 0) {
                        System.out.println("接收到消息：" + new String(byteBuffer.array()));
                    } else if (len == -1) { // 如果客户端断开连接，关闭Socket
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
                //从事件列表中移除，避免下次selectionKey重复处理
                iterator.remove();
            }

        }
    }
}
