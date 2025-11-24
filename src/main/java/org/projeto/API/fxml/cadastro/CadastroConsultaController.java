package org.projeto.API.fxml.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Consulta;
import org.projeto.database.model.Medico;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class CadastroConsultaController {

    @FXML private TextField inputData;
    @FXML private TextField inputHora;
    @FXML private ChoiceBox<String> choiceStatus;

    @FXML private ChoiceBox<Paciente> choicePaciente;
    @FXML private ChoiceBox<Medico> choiceMedico;

    @FXML private TextArea inputObservacoes;

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    @FXML
    public void initialize() throws SQLException {
        carregarStatus();
        carregarPacientes();
        carregarMedicos();
    }

    private void carregarStatus() {
        choiceStatus.getItems().addAll("Agendada", "Concluída", "Cancelada");
        choiceStatus.getSelectionModel().selectFirst();
    }

    private void carregarPacientes() throws SQLException {
        choicePaciente.getItems().addAll(pacienteDAO.getAll());
        choicePaciente.getSelectionModel().selectFirst();
    }

    private void carregarMedicos() throws SQLException {
        choiceMedico.getItems().addAll(medicoDAO.getAll());
        choiceMedico.getSelectionModel().selectFirst();
    }

    @FXML
    private void cadastrarConsulta() throws SQLException {

        if (!validar()) return;

        Paciente p = choicePaciente.getValue();
        Medico m = choiceMedico.getValue();

        Consulta consulta = new Consulta(
                inputData.getText(),
                inputHora.getText(),
                choiceStatus.getValue(),
                inputObservacoes.getText(),
                p.getId(),
                m.getId()
        );

        consultaDAO.insert(consulta);

        alerta("Sucesso", "Consulta cadastrada com sucesso!");
        limpar();
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private boolean validar() {
        if (inputData.getText().isEmpty() ||
                inputHora.getText().isEmpty() ||
                choiceStatus.getValue() == null ||
                choicePaciente.getValue() == null ||
                choiceMedico.getValue() == null) {

            alerta("Campos obrigatórios", "Preencha todos os campos.");
            return false;
        }
        return true;
    }

    private void limpar() {
        inputData.clear();
        inputHora.clear();
        inputObservacoes.clear();
        choiceStatus.getSelectionModel().selectFirst();
        choicePaciente.getSelectionModel().selectFirst();
        choiceMedico.getSelectionModel().selectFirst();
    }

    private void alerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}