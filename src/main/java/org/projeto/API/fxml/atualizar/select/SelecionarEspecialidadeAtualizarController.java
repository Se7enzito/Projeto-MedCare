package org.projeto.API.fxml.atualizar.select;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import org.projeto.API.FXMLManager;
import org.projeto.Main;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

public class SelecionarEspecialidadeAtualizarController {

    @FXML private TableView<Especialidade> tableEspecialidades;
    @FXML private TableColumn<Especialidade, String> colNome;
    @FXML private TableColumn<Especialidade, String> colDescricao;

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    public static Especialidade especialidadeSelecionada;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colDescricao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescricao()));

        try {
            tableEspecialidades.setItems(FXCollections.observableArrayList(dao.getAll(Main.getIdClinica())));
        } catch (Exception ignored) {}
    }

    @FXML
    private void selecionar() {
        especialidadeSelecionada = tableEspecialidades.getSelectionModel().getSelectedItem();

        if (especialidadeSelecionada == null) {
            alerta("Aviso", "Selecione uma especialidade.");
            return;
        }

        FXMLManager.showScene("atualizarEspecialidade.fxml", "Atualizar Especialidade");
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
