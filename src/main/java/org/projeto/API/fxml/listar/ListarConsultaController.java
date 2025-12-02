package org.projeto.API.fxml.listar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.projeto.API.FXMLManager;
import org.projeto.Main;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Consulta;
import org.projeto.database.model.Medico;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class ListarConsultaController {

    @FXML private TableView<Consulta> tableConsultas;

    @FXML private TableColumn<Consulta, String> colData;
    @FXML private TableColumn<Consulta, String> colHora;
    @FXML private TableColumn<Consulta, String> colPaciente;
    @FXML private TableColumn<Consulta, String> colMedico;
    @FXML private TableColumn<Consulta, String> colStatus;

    @FXML private TextField campoBusca;
    @FXML private ChoiceBox<String> filtroStatus;

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

    private ObservableList<Consulta> cacheConsultas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarTabela();
        configurarFiltros();
        atualizar();
    }

    private void configurarFiltros() {
        filtroStatus.getItems().addAll("Todos", "Agendada", "Concluída", "Cancelada");
        filtroStatus.getSelectionModel().selectFirst();

        campoBusca.textProperty().addListener((obs, oldV, newV) -> filtrar());
        filtroStatus.valueProperty().addListener((obs, oldV, newV) -> filtrar());
    }

    private void configurarTabela() {
        colData.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getData()));
        colHora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHora()));

        colPaciente.setCellValueFactory(c -> {
            try {
                Paciente p = pacienteDAO.getById(c.getValue().getPacienteId());
                return new SimpleStringProperty(p.getNome());
            } catch (Exception e) {
                return new SimpleStringProperty("Erro");
            }
        });

        colMedico.setCellValueFactory(c -> {
            try {
                Medico m = medicoDAO.getById(c.getValue().getMedicoId());
                return new SimpleStringProperty(m.getNome());
            } catch (Exception e) {
                return new SimpleStringProperty("Erro");
            }
        });

        colStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));

        // STATUS COLORIDO
        colStatus.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                    return;
                }

                setText(status);
                setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: CENTER;");

                switch (status.toLowerCase()) {
                    case "concluída" -> setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                    case "agendada" -> setStyle("-fx-background-color: #FFC107; -fx-text-fill: black; -fx-font-weight: bold;");
                    case "cancelada" -> setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-weight: bold;");
                }
            }
        });
    }

    @FXML
    public void atualizar() {
        try {
            cacheConsultas = FXCollections.observableArrayList(consultaDAO.getAll(Main.getIdClinica()));
            tableConsultas.setItems(cacheConsultas);
            filtrar();
        } catch (SQLException e) {
            alerta("Erro", "Erro ao carregar consultas.");
        }
    }

    private void filtrar() {
        String busca = campoBusca.getText().toLowerCase();
        String statusFiltro = filtroStatus.getValue();

        ObservableList<Consulta> filtrado = FXCollections.observableArrayList();

        for (Consulta c : cacheConsultas) {
            boolean combinaBusca =
                    c.getData().toLowerCase().contains(busca)
                            || c.getHora().toLowerCase().contains(busca);

            try {
                Paciente p = pacienteDAO.getById(c.getPacienteId());
                Medico m = medicoDAO.getById(c.getMedicoId());

                if (p.getNome().toLowerCase().contains(busca)) combinaBusca = true;
                if (m.getNome().toLowerCase().contains(busca)) combinaBusca = true;

            } catch (Exception ignored) {}

            boolean combinaStatus =
                    statusFiltro.equals("Todos") || c.getStatus().equalsIgnoreCase(statusFiltro);

            if (combinaBusca && combinaStatus) filtrado.add(c);
        }

        tableConsultas.setItems(filtrado);
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private void alerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
