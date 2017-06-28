package com.sda.windowMessenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kornel Mikolajski on 28.06.2017.
 */
public class SimpleWindowMessengerClient {
    JTextArea receivedMessages;
    JTextField messages;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleWindowMessengerClient simpleWindowMessengerClient = new SimpleWindowMessengerClient();
        simpleWindowMessengerClient.start();
    }

    public void start(){
        JFrame frame = new JFrame("Simple messenger");
        JPanel mainPanel = new JPanel();

        receivedMessages = new JTextArea(15, 20);
        receivedMessages.setLineWrap(true);
        receivedMessages.setWrapStyleWord(true);
        receivedMessages.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(receivedMessages);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        messages = new JTextField(20);
        JButton sendMessagesButton = new JButton("Send");
        sendMessagesButton.addActionListener(new SendMessagesListener());

        mainPanel.add(scrollPane);
        mainPanel.add(messages);
        mainPanel.add(sendMessagesButton);
        configureCommunication();

        Thread receiverThread = new Thread(new MessagesReceiver());

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    }


    private void configureCommunication(){
        try{
            socket = new Socket("localhost", 5000);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(socket.getOutputStream());

            System.out.println("Configuration done");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public class SendMessagesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(messages.getText());
                writer.flush();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            messages.setText("");
            messages.requestFocus();
        }
    }


    public class MessagesReceiver implements Runnable{

        @Override
        public void run() {
            String message;

            try{
                while((message = reader.readLine()) != null)
                    System.out.println("Read: " + message);
                receivedMessages.append(message + "\n");
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
    }
}
