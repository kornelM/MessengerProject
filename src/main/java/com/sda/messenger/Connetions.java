package com.sda.messenger;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Connetions {

    private MyReader myReader;
    private MyWriter myWriter;


    private Executor pool;

    public Connetions(Socket socket) throws IOException {
        this.myReader = new MyReader(socket);
        this.myWriter= new MyWriter(socket);
        pool = Executors.newCachedThreadPool();
    }

    public void start() {
        pool.execute(myWriter);
        pool.execute(myReader);
    }

}
