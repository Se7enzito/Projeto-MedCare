package org.projeto.API.fxml.atualizar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;

public class AtualizarEspecialidadeController {

    @FXML private ChoiceBox<Especialidade> choiceEspecialidade;

    @FXML private TextField inputNome;
    @FXML private TextArea inputDescricao;

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    @FXML
    public void initialize() {
        carregarEspecialidades();
        choiceEspecialidade.getSelectionModel().selectedItemProperty().addListener((obs, oldV, novo) -> {
            if (novo != null) preencherCampos(novo);
        });
    }

    private void carregarEspecialidades() {
        try {
            choiceEspecialidade.getItems().clear();
            choiceEspecialidade.getItems().addAll(dao.getAll());

            if (!choiceEspecialidade.getItems().isEmpty()) {
                choiceEspecialidade.getSelectionModel().selectFirst();
                preencherCampos(choiceEspecialidade.getValue());
            }

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar especialidades.");
        }
    }

    private void preencherCampos(Especialidade e) {
        inputNome.setText(e.getNome());
        inputDescricao.setText(e.getDescricao());
    }

    @FXML
    private void atualizarEspecialidade() {
        Especialidade e = choiceEspecialidade.getValue();

        if (e == null) {
            alerta("Aviso", "Selecione uma especialidade.");
            return;
        }

        try {
            e.setNome(inputNome.getText());
            e.setDescricao(inputDescricao.getText());

            dao.update(e);

            alerta("Sucesso", "Especialidade atualizada com sucesso!");
        } catch (SQLException ex) {
            alerta("Erro", "Erro ao atualizar especialidade.");
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
