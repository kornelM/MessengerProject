package com.sda.messenger;

import java.io.IOException;
import java.net.Socket;

public class MessengerClient {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 4444;

        Socket socket = new Socket(host, port);

        Connetions connection = new Connetions(socket);
        connection.start();
    }
}
