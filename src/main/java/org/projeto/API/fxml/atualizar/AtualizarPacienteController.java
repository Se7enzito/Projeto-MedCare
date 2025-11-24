package org.projeto.API.fxml.atualizar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class AtualizarPacienteController {

    @FXML private ChoiceBox<Paciente> choicePaciente;

    @FXML private TextField inputNome;
    @FXML private TextField inputCpf;
    @FXML private TextField inputTelefone;
    @FXML private TextField inputEmail;
    @FXML private TextField inputEndereco;
    @FXML private TextField inputNascimento;

    private final PacienteDAO dao = new PacienteDAO();

    @FXML
    public void initialize() {
        carregarPacientes();

        choicePaciente.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, novo) -> {
            if (novo != null) preencherCampos(novo);
        });
    }

    private void carregarPacientes() {
        try {
            choicePaciente.getItems().clear();
            choicePaciente.getItems().addAll(dao.getAll());

            if (!choicePaciente.getItems().isEmpty()) {
                choicePaciente.getSelectionModel().selectFirst();
                preencherCampos(choicePaciente.getValue());
            }

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar pacientes.");
        }
    }

    private void preencherCampos(Paciente p) {
        inputNome.setText(p.getNome());
        inputCpf.setText(p.getCpf());
        inputTelefone.setText(p.getTelefone());
        inputEmail.setText(p.getEmail());
        inputEndereco.setText(p.getEndereco());
        inputNascimento.setText(p.getDataNascimento());
    }

    @FXML
    private void atualizarPaciente() {
        Paciente p = choicePaciente.getValue();

        if (p == null) {
            alerta("Erro", "Selecione um paciente.");
            return;
        }

        try {
            p.setNome(inputNome.getText());
            p.setCpf(inputCpf.getText());
            p.setTelefone(inputTelefone.getText());
            p.setEmail(inputEmail.getText());
            p.setEndereco(inputEndereco.getText());
            p.setDataNascimento(inputNascimento.getText());

            dao.update(p);

            alerta("Sucesso", "Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            alerta("Erro", "Erro ao atualizar paciente.");
        }
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(titulo);
        a.setContentText(msg);
        a.showAndWait();
    }
}
