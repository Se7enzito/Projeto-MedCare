package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.model.Especialidade;

import java.sql.SQLException;

public class ListarEspecialidadeCommand implements Command {

    private final EspecialidadeDAO dao = new EspecialidadeDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Listar Pacientes");

        for (Especialidade especialidade : dao.getAll()) {
            System.out.println(especialidade.getId() + " - " + especialidade.getNome() + ": " + especialidade.getDescricao());
        }
    }

    public String getName() { return "Listar Paciente"; }

}
