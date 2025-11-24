package org.projeto.API;

import org.projeto.API.commands.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GerenciadorAPI {

    private final List<Command> commands = List.of(
            new AdicionarMedicoCommand(),
            new AdicionarPacienteCommand(),
            new CriarEspecialidadeCommand(),
            new CriarConsultaCommand(),
            new AtualizarMedicoCommand(),
            new AtualizarPacienteCommand(),
            new AtualizarEspecialidadeCommand(),
            new AtualizarConsultaCommand(),
            new RemoverMedicoCommand(),
            new RemoverPacienteCommand(),
            new RemoverEspecialidadeCommand(),
            new RemoverConsultaCommand(),
            new ListarMedicoCommand(),
            new ListarPacienteCommand(),
            new ListarEspecialidadeCommand(),
            new ListarConsultaCommand()
    );

    public void runApp(boolean terminal) {

        if (terminal) {
            executarTerminal();
        } else {
            System.out.println("Carregando interface gráfica...");
            iniciarInterfaceFXML();
        }
    }

    private void iniciarInterfaceFXML() {
        try {
            FXMLManager.showScene("inicial.fxml", "MedCare");
        } catch (Exception e) {
            System.out.println("Erro ao carregar interface FXML:");
            e.printStackTrace();
        }
    }

    private void executarTerminal() {
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\nMedCare - Menu:");

            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + " - " + commands.get(i).getName());
            }

            System.out.println((commands.size() + 1) + " - Sair");
            System.out.print("Digite a opção desejada: ");

            int opc = sc.nextInt();

            if (opc == commands.size() + 1) {
                System.out.println("Saindo do MedCare...");
                rodando = false;

            } else if (opc > 0 && opc <= commands.size()) {
                try {
                    commands.get(opc - 1).execute();
                } catch (SQLException e) {
                    System.out.println("Erro ao executar comando:");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
