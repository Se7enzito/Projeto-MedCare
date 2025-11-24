package org.projeto.API.fxml.atualizar.select;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;

public class SelecionarMedicoAtualizarController {

    @FXML private TableView<Medico> tableMedicos;
    @FXML private TableColumn<Medico, String> colNome;
    @FXML private TableColumn<Medico, String> colCrm;

    private final MedicoDAO medicoDAO = new MedicoDAO();
    public static Medico medicoSelecionado;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colCrm.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getCrm())));

        try {
            tableMedicos.setItems(FXCollections.observableArrayList(medicoDAO.getAll()));
        } catch (Exception ignored) {}
    }

    @FXML
    private void selecionar() {
        medicoSelecionado = tableMedicos.getSelectionModel().getSelectedItem();

        if (medicoSelecionado == null) {
            alerta("Aviso", "Selecione um médico.");
            return;
        }

        FXMLManager.showScene("atualizarMedico.fxml", "Atualizar Médico");
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
