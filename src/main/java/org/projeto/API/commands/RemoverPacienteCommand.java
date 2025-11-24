package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.PacienteDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverPacienteCommand implements Command {
    private final PacienteDAO dao = new PacienteDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Remover Paciente");

        new ListarPacienteCommand().execute();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Paciente que vocẽ quer remover: ");
        int id = scanner.nextInt();

        dao.delete(id);

        scanner.close();
    }

    public String getName() { return "Remover Paciente"; }

}
