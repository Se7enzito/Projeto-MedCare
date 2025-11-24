package org.projeto.API.fxml.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Especialidade;
import org.projeto.database.model.Medico;

import java.sql.SQLException;

public class CadastroMedicoController {

    private final static EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    private final static MedicoDAO dao = new MedicoDAO();

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputTelefone;

    @FXML
    private TextField inputEmail;

    @FXML
    private ChoiceBox<Especialidade> choiceEspecialidade;

    @FXML
    public void initialize() throws SQLException {
        carregarEspecialidades();
    }

    private void carregarEspecialidades() throws SQLException {
        choiceEspecialidade.getItems().clear();

        for (Especialidade especialidade : especialidadeDAO.getAll()) {
            choiceEspecialidade.getItems().add(especialidade);
        }

        choiceEspecialidade.getSelectionModel().selectFirst();
    }

    @FXML
    private void cadastrarMedico() throws SQLException {
        if (!validar()) return;

        System.out.println("=== CADASTRANDO MÉDICO ===");
        System.out.println("Nome: " + inputNome.getText());
        System.out.println("CPF: " + inputCpf.getText());
        System.out.println("Telefone: " + inputTelefone.getText());
        System.out.println("Email: " + inputEmail.getText());

        Especialidade especialidade = choiceEspecialidade.getValue();

        System.out.println("Especialidade Id: " + especialidade.getId());
        System.out.println("Especialidade Nome: " + especialidade.getNome());

        int cpfInt;
        try {
            cpfInt = Integer.parseInt(inputCpf.getText());
        } catch (NumberFormatException e) {
            alerta("Error!", "O CPF deve ser composto apenas por números!");
            throw new RuntimeException(e);
        }

        Medico medico = new Medico(inputNome.getText(), cpfInt, inputTelefone.getText(), inputEmail.getText(), especialidade.getId());
        
        dao.insert(medico);

        alerta("Sucesso", "Médico cadastrado com sucesso!");

        limpar();
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private boolean validar() {
        if (inputNome.getText().isEmpty() ||
                inputCpf.getText().isEmpty() ||
                inputTelefone.getText().isEmpty() ||
                inputEmail.getText().isEmpty() ||
                choiceEspecialidade.getValue() == null) {

            alerta("Campos obrigatórios", "Preencha todos os campos.");
            return false;
        }
        return true;
    }

    private void limpar() {
        inputNome.clear();
        inputCpf.clear();
        inputTelefone.clear();
        inputEmail.clear();
        choiceEspecialidade.getSelectionModel().selectFirst();
    }

    private void alerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}