package com.sda.gui;

import com.sda.messenger.Connetions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by kornel on 28.06.17.
 */
public class App {
    private JButton button1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextArea textArea1;
    private ReaderForTextArea readerForTextArea;
    private  BufferedReader bufferedReader;


    public App() throws IOException {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Everything is fine");
                System.out.println(textField1.getText());
                textField1.setText("");
            }
        });

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.append(textField1.getText() + "\n");
                System.out.println("Everything is fine");
                System.out.println(textField1.getText());
                textField1.setText("");
            }
        });

        bufferedReader = new BufferedReader(new InputStreamReader(new Socket("localhost", 4444).getInputStream()));
        textArea1.append(bufferedReader.readLine());
    }

    public class ReaderForTextArea implements Runnable {

        private final BufferedReader bufferedReader;

        public ReaderForTextArea(final InputStream inputStream) {
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


    public static void main(String[] args) throws IOException {

        String host = "127.0.0.1";
        int port = 4444;

        Socket socket = new Socket(host, port);

        Connetions connection = new Connetions(socket);
        connection.start();


        JFrame frame = new JFrame("app");
        frame.setContentPane(new App().panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
