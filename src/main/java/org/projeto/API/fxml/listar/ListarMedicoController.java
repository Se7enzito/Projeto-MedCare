package org.projeto.API.fxml.listar;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.API.utils.TelefoneUtils;
import org.projeto.Main;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;
import javafx.beans.property.SimpleStringProperty;

public class ListarMedicoController {

    @FXML private TextField inputBusca;
    @FXML private TableView<Medico> tableMedicos;

    @FXML private TableColumn<Medico, String> colNome;
    @FXML private TableColumn<Medico, String> colCrm;
    @FXML private TableColumn<Medico, String> colTelefone;

    private final MedicoDAO medicoDAO = new MedicoDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colCrm.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getCrm())));
        colTelefone.setCellValueFactory(c -> new SimpleStringProperty(TelefoneUtils.formatPhone(c.getValue().getTelefone())));

        atualizar();

        inputBusca.textProperty().addListener((obs, oldV, newV) -> filtrar(newV));
    }

    public void atualizar() {
        try {
            tableMedicos.setItems(FXCollections.observableArrayList(medicoDAO.getAll(Main.getIdClinica())));
        } catch (Exception e) {
            alerta("Erro", "Não foi possível carregar os médicos.");
        }
    }

    private void filtrar(String texto) {
        if (texto == null || texto.isEmpty()) {
            atualizar();
            return;
        }

        ObservableList<Medico> filtrados = FXCollections.observableArrayList();

        for (Medico m : tableMedicos.getItems()) {
            if (m.getNome().toLowerCase().contains(texto.toLowerCase()) ||
                    String.valueOf(m.getCrm()).contains(texto)) {
                filtrados.add(m);
            }
        }

        tableMedicos.setItems(filtrados);
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
