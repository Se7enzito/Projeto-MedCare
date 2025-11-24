package org.projeto.API.fxml.atualizar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Consulta;
import org.projeto.database.model.Paciente;
import org.projeto.database.model.Medico;

public class AtualizarConsultaController {

    @FXML private ChoiceBox<Paciente> choicePaciente;
    @FXML private ChoiceBox<Medico> choiceMedico;

    @FXML private TextField inputData;
    @FXML private TextField inputHora;
    @FXML private ChoiceBox<String> choiceStatus;
    @FXML private TextArea inputObs;

    public static Consulta consultaSelecionada;

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    private Consulta consultaOriginal;

    @FXML
    public void initialize() {
        try {
            choicePaciente.getItems().addAll(pacienteDAO.getAll());
            choiceMedico.getItems().addAll(medicoDAO.getAll());
            choiceStatus.getItems().addAll("Agendada", "Concluída", "Cancelada");

            if (consultaSelecionada != null)
                carregarConsulta(consultaSelecionada);

        } catch (Exception e) {
            alerta("Erro", "Erro ao carregar dados!");
        }
    }

    /** CHAMADO AO ABRIR A TELA */
    public void carregarConsulta(Consulta c) {
        this.consultaOriginal = c;

        inputData.setText(c.getData());
        inputHora.setText(c.getHora());
        inputObs.setText(c.getObservacoes());
        choiceStatus.setValue(c.getStatus());

        choicePaciente.getSelectionModel().select(
                choicePaciente.getItems().stream().filter(p -> p.getId() == c.getPacienteId()).findFirst().get()
        );

        choiceMedico.getSelectionModel().select(
                choiceMedico.getItems().stream().filter(m -> m.getId() == c.getMedicoId()).findFirst().get()
        );
    }

    @FXML
    private void salvar() {
        try {
            consultaOriginal.setData(inputData.getText());
            consultaOriginal.setHora(inputHora.getText());
            consultaOriginal.setObservacoes(inputObs.getText());
            consultaOriginal.setStatus(choiceStatus.getValue());
            consultaOriginal.setPacienteId(choicePaciente.getValue().getId());
            consultaOriginal.setMedicoId(choiceMedico.getValue().getId());

            consultaDAO.update(consultaOriginal);

            alerta("Sucesso", "Consulta atualizada com sucesso!");
            voltar();

        } catch (Exception e) {
            alerta("Erro", "Erro ao atualizar consulta!");
        }
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("selecionarAtualizarConsulta.fxml", "Consultas");
    }

    private void alerta(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(t);
        a.setContentText(m);
        a.show();
    }
}
