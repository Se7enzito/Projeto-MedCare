package org.projeto.API.fxml.listar;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.Main;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;
import javafx.beans.property.SimpleStringProperty;

public class ListarPacienteController {

    @FXML private TextField inputBusca;
    @FXML private TableView<Paciente> tablePacientes;

    @FXML private TableColumn<Paciente, String> colNome;
    @FXML private TableColumn<Paciente, String> colCpf;
    @FXML private TableColumn<Paciente, String> colTelefone;

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colCpf.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCpf()));
        colTelefone.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefone()));

        atualizar();

        inputBusca.textProperty().addListener((obs, oldV, newV) -> filtrar(newV));
    }

    public void atualizar() {
        try {
            tablePacientes.setItems(FXCollections.observableArrayList(pacienteDAO.getAll(Main.getIdClinica())));
        } catch (Exception e) {
            alerta("Erro", "Não foi possível carregar os pacientes.");
        }
    }

    private void filtrar(String texto) {
        if (texto == null || texto.isEmpty()) {
            atualizar();
            return;
        }

        ObservableList<Paciente> filtrados = FXCollections.observableArrayList();

        for (Paciente p : tablePacientes.getItems()) {
            if (p.getNome().toLowerCase().contains(texto.toLowerCase()) ||
                    p.getCpf().contains(texto)) {
                filtrados.add(p);
            }
        }

        tablePacientes.setItems(filtrados);
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
