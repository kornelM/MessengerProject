package com.sda.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by kornel on 27.06.17.
 */
public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Witaj, tu wątek " + Thread.currentThread().getName());

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            String response = reader.readLine();
            System.out.println("Received: " + response);
            printWriter.println("Response from server: " + response);
            printWriter.flush();
        } catch (IOException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
