package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.dao.PacienteDAO;
import org.projeto.database.model.Consulta;
import org.projeto.database.model.Medico;
import org.projeto.database.model.Paciente;

import java.sql.SQLException;
import java.util.Scanner;

public class CriarConsultaCommand implements Command {

    private final ConsultaDAO dao = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opção selecionada: Criar Consulta");

        Consulta consulta = new Consulta();

        System.out.print("Digite a data da consulta: ");
        consulta.setData(scanner.nextLine());

        System.out.print("Digite o horário da consulta: ");
        consulta.setHora(scanner.nextLine());

        System.out.print("Digite o status da consulta: ");
        consulta.setStatus(scanner.nextLine());

        System.out.print("Digite as observações da consulta: ");
        consulta.setObservacoes(scanner.nextLine());

        for (Paciente paciente : pacienteDAO.getAll()) {
            System.out.println(paciente.getId() + " - " + paciente.getNome());
        }

        System.out.print("Digite o id do paciente da consulta: ");
        consulta.setPacienteId(scanner.nextInt());

        for (Medico medico : medicoDAO.getAll()) {
            System.out.println(medico.getId() + " - " + medico.getNome());
        }

        System.out.print("Digite o id do médico da consulta: ");
        consulta.setMedicoId(scanner.nextInt());

        dao.insert(consulta);

        scanner.close();
        System.out.println("Consulta criada com sucesso!");
    }

    public String getName() { return "Criar Consulta"; }
}
