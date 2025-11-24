package org.projeto.API.fxml.remover;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class RemoverPacienteController {

    @FXML private TableView<Paciente> tablePacientes;
    @FXML private TableColumn<Paciente, String> colNome;
    @FXML private TableColumn<Paciente, String> colCpf;
    @FXML private TableColumn<Paciente, String> colTelefone;

    private final PacienteDAO dao = new PacienteDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colCpf.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCpf()));
        colTelefone.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTelefone()));

        atualizarTabela();
    }

    @FXML
    private void atualizarTabela() {
        try {
            tablePacientes.getItems().setAll(dao.getAll());

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar pacientes.");
        }
    }

    @FXML
    private void removerSelecionado() {
        Paciente p = tablePacientes.getSelectionModel().getSelectedItem();

        if (p == null) {
            alerta("Selecione um paciente", "Nenhum paciente selecionado.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Remover Paciente");
        confirm.setContentText("Deseja remover o paciente: " + p.getNome() + "?");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                dao.delete(p.getId());
                atualizarTabela();
                alerta("Removido", "Paciente removido com sucesso!");

            } catch (SQLException e) {
                alerta("Erro", "Erro ao remover paciente.");
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
