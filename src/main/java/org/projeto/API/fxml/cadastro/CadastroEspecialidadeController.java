package org.projeto.API.fxml.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;

public class CadastroEspecialidadeController {

    private final static EspecialidadeDAO dao = new EspecialidadeDAO();

    @FXML
    private TextField inputNome;

    @FXML
    private TextArea inputDescricao;

    @FXML
    private void cadastrarEspecialidade() throws SQLException {
        if (!validarCampos()) {
            return;
        }

        String nome = inputNome.getText();
        String descricao = inputDescricao.getText();

        System.out.println("---- Cadastro de Especialidade ----");
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);

        Especialidade especialidade = new Especialidade(nome, descricao);

        dao.insert(especialidade);

        mostrarMensagem("Sucesso!", "Especialidade cadastrado com sucesso.");
        limparCampos();
    }

    private boolean validarCampos() {
        if (inputNome.getText().isEmpty() ||
                inputDescricao.getText().isEmpty()) {
            mostrarMensagem("Campos incompletos", "Preencha todos os campos para continuar.");

            return false;
        }

        return true;
    }

    private void limparCampos() {
        inputNome.clear();
        inputDescricao.clear();
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
