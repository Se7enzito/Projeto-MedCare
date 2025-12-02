package org.projeto.API.fxml.remover;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import org.projeto.API.FXMLManager;
import org.projeto.Main;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;

public class RemoverEspecialidadeController {

    @FXML private TableView<Especialidade> tableEspecialidades;
    @FXML private TableColumn<Especialidade, String> colNome;
    @FXML private TableColumn<Especialidade, String> colDescricao;

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colDescricao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescricao()));

        atualizarTabela();
    }

    @FXML
    private void atualizarTabela() {
        try {
            tableEspecialidades.getItems().setAll(dao.getAll(Main.getIdClinica()));

        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar especialidades.");
        }
    }

    @FXML
    private void removerSelecionada() {
        Especialidade e = tableEspecialidades.getSelectionModel().getSelectedItem();

        if (e == null) {
            alerta("Aviso", "Nenhuma especialidade selecionada.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Remover Especialidade");
        confirm.setContentText("Deseja remover a especialidade: " + e.getNome() + "?");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                dao.delete(e.getId());
                atualizarTabela();

                alerta("Removida", "Especialidade removida com sucesso!");

            } catch (SQLException ex) {
                alerta("Erro", "Erro ao remover especialidade.");
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
