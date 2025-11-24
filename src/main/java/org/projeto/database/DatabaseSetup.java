package org.projeto.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Tabela pacientes
            String createPacientesTable = """
                CREATE TABLE IF NOT EXISTS pacientes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT,
                    cpf TEXT,
                    dataNascimento TEXT,
                    telefone TEXT,
                    email TEXT,
                    endereco TEXT
                );
            """;

            // Tabela prontuario
            String createProntuarioTable = """
                CREATE TABLE IF NOT EXISTS prontuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pacienteId INTEGER,
                    historicoMedico TEXT,
                    alergias TEXT,
                    medicacoes TEXT,
                    FOREIGN KEY (pacienteId) REFERENCES pacientes(id) ON DELETE CASCADE
                );
            """;

            // Tabela especialidade
            String createEspecialidadeTable = """
                CREATE TABLE IF NOT EXISTS especialidade (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT,
                    descricao TEXT
                );
            """;

            // Tabela medico
            String createMedicoTable = """
                CREATE TABLE IF NOT EXISTS medico (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT,
                    crm INTEGER,
                    telefone TEXT,
                    email TEXT,
                    especialidadeId INTEGER,
                    FOREIGN KEY (especialidadeId) REFERENCES especialidade(id) ON DELETE CASCADE
                );
            """;

            // Tabela consulta
            String createConsultaTable = """
                CREATE TABLE IF NOT EXISTS consulta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    data TEXT,
                    hora TEXT,
                    status TEXT,
                    observacoes TEXT,
                    pacienteId INTEGER,
                    medicoId INTEGER,
                    FOREIGN KEY (pacienteId) REFERENCES pacientes(id) ON DELETE CASCADE,
                    FOREIGN KEY (medicoId) REFERENCES medico(id) ON DELETE CASCADE
                );
            """;

            // Execução das tabelas
            statement.executeUpdate(createPacientesTable);
            statement.executeUpdate(createProntuarioTable);
            statement.executeUpdate(createEspecialidadeTable);
            statement.executeUpdate(createMedicoTable);
            statement.executeUpdate(createConsultaTable);

            System.out.println("Banco de dados inicializado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a estrutura do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

}