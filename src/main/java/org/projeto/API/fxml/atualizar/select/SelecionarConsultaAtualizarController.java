package org.projeto.API.fxml.atualizar.select;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import org.projeto.API.FXMLManager;
import org.projeto.API.fxml.atualizar.AtualizarConsultaController;
import org.projeto.Main;
import org.projeto.database.dao.*;
import org.projeto.database.model.*;

public class SelecionarConsultaAtualizarController {

    @FXML private TableView<Consulta> tableConsultas;

    @FXML private TableColumn<Consulta, String> colData;
    @FXML private TableColumn<Consulta, String> colHora;
    @FXML private TableColumn<Consulta, String> colPaciente;
    @FXML private TableColumn<Consulta, String> colMedico;

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

    @FXML
    public void initialize() {
        colData.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getData()));
        colHora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHora()));

        colPaciente.setCellValueFactory(c -> {
            try {
                return new SimpleStringProperty(pacienteDAO.getById(c.getValue().getPacienteId()).getNome());
            } catch (Exception e) {
                return new SimpleStringProperty("-");
            }
        });

        colMedico.setCellValueFactory(c -> {
            try {
                return new SimpleStringProperty(medicoDAO.getById(c.getValue().getMedicoId()).getNome());
            } catch (Exception e) {
                return new SimpleStringProperty("-");
            }
        });

        atualizarLista();
    }

    private void atualizarLista() {
        try {
            tableConsultas.setItems(FXCollections.observableArrayList(consultaDAO.getAll(Main.getIdClinica())));
        } catch (Exception e) {
            alerta("Erro", "Erro ao carregar consultas.");
        }
    }

    @FXML
    private void selecionar() {
        Consulta consulta = tableConsultas.getSelectionModel().getSelectedItem();

        if (consulta == null) {
            alerta("Aviso", "Selecione uma consulta!");
            return;
        }

        // Salva a consulta escolhida em memória estática
        AtualizarConsultaController.consultaSelecionada = consulta;

        // Abre a tela de edição
        FXMLManager.showScene("atualizarConsulta.fxml", "Atualizar Consulta");
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
