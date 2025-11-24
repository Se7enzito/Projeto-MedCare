package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;

public class ListarPacienteCommand implements Command {

    private final PacienteDAO dao = new PacienteDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Listar Pacientes");

        for (Paciente paciente : dao.getAll()) {
            System.out.println("Informações do paciente: " + paciente.getNome());
            System.out.println("- ID: " + paciente.getId());
            System.out.println("- Nome: " + paciente.getNome());
            System.out.println("- CPF: " + paciente.getCpf());
            System.out.println("- Data Nascimento: " + paciente.getDataNascimento());
            System.out.println("- Telefone: " + paciente.getTelefone());
            System.out.println("- Email: " + paciente.getEmail());
            System.out.println("- Endereço: " + paciente.getEndereco());
        }
    }

    public String getName() { return "Listar Paciente"; }

}
