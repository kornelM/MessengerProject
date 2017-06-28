package com.sda.messenger;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class MessengerClient {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 4444;
       // Scanner scanner = new Scanner(System.in);

        //Map<String, String> ipByUser;


        Socket socket = new Socket(host, port);

        Connetions connection = new Connetions(socket);
        connection.start();
    }
}
