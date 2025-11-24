package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.model.Consulta;

import java.sql.SQLException;

public class ListarConsultaCommand implements Command {

    private final ConsultaDAO dao = new ConsultaDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Listar Consultas");

        for (Consulta consulta : dao.getAll()) {
            System.out.println("Informações da consulta: " + consulta.getId());
            System.out.println("- Data da consulta: " + consulta.getData());
            System.out.println("- Hora da consulta: " + consulta.getHora());
            System.out.println("- Status da consulta: " + consulta.getStatus());
            System.out.println("- Observações da consulta: " + consulta.getObservacoes());
            System.out.println("- Paciente Id: " + consulta.getPacienteId());
            System.out.println("- Médico Id: " + consulta.getMedicoId());
        }
    }

    public String getName() { return "Listar Consulta"; }

}
