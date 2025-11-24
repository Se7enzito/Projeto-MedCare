package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarEspecialidadeCommand implements Command {

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Atualizar Especialidade");

        new ListarEspecialidadeCommand().execute();

        Scanner scanner = new Scanner(System.in);

        Especialidade especialidade = new Especialidade();

        System.out.print("Digite o ID da especialidade: ");
        especialidade.setId(scanner.nextInt());

        System.out.print("Digite o novo nome da especialidade: ");
        especialidade.setNome(scanner.next());

        System.out.print("Digite a nova descrição da especialidade: ");
        especialidade.setDescricao(scanner.next());

        dao.update(especialidade);

        scanner.close();
        System.out.println("Especialidade atualizada com sucesso!");
    }

    public String getName() { return "Atualizar Especialidade"; }

}
