package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Medico;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarMedicoCommand implements Command {

    private final MedicoDAO dao = new MedicoDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Atualizar Médico");

        Scanner scanner = new Scanner(System.in);

        Medico medico = new Medico();

        new ListarMedicoCommand().execute();

        System.out.print("Digite o ID do médico: ");
        medico.setId(scanner.nextInt());

        System.out.print("Digite o novo nome do médico: ");
        medico.setNome(scanner.next());

        System.out.print("Digite o novo crm do médico: ");
        medico.setCrm(scanner.nextInt());

        System.out.print("Digite o novo telefone do médico: ");
        medico.setTelefone(scanner.next());

        System.out.print("Digite o novo email do médico: ");
        medico.setEmail(scanner.next());

        new ListarEspecialidadeCommand().execute();

        System.out.print("Digite o ID da nova especialidade: ");
        medico.setEspecialidadeId(scanner.nextInt());

        dao.update(medico);

        scanner.close();
        System.out.println("Dados do médico atualizado com sucesso!");
    }

    public String getName() { return "Atualizar Médico"; }

}
