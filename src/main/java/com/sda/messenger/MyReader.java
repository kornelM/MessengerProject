package com.sda.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyReader implements Runnable {

    private final BufferedReader bufferedReader;

    public MyReader(final InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        while (true) {
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
