package com.sda.messenger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyWriter implements Runnable {

    private final PrintWriter printWriter;
    private final Scanner scanner;
    Socket socket;




    public MyWriter(final Socket socket) throws IOException {

        this.socket = socket;
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.scanner = new Scanner(System.in);

    }

    @Override
    public void run() {

        while (socket.isConnected()) {
            String line = scanner.nextLine();
            printWriter.println(line);
        }
    }
}
