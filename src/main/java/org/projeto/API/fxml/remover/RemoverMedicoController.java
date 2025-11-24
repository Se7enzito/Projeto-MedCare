package org.projeto.API.fxml.remover;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;

import java.sql.SQLException;

public class RemoverMedicoController {

    @FXML private TableView<Medico> tableMedicos;
    @FXML private TableColumn<Medico, String> colNome;
    @FXML private TableColumn<Medico, String> colCrm;
    @FXML private TableColumn<Medico, String> colTelefone;

    private final MedicoDAO dao = new MedicoDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colCrm.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getCrm())));
        colTelefone.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTelefone()));

        atualizarTabela();
    }

    @FXML
    private void atualizarTabela() {
        try {
            tableMedicos.getItems().setAll(dao.getAll());

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar médicos.");
        }
    }

    @FXML
    private void removerSelecionado() {
        Medico m = tableMedicos.getSelectionModel().getSelectedItem();

        if (m == null) {
            alerta("Selecione um médico", "Nenhum médico selecionado.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Remover Médico");
        confirm.setContentText("Deseja remover o médico: " + m.getNome() + "?");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                dao.delete(m.getId());
                atualizarTabela();
                alerta("Removido", "Médico removido com sucesso!");

            } catch (SQLException e) {
                alerta("Erro", "Erro ao remover médico.");
            }
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
