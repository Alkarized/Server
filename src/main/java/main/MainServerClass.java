package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServerClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1707);
        Socket socket = serverSocket.accept();
        System.out.println("Запрос принят - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        TestClass testClass = (TestClass) input.readObject();
        System.out.println("Полученное значение - " + testClass.x);
        testClass.x = 12;
        output.writeObject(testClass);
        output.flush();
        System.out.println("Отправлено");
        /*socket.close();
        serverSocket.close();*/
    }
}

class TestClass implements Serializable {
    private static final long serialVersionUID = 1012312321;
    int x = 5;

    public void say(){

    }
}

