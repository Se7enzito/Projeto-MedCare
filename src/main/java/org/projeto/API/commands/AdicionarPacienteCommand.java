package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;
import java.util.Scanner;

public class AdicionarPacienteCommand implements Command {

    private final PacienteDAO dao = new PacienteDAO();

    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opção selecionada: Adicionar Paciente");

        Paciente paciente = new Paciente();

        System.out.print("Digite o nome do paciente: ");
        paciente.setNome(scanner.next());

        System.out.print("Digite o cpf do paciente: ");
        paciente.setCpf(scanner.next());

        System.out.print("Digite a data de nascimento do paciente: ");
        paciente.setDataNascimento(scanner.next());

        System.out.print("Digite o telefone do paciente: ");
        paciente.setTelefone(scanner.next());

        System.out.print("Digite o endereco do paciente: ");
        paciente.setEndereco(scanner.next());

        System.out.println("Digite o email do paciente: ");
        paciente.setEmail(scanner.next());

        dao.insert(paciente);

        scanner.close();
        System.out.println("Paciente adicionado com sucesso!");
    }

    public String getName() { return "Adicionar Paciente"; }

}
