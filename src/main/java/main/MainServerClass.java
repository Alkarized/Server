package main;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MainServerClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(1707));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }

                if (selectionKey.isReadable() && selectionKey.isWritable()){
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    client.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new ByteArrayInputStream(byteBuffer.array()));
                    byteBuffer.clear();

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(FileResponse.NothingAccepted);
                    objectOutputStream.flush();
                    byteBuffer.put(byteArrayOutputStream.toByteArray());
                    client.write(byteBuffer);
                    byteBuffer.clear();
                }

                iterator.remove();
            }
        }
    }
}

