package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarPacienteCommand implements Command {

    private final PacienteDAO dao = new PacienteDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Atualizar Paciente");

        Scanner scanner = new Scanner(System.in);

        Paciente paciente = new Paciente();

        new ListarPacienteCommand().execute();

        System.out.print("Digite o ID do paciente que você deseja atualizar: ");
        paciente.setId(scanner.nextInt());

        System.out.print("Digite o novo nome: ");
        paciente.setNome(scanner.next());

        System.out.print("Digite o novo cpf: ");
        paciente.setCpf(scanner.next());

        System.out.print("Digite a nova data de nascimento: ");
        paciente.setDataNascimento(scanner.next());

        System.out.print("Digite o novo telefone: ");
        paciente.setTelefone(scanner.next());

        System.out.print("Digite o novo email: ");
        paciente.setEmail(scanner.next());

        System.out.print("Digite o endereço: ");
        paciente.setEndereco(scanner.next());

        dao.update(paciente);

        scanner.close();
        System.out.println("Dados do paciente atualizado com sucesso");
    }

    public String getName() { return "Atualizar Paciente"; }

}
