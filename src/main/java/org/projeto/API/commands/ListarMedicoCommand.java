package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;

import java.sql.SQLException;

public class ListarMedicoCommand implements Command {

    private final MedicoDAO dao = new MedicoDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Listar Médicos");

        for (Medico medico : dao.getAll()) {
            System.out.println("Informações do médico " + medico.getNome() + ":");
            System.out.println("- Id: " + medico.getId());
            System.out.println("- Nome: " + medico.getNome());
            System.out.println("- CRM: " + medico.getCrm());
            System.out.println("- Telefone: " + medico.getTelefone());
            System.out.println("- EMail: " + medico.getEmail());
            System.out.println("- Especialidade Id: " + medico.getEspecialidadeId());
        }
    }

    public String getName() { return "Listar Médico"; }
}
