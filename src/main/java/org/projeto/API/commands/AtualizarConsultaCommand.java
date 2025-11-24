package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.model.Consulta;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarConsultaCommand implements Command {

    private final ConsultaDAO dao = new ConsultaDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Atualizar Consulta");

        new ListarConsultaCommand().execute();

        Scanner scanner = new Scanner(System.in);

        Consulta consulta = new Consulta();

        System.out.print("Digite o ID da consulta: ");
        consulta.setId(scanner.nextInt());

        System.out.print("Digite a nova data da consulta: ");
        consulta.setData(scanner.next());

        System.out.print("Digite o novo horário da consulta: ");
        consulta.setHora(scanner.next());

        System.out.print("Digite o novo status da consulta: ");
        consulta.setStatus(scanner.next());

        System.out.print("Digite a nova observação da consulta: ");
        consulta.setObservacoes(scanner.next());

        System.out.print("Digite o novo ID do paciente: ");
        consulta.setPacienteId(scanner.nextInt());

        System.out.print("Digite o novo ID do médico: ");
        consulta.setMedicoId(scanner.nextInt());

        scanner.close();
        System.out.println("Consulta atualizada com sucesso!");
    }

    public String getName() { return "Atualizar Consulta"; }

}
