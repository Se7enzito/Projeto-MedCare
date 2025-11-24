package org.projeto.API;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Parent loadFXML(String fxmlName) {
        try {
            return FXMLLoader.load(FXMLManager.class.getResource("/" + fxmlName));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar FXML: " + fxmlName);
        }
    }

    public static void showScene(String fxmlName, String title) {
        try {
            Parent root = loadFXML(fxmlName);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao exibir cena: " + fxmlName);
        }
    }

    public static void openWindow(String fxmlName, String title) {
        try {
            Parent root = loadFXML(fxmlName);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao abrir nova janela: " + fxmlName);
        }
    }
}