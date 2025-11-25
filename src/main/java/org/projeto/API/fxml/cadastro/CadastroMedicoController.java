package org.projeto.API.fxml.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.API.utils.EmailUtils;
import org.projeto.API.utils.TelefoneUtils;
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
    private TextField inputCrm;

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

        String nome = inputNome.getText();
        String crm = inputCrm.getText();
        String telefone = TelefoneUtils.removeSymbolsPhone(inputTelefone.getText());
        String email = inputEmail.getText();

        System.out.println("=== CADASTRANDO MÉDICO ===");
        System.out.println("Nome: " + nome);
        System.out.println("CRM: " + crm);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);

        Especialidade especialidade = choiceEspecialidade.getValue();

        System.out.println("Especialidade Id: " + especialidade.getId());
        System.out.println("Especialidade Nome: " + especialidade.getNome());

        int crmInt;
        try {
            crmInt = Integer.parseInt(crm);
        } catch (NumberFormatException e) {
            alerta("Error!", "O CRM deve ser composto apenas por números!");
            throw new RuntimeException(e);
        }

        Medico medico = new Medico(nome, crmInt, telefone, email, especialidade.getId());
        
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
                inputCrm.getText().isEmpty() ||
                inputTelefone.getText().isEmpty() ||
                inputEmail.getText().isEmpty() ||
                choiceEspecialidade.getValue() == null) {

            alerta("Campos obrigatórios", "Preencha todos os campos.");
            return false;
        }

        String telefoneLimpo = TelefoneUtils.removeSymbolsPhone(inputTelefone.getText());

        if (!TelefoneUtils.isValid(telefoneLimpo, "mobile") && !TelefoneUtils.isValid(telefoneLimpo, "landline")) {
            alerta("Telefone inválido", "O telefone informado é inválido. Verifique e tente novamente.");

            return false;
        }

        if (!EmailUtils.isValidEmail(inputEmail.getText())) {
            mostrarMensagem("Email inválido", "O email informado é inválido. Verifique e tente novamente.");
            return false;
        }

        return true;
    }

    private void limpar() {
        inputNome.clear();
        inputCrm.clear();
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