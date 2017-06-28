package com.sda.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyReader implements Runnable {

    private final BufferedReader bufferedReader;
    Socket socket;

    public MyReader(final Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {

        while (socket.isConnected()) {
            try {
                String line = bufferedReader.readLine();
                System.out.println(line);
            } catch (IOException e) {
                System.err.println(e);
                throw new RuntimeException(e);
            }
        }
    }
}
