package org.projeto.API.fxml.listar;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

public class ListarEspecialidadeController {

    @FXML private TextField inputBusca;
    @FXML private TableView<Especialidade> tableEspecialidades;

    @FXML private TableColumn<Especialidade, String> colNome;
    @FXML private TableColumn<Especialidade, String> colDescricao;

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colDescricao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescricao()));

        atualizar();

        inputBusca.textProperty().addListener((obs, oldV, newV) -> filtrar(newV));
    }

    public void atualizar() {
        try {
            tableEspecialidades.setItems(FXCollections.observableArrayList(dao.getAll()));
        } catch (Exception e) {
            alerta("Erro", "Não foi possível carregar as especialidades.");
        }
    }

    private void filtrar(String texto) {
        if (texto == null || texto.isEmpty()) {
            atualizar();
            return;
        }

        ObservableList<Especialidade> filtrados = FXCollections.observableArrayList();

        for (Especialidade e : tableEspecialidades.getItems()) {
            if (e.getNome().toLowerCase().contains(texto.toLowerCase()) ||
                    e.getDescricao().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(e);
            }
        }

        tableEspecialidades.setItems(filtrados);
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(titulo);
        a.setContentText(msg);
        a.show();
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }
}
