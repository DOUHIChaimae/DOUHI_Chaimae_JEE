package ma.enset.noblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingServer {

    private static final int BUFFER_SIZE = 1024;
    private static final int TIMEOUT = 5000;
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {

        // Ouvrir un canal de serveur non bloquant
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // Lier le canal de serveur à un port et démarrer l'écoute des connexions entrantes
        serverChannel.socket().bind(new InetSocketAddress(SERVER_PORT));
        serverChannel.register(Selector.open(), SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port " + SERVER_PORT);

        while (true) {

            Selector selector = Selector.open();

            // Attendre les événements de sélection
            if (selector.select(TIMEOUT) == 0) {
                continue;
            }

            // Obtenir l'ensemble des clés sélectionnées
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {

                    // Accepter une nouvelle connexion entrante
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);

                    System.out.println("New client connected: " + clientChannel.getRemoteAddress());

                } else if (key.isReadable()) {

                    // Lire les données de l'utilisateur
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    clientChannel.read(buffer);
                    String message = new String(buffer.array()).trim();

                    System.out.println("Received message from client " + clientChannel.getRemoteAddress() + ": " + message);

                    // Répondre à l'utilisateur
                    String response = "Server received your message: " + message;
                    buffer = ByteBuffer.wrap(response.getBytes());
                    clientChannel.write(buffer);

                    // Fermer la connexion si l'utilisateur a entré 'exit'
                    if (message.equals("exit")) {
                        System.out.println("Client " + clientChannel.getRemoteAddress() + " disconnected.");
                        clientChannel.close();
                    }
                }

                keyIterator.remove();
            }
        }
    }
}