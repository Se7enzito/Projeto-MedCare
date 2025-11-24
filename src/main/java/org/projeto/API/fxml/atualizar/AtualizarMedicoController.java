package org.projeto.API.fxml.atualizar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;

import java.sql.SQLException;

public class AtualizarMedicoController {

    @FXML private ChoiceBox<Medico> choiceMedico;

    @FXML private TextField inputNome;
    @FXML private TextField inputCrm;
    @FXML private TextField inputTelefone;
    @FXML private TextField inputEmail;
    @FXML private TextField inputEspecialidadeId;

    private final MedicoDAO dao = new MedicoDAO();

    @FXML
    public void initialize() {
        carregarMedicos();

        choiceMedico.getSelectionModel().selectedItemProperty().addListener((obs, oldV, novo) -> {
            if (novo != null) preencherCampos(novo);
        });
    }

    private void carregarMedicos() {
        try {
            choiceMedico.getItems().clear();
            choiceMedico.getItems().addAll(dao.getAll());

            if (!choiceMedico.getItems().isEmpty()) {
                choiceMedico.getSelectionModel().selectFirst();
                preencherCampos(choiceMedico.getValue());
            }

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar médicos.");
        }
    }

    private void preencherCampos(Medico m) {
        inputNome.setText(m.getNome());
        inputCrm.setText(String.valueOf(m.getCrm()));
        inputTelefone.setText(m.getTelefone());
        inputEmail.setText(m.getEmail());
        inputEspecialidadeId.setText(String.valueOf(m.getEspecialidadeId()));
    }

    @FXML
    private void atualizarMedico() {
        Medico m = choiceMedico.getValue();

        if (m == null) {
            alerta("Erro", "Selecione um médico.");
            return;
        }

        try {
            m.setNome(inputNome.getText());
            m.setCrm(Integer.parseInt(inputCrm.getText()));
            m.setTelefone(inputTelefone.getText());
            m.setEmail(inputEmail.getText());
            m.setEspecialidadeId(Integer.parseInt(inputEspecialidadeId.getText()));

            dao.update(m);

            alerta("Sucesso", "Médico atualizado com sucesso!");

        } catch (Exception e) {
            alerta("Erro", "Erro ao atualizar médico.");
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
