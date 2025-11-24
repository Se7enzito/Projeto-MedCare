package org.projeto.API.fxml.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class CadastroPacienteController {

    private final static PacienteDAO dao = new PacienteDAO();

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputNascimento;

    @FXML
    private TextField inputTelefone;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputEndereco;

    @FXML
    private void cadastrarPaciente() throws SQLException {

        if (!validarCampos()) {
            return;
        }

        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String nascimento = inputNascimento.getText();
        String telefone = inputTelefone.getText();
        String email = inputEmail.getText();
        String endereco = inputEndereco.getText();

        System.out.println("---- Cadastro de Paciente ----");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Nascimento: " + nascimento);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Endereço: " + endereco);

        Paciente paciente = new Paciente(nome, cpf, nascimento, telefone, email, endereco);

        dao.insert(paciente);

        mostrarMensagem("Sucesso!", "Paciente cadastrado com sucesso.");
        limparCampos();
    }

    private boolean validarCampos() {
        if (inputNome.getText().isEmpty() ||
                inputCpf.getText().isEmpty() ||
                inputNascimento.getText().isEmpty() ||
                inputTelefone.getText().isEmpty() ||
                inputEmail.getText().isEmpty() ||
                inputEndereco.getText().isEmpty()) {
            mostrarMensagem("Campos incompletos", "Preencha todos os campos para continuar.");

            return false;
        }

        return true;
    }

    private void limparCampos() {
        inputNome.clear();
        inputCpf.clear();
        inputNascimento.clear();
        inputTelefone.clear();
        inputEmail.clear();
        inputEndereco.clear();
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
