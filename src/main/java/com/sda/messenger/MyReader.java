package com.sda.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyReader implements Runnable {

    private final BufferedReader bufferedReader;
    Socket socket;
    Login login;


    public MyReader(final Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.login = new Login();
    }

    @Override
    public void run() {
        String nickname = login.findUser(socket.getInetAddress().getHostAddress());

        String line = "";

        while (socket.isConnected() && line != null) {
            try {
                line = bufferedReader.readLine();
                System.out.println(nickname + ": " + line);
            } catch (IOException e) {
                System.err.println(e);
                throw new RuntimeException(e);
            }
        }
    }
}
