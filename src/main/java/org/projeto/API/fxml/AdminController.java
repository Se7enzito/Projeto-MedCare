package org.projeto.API.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.ClinicaDAO;
import org.projeto.database.model.Clinica;

public class AdminController {

    @FXML private TextField inputEmail;
    @FXML private PasswordField inputSenha;

    @FXML
    public void criarClinica() {
        try {
            String email = inputEmail.getText();
            String senha = inputSenha.getText();

            if (email.isEmpty() || senha.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Campos vazios");
                alert.setContentText("Preencha todos os campos.");
                alert.show();
                return;
            }

            ClinicaDAO dao = new ClinicaDAO();
            Clinica c = new Clinica(email, senha);
            dao.insert(c);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso!");
            alert.setContentText("Clínica cadastrada com sucesso.");
            alert.show();

            inputEmail.clear();
            inputSenha.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sair() {
        FXMLManager.showScene("login.fxml", "MedCare - Login");
    }

}
