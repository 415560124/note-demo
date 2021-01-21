package com.rhy.note.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 阻塞IO
 * 问题：
 * 1.如果使用异步处理的话，C10K问题无法解决
 * 2.如果读取数据太大或未发送数据，读写会一直阻塞占用线程
 * 3.线程切换开销
 */
public class BlockSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while(true){
            System.out.println("等待连接...");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了");
            //同步阻塞IO
//            handler(clientSocket);
            //异步阻塞IO
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read...");
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕");
        if(read != -1){
            System.out.println("接收到客户端的数据"+new String(bytes,0,read));
        }
        clientSocket.getOutputStream().write("Hello client!".getBytes(StandardCharsets.UTF_8));
        clientSocket.getOutputStream().flush();
    }
}
