package main;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class MainServerClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(1707));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()){
                    SocketChannel client = serverSocketChannel.accept();
                    System.out.println("Есть клиент - " + client.getRemoteAddress());
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                }

                if (selectionKey.isReadable() && selectionKey.isWritable()){
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    client.read(byteBuffer);
                    byteBuffer.rewind();
                    System.out.println(Arrays.toString(byteBuffer.array()));
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }
    }
}

