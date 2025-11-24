package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.EspecialidadeDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.model.Especialidade;
import org.projeto.database.model.Medico;

import java.sql.SQLException;
import java.util.Scanner;

public class AdicionarMedicoCommand implements Command {

    private final MedicoDAO dao = new MedicoDAO();
    private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opção Selecionada: Adicionar Médico");

        System.out.print("Digite o nome do medico: ");
        String nome = scanner.next();

        System.out.print("Digite o CRM do medico: ");
        int crm = scanner.nextInt();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.next();

        System.out.print("Digite o email: ");
        String email = scanner.next();
        
        for (Especialidade especialidade : especialidadeDAO.getAll()) {
            System.out.println(especialidade.getId() + " - " + especialidade.getNome());
        }
            
        System.out.print("Digite o especialidade: ");
        int especialidade = scanner.nextInt();

        Medico medico = new Medico(nome, crm, telefone, email, especialidade);

        dao.insert(medico);

        System.out.println("Médico adicionado com sucesso!");
        scanner.close();
    }

    public String getName() { return "Adicionar Médico"; }

}
