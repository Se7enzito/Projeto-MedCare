package org.projeto.API.fxml.atualizar.select;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

public class SelecionarPacienteAtualizarController {

    @FXML private TableView<Paciente> tablePacientes;
    @FXML private TableColumn<Paciente, String> colNome;
    @FXML private TableColumn<Paciente, String> colCpf;

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public static Paciente pacienteSelecionado;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colCpf.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCpf()));

        try {
            tablePacientes.setItems(FXCollections.observableArrayList(pacienteDAO.getAll()));
        } catch (Exception ignored) {}
    }

    @FXML
    private void selecionar() {
        pacienteSelecionado = tablePacientes.getSelectionModel().getSelectedItem();

        if (pacienteSelecionado == null) {
            alerta("Aviso", "Selecione um paciente.");
            return;
        }

        FXMLManager.showScene("atualizarPaciente.fxml", "Atualizar Paciente");
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(titulo);
        a.setContentText(msg);
        a.show();
    }
}
