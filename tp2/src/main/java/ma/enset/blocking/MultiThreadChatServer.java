package ma.enset.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadChatServer extends Thread {
    private List<Conversation> conversations = new ArrayList<>();
    int clientsCount;

    public static void main(String[] args) {
        new MultiThreadChatServer().start();

    }

    @Override
    public void run() {
        System.out.println("The server is started using port 1235");

        try {
            ServerSocket serverSocket = new ServerSocket(1235);
            while (true) {
                Socket socket = serverSocket.accept();
                ++clientsCount;
                Conversation conversation = new Conversation(socket, clientsCount);
                conversations.add(conversation);
                conversation.start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Conversation extends Thread {
        Socket socket;
        int clientId;

        public Conversation(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                String ip = socket.getRemoteSocketAddress().toString();
                System.out.println("New client connexion = " + clientId + " Ip = " + ip);
                pw.println("welcome, your ID is " + clientsCount);
                String request;
                while ((request = br.readLine()) != null) {
                    System.out.println("New request  => Ip : " + ip + " request : " + request);
                    broadCastMessage(request);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void broadCastMessage(String message) {
        try {
            for (Conversation conversation : conversations) {
                Socket socket = conversation.socket;
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream,true);
                printWriter.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
