package com.sda.messenger;

import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.Scanner;

public class MyWriter implements Runnable {

    private final PrintWriter printWriter;
    private final Scanner scanner;

    public MyWriter(final OutputStream outputStream) {
        this.printWriter = new PrintWriter(outputStream, true);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            String line = scanner.nextLine();
            printWriter.println(line);
        }
    }
}
