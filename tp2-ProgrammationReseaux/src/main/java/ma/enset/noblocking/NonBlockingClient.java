package ma.enset.noblocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NonBlockingClient {

    private static final int BUFFER_SIZE = 1024;
    private static final int SERVER_PORT = 1234;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) throws IOException {

        // Ouvrir un canal de socket non bloquant
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        // Connecter le canal de socket au serveur
        socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

        // Attendre la fin de la connexion
        while (!socketChannel.finishConnect()) {
            // Attendre que la connexion soit établie
        }

        System.out.println("Connected to server " + SERVER_HOST + ":" + SERVER_PORT);

        // Ouvrir un flux d'entrée pour lire les messages de l'utilisateur
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // Boucle principale pour lire les messages de l'utilisateur et les envoyer au serveur
        String message;
        do {
            message = readMessageFromConsole(input);
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);

            // Lire la réponse du serveur
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
            socketChannel.read(buffer);
            String response = new String(buffer.array()).trim();
            System.out.println("Server response: " + response);

        } while (!message.equals("exit"));

        // Fermer le flux et le canal de socket
        input.close();
        socketChannel.close();
    }

    private static String readMessageFromConsole(BufferedReader input) throws IOException {
        System.out.print("Enter message: ");
        return input.readLine();
    }
}