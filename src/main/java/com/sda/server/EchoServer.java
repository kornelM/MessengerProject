package com.sda.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EchoServer {

    public static void main(String[] args) throws Exception {

        int port = 4444;
        long SLEEP_TIME = 5_000L;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started server on port " + port);
        Executor executor = Executors.newCachedThreadPool();


        while (true) {
            System.out.println("Waiting for client : ...");
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            executor.execute(clientHandler); //after a coma write a name of a thread

//            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
//
//                String line = reader.readLine();
//                System.out.println("Received: "+line);
//                printWriter.println("Response from server: "+line);
//                printWriter.flush();
//
//                printWriter.close();
//                reader.close();
//                Thread.sleep(SLEEP_TIME);
        }
    }
}
