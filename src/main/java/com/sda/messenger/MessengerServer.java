package com.sda.messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MessengerServer {

    public static void main(String[] args) throws IOException {
        int port = 4444;

        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = serverSocket.accept();


        Connetions connections = new Connetions(socket);
        connections.start();
    }
}
