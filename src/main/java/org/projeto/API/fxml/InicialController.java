package org.projeto.API.fxml;

import javafx.fxml.FXML;
import org.projeto.API.FXMLManager;
import org.projeto.Main;

public class InicialController {

    @FXML
    private void adicionarPaciente() {
        System.out.println("Adicionar Paciente");

        FXMLManager.showScene("adicionarPaciente.fxml", "MedCare - Adicionar Paciente");
    }

    @FXML
    private void listarPacientes() {
        System.out.println("Listar Pacientes");

        FXMLManager.showScene("listarPacientes.fxml", "MedCare - Listar Pacientes");
    }

    @FXML
    private void atualizarPaciente() {
        System.out.println("Atualizar Paciente");

        FXMLManager.showScene("selecionarPacienteAtualizar.fxml", "MedCare - Atualizar Paciente");
    }

    @FXML
    private void removerPaciente() {
        System.out.println("Remover Paciente");

        FXMLManager.showScene("removerPaciente.fxml", "MedCare - Remover Paciente");
    }

    @FXML
    private void adicionarMedico() {
        System.out.println("Adicionar Medico");

        FXMLManager.showScene("adicionarMedico.fxml", "MedCare - Adicionar Médico");
    }

    @FXML
    private void listarMedicos() {
        System.out.println("Listar Medicos");

        FXMLManager.showScene("listarMedicos.fxml", "MedCare - Listar Medicos");
    }

    @FXML
    private void atualizarMedico() {
        System.out.println("Atualizar Medico");

        FXMLManager.showScene("selecionarAtualizarMedico.fxml", "MedCare - Atualizar Medico");
    }

    @FXML
    private void removerMedico() {
        System.out.println("Remover Medico");

        FXMLManager.showScene("removerMedico.fxml", "MedCare - Remover Medico");
    }

    @FXML
    private void adicionarConsulta() {
        System.out.println("Adicionar Consulta");

        FXMLManager.showScene("cadastrarConsulta.fxml", "MedCare - Cadastrar Consulta");
    }

    @FXML
    private void listarConsultas() {
        System.out.println("Listar Consultas");

        FXMLManager.showScene("listarConsulta.fxml", "MedCare - Listar Consultas");
    }

    @FXML
    private void atualizarConsulta() {
        System.out.println("Atualizar Consulta");

        FXMLManager.showScene("selecionarAtualizarConsulta.fxml", "MedCare - Atualizar Consulta");
    }

    @FXML
    private void removerConsulta() {
        System.out.println("Remover Consulta");

        FXMLManager.showScene("removerConsulta.fxml", "MedCare - Remover Consulta");
    }

    @FXML
    private void adicionarEspecialidade() {
        System.out.println("Adicionar Especialidade");

        FXMLManager.showScene("cadastrarEspecialidade.fxml", "MedCare - Criar Especialidade");
    }

    @FXML
    private void listarEspecialidade() {
        System.out.println("Listar Especialidade");

        FXMLManager.showScene("listarEspecialidades.fxml", "MedCare - Listar Especialidade");
    }

    @FXML
    private void atualizarEspecialidade() {
        System.out.println("Atualizar Especialidade");

        FXMLManager.showScene("selecionarEspecialidadeAtualizar.fxml", "MedCare - Atualizar Especialidade");
    }

    @FXML
    private void removerEspecialidade() {
        System.out.println("Remover Especialidade");

        FXMLManager.showScene("removerEspecialidade.fxml", "MedCare - Remover Especialidade");
    }

    @FXML
    private void sair() {
        System.out.println("Saindo da clínica...");

        Main.setIdClinica(0);

        System.exit(0);
    }
}