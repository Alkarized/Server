package main;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MainServerClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        read2();
    }

    private static void read2() throws IOException {
        Selector selector = Selector.open();
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(1111));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectedKey = iterator.next();
                    iterator.remove();

                    if (selectedKey.isValid()) {
                        if (selectedKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectedKey.channel();
                            SocketChannel client = server.accept();

                            if (client != null) {
                                client.configureBlocking(false);
                                client.register(selector, SelectionKey.OP_READ);
                                System.out.println("Connected");
                            }
                        }

                        if (selectedKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            ByteBuffer outBuffer = ByteBuffer.allocate(1024);

                            while (socketChannel.read(byteBuffer) >= 0 || byteBuffer.position() > 0) {
                                byteBuffer.flip();
                                outBuffer.put(byteBuffer);
                                byteBuffer.compact();
                                try {
                                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(outBuffer.array()));
                                    Object object = objectInputStream.readObject();
                                    System.out.println(object);
                                    break;
                                } catch (Exception ignored){}
                            }

                            System.out.println("end Reading");
                            selectedKey.attach(outBuffer);
                            selectedKey.interestOps(SelectionKey.OP_WRITE);
                        }

                        if (selectedKey.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                            ByteBuffer buffer = (ByteBuffer) selectedKey.attachment();

                            buffer.flip();
                            System.out.println("written");
                            buffer.rewind();
                            socketChannel.write(buffer);
                            selectedKey.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                //System.out.println("ошибочка");
                e.printStackTrace();
            }
        }
    }
}