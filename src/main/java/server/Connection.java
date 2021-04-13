package server;

import collection.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
            Messages.normalMessageOutput(e.toString(), MessageColor.ANSI_RED);
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                        Messages.normalMessageOutput("Read info from client", MessageColor.ANSI_CYAN);
                        selectedKey.attach(new ObjectParser().parseObjectToByteBuffer(object, receiver));
                        selectedKey.interestOps(SelectionKey.OP_WRITE);
                    }

                    if (selectedKey.isWritable()) {
                        writeAns(selectedKey);
                    }
                }
            }

        }


    }

    private void connectServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(inetSocketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            Messages.normalMessageOutput("Start and open server", MessageColor.ANSI_GREEN);
        } catch (IOException e) {
            Messages.normalMessageOutput(e.toString(), MessageColor.ANSI_RED);

        }
    }

    private void acceptClient(SelectionKey selectionKey) {
        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
        try {
            SocketChannel client = server.accept();

            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            Messages.normalMessageOutput("Accept new Client" + client.getRemoteAddress(), MessageColor.ANSI_BLUE);
        } catch (IOException e) {
            Messages.normalMessageOutput(e.toString(), MessageColor.ANSI_RED);

        }
        Messages.normalMessageOutput("Connected", MessageColor.ANSI_GREEN);
    }

    private Object readObjectFromClient(SelectionKey selectionKey) {

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        ByteBuffer outBuffer = ByteBuffer.allocate(2048);

        while (true) {
            try {
                if (!(socketChannel.read(byteBuffer) >= 0 || byteBuffer.position() > 0)) break;
            } catch (IOException e) {
                if (e.getMessage().equals("An existing connection was forcibly closed by the remote host")) {
                    break;
                }
            }
            byteBuffer.flip();
            outBuffer.put(byteBuffer);
            byteBuffer.compact();

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(outBuffer.array()));
                return objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException ignored) {
            }
        }

        return null;
    }

    public void writeAns(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();

        buffer.flip();

        try {
            socketChannel.write(buffer);
        } catch (IOException e) {
            if (e.getMessage().equals("An existing connection was forcibly closed by the remote host")) {
                try {
                    socketChannel.close();
                } catch (IOException ioException) {
                    Messages.normalMessageOutput(ioException.toString(), MessageColor.ANSI_RED);
                }
            }
        }
        try {
            selectionKey.interestOps(SelectionKey.OP_READ);
            Messages.normalMessageOutput("Written answer for client", MessageColor.ANSI_GREEN);
        } catch (CancelledKeyException ignored) {

        }

    }

    public void endConnection() {
        try {
            serverSocketChannel.close();
            selector.close();
        } catch (Exception e) {
            Messages.normalMessageOutput(e.toString(), MessageColor.ANSI_RED);
        }


    }


}
