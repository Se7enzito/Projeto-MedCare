package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.MedicoDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverMedicoCommand implements Command {
    private final MedicoDAO dao = new MedicoDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Remover Médico");

        new ListarMedicoCommand().execute();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do médico que você quer remover: ");
        int id = scanner.nextInt();

        dao.delete(id);

        scanner.close();
    }

    public String getName() { return "Remover Médico"; }
}
