package ma.enset.blocking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientChat extends Application {
    PrintWriter pw;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Client de Chat");
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // En-tête
        Label labelHost = new Label("Host:");
        TextField textFieldHost = new TextField("localhost");
        Label labelPort = new Label("Port:");
        TextField textFieldPort = new TextField("1234");
        Button buttonConnecter = new Button("Connecter");
        HBox hBoxHeader = new HBox();
        hBoxHeader.setSpacing(10);
        hBoxHeader.getChildren().addAll(labelHost, textFieldHost, labelPort, textFieldPort, buttonConnecter);
        borderPane.setTop(hBoxHeader);

        // Liste de messages
        ObservableList<String> listModel = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(listModel);
        VBox vBoxMessages = new VBox();
        vBoxMessages.setSpacing(10);
        vBoxMessages.getChildren().addAll(listView);
        borderPane.setCenter(vBoxMessages);

        // Pied de page
        Label labelMessage = new Label("Message:");
        TextField textFieldMessage = new TextField();
        textFieldMessage.setPrefSize(300, 30);
        Button buttonEnvoyer = new Button("Envoyer");
        HBox hBoxFooter = new HBox();
        hBoxFooter.setSpacing(10);
        hBoxFooter.getChildren().addAll(labelMessage, textFieldMessage, buttonEnvoyer);
        borderPane.setBottom(hBoxFooter);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Connexion au serveur
        buttonConnecter.setOnAction((evt) -> {
            String host = textFieldHost.getText();
            int port = Integer.parseInt(textFieldPort.getText());
            try {
                Socket socket = new Socket(host, port);
                InputStream inputStream = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(isr);
                pw = new PrintWriter(socket.getOutputStream(), true);
                new Thread(() -> {
                    try {
                        while (true) {
                            String response = bufferedReader.readLine();
                            Platform.runLater(() -> {
                                listModel.add(response);
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Envoi d'un message
        buttonEnvoyer.setOnAction((evt) -> {
            String message = textFieldMessage.getText();
            pw.println(message);
        });

    }
}