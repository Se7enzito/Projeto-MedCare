package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.EspecialidadeDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverEspecialidadeCommand implements Command {

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Remover Especialidade");

        new ListarEspecialidadeCommand().execute();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o id da especialidade que você deseja remover: ");
        int id = scanner.nextInt();

        dao.delete(id);

        scanner.close();
        System.out.println("Especialidade deletada com sucesso!");
    }

    public String getName() { return "Remover Especialidade"; }

}
