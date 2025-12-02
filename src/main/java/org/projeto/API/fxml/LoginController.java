package org.projeto.API.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import org.projeto.API.FXMLManager;
import org.projeto.Main;
import org.projeto.database.dao.ClinicaDAO;
import org.projeto.database.model.Clinica;

public class LoginController {

    @FXML private TextField inputEmail;
    @FXML private PasswordField inputSenha;

    @FXML
    public void initialize() {
        int idClinica = Main.getIdClinica();

        if (idClinica != 0) {
            FXMLManager.showScene("inicial.fxml", "MedCare - Menu Inicial");
        }
    }

    public void login() {
        try {
            String email = inputEmail.getText();
            String senha = inputSenha.getText();

            if (email.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
                FXMLManager.showScene("admin.fxml", "MedCare - Admin");
                return;
            }

            ClinicaDAO dao = new ClinicaDAO();
            Clinica c = dao.getByEmail(email);

            if (c == null || !c.getSenha().equals(senha)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Credenciais incorretas");
                alert.setContentText("Email ou senha inválidos.");
                alert.show();
                return;
            }

            Main.setIdClinica(c.getId());

            FXMLManager.showScene("inicial.fxml", "MedCare - Menu Inicial");

            System.out.println("Login OK!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}