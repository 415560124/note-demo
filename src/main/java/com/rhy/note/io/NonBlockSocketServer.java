package com.rhy.note.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * 同步非阻塞模型
 * 问题：
 * 1.如果有C10K个连接会有大量无效的遍历
 */
public class NonBlockSocketServer {
    //客户端连接集合
    private static final List<SocketChannel> channelList = new Vector<>();

    public static void main(String[] args) throws IOException {
        //创建NIO ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //监听9000端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        //设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");
        while (true){
            //true非阻塞accept方法不会阻塞，否则false会阻塞
            SocketChannel clientSocekt = serverSocketChannel.accept();
            if(clientSocekt != null){
                System.out.println("连接成功");
                //设置socketChannel为非阻塞  read时不会阻塞
                clientSocekt.configureBlocking(false);
                //保存连接在List中
                channelList.add(clientSocekt);
            }
            if(!channelList.isEmpty()){
                //遍历连接读取数据
                Iterator<SocketChannel> iterator = channelList.iterator();
                while(iterator.hasNext()){
                    SocketChannel socketChannel = iterator.next();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    //非阻塞模式read方法不会阻塞。否则会阻塞
                    int len = socketChannel.read(byteBuffer);
                    if(len > 0){
                        System.out.println("接收到消息："+new String(byteBuffer.array()));
                    }else if(len == -1){
                        //客户端断开连接
                        iterator.remove();
                        System.out.println("客户端断开连接");
                    }
                }
            }
        }

    }
}
