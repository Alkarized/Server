package server;

import collection.Receiver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Connection {
    private InetSocketAddress inetSocketAddress;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Receiver receiver;

    public Connection(int port) {
        this.inetSocketAddress = new InetSocketAddress(port);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void start() {
        try {
            connectServer();

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
                            acceptClient(selectedKey);
                        }

                        if (selectedKey.isReadable()) {
                            Object object = readObjectFromClient(selectedKey);
                            selectedKey.attach(new ObjectParser().parseObjectToByteBuffer(object, receiver));
                            selectedKey.interestOps(SelectionKey.OP_WRITE);
                        }

                        if (selectedKey.isWritable()) {
                            writeAns(selectedKey);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CancelledKeyException ignored){}
        }


    }

    private void connectServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void acceptClient(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
        SocketChannel client = server.accept();

        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("Connected");
    }

    private Object readObjectFromClient(SelectionKey selectionKey) throws IOException {

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        ByteBuffer outBuffer = ByteBuffer.allocate(2048);
        try {
            while (socketChannel.read(byteBuffer) >= 0 || byteBuffer.position() > 0) {
                byteBuffer.flip();
                outBuffer.put(byteBuffer);
                byteBuffer.compact();
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(outBuffer.array()));
                    return objectInputStream.readObject();
                } catch (StreamCorruptedException ignored) {
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            if (e.getMessage().equals("An established connection was aborted by the software in your host machine")) {
                socketChannel.close();
            }
        }
        return null;
    }

    public void writeAns(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();

        buffer.flip();
        System.out.println("written");
        socketChannel.write(buffer);
        selectionKey.interestOps(SelectionKey.OP_READ);
    }

    public void endConnection() {
        try {
            serverSocketChannel.close();
            selector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
