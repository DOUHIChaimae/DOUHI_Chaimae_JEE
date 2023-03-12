package ma.enset.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadBlockingServer extends Thread {
    int clientsCount;

    public static void main(String[] args) {
        new MultiThreadBlockingServer().start();

    }

    @Override
    public void run() {
        System.out.println("The server is started using port 1234!!");
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                Socket socket = serverSocket.accept();
                ++clientsCount;
                new Conversation(socket, clientsCount).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Conversation extends Thread {
        //test comment
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
                    String reponse = "size is " + request.length();
                    pw.println(reponse);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
