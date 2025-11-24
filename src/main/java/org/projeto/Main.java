package org.projeto;

import javafx.application.Application;
import javafx.stage.Stage;
import org.projeto.API.FXMLManager;
import org.projeto.API.GerenciadorAPI;
import org.projeto.database.DatabaseConnection;
import org.projeto.database.DatabaseSetup;

public class Main extends Application {

    private static GerenciadorAPI gerenciadorAPI = new GerenciadorAPI();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLManager.setPrimaryStage(primaryStage);

            gerenciadorAPI.runApp(false);
        } catch (Exception e) {
            System.out.println("Erro ao iniciar interface gráfica:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("Iniciando MedCare e banco SQLite...");
        System.out.println("MedCare - seu sistema de gerenciamento de clínica médica.");

        try {
            DatabaseConnection.getConnection();
            DatabaseSetup.initializeDatabase();

            System.out.println("Banco de dados inicializado com sucesso!");

            launch(args);
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o MedCare: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("MedCare finalizado!");
    }
}