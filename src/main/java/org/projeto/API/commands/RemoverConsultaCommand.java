package org.projeto.API.commands;

import org.projeto.API.Command;
import org.projeto.database.dao.ConsultaDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverConsultaCommand implements Command {

    private final ConsultaDAO dao = new ConsultaDAO();

    public void execute() throws SQLException {
        System.out.println("Opção selecionada: Remover Consulta");

        new ListarConsultaCommand().execute();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da consulta que vocẽ quer remover: ");
        int id =  scanner.nextInt();

        dao.delete(id);

        scanner.close();
        System.out.println("Consulta removida com sucesso!");
    }

    public String getName() { return "Remover Consulta";}

}
