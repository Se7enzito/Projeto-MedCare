package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;
import java.util.Scanner;

public class CriarEspecialidadeCommand implements Command {

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opção selecionada: Criar Especialidade");

        Especialidade especialidade = new Especialidade();

        System.out.print("Digite o nome do especialidade: ");
        especialidade.setNome(scanner.next());

        System.out.print("Digite a descrição: ");
        especialidade.setDescricao(scanner.next());

        dao.insert(especialidade);

        scanner.close();
        System.out.println("Especialidade criada com sucesso!");
    }

    public String getName() { return "Criar Especialidade"; }
}
