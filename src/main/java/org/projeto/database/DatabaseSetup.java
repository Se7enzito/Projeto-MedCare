package org.projeto.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String createClinicasTable = """
                CREATE TABLE IF NOT EXISTS clinicas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    email TEXT,
                    senha TEXT
                );
            """;

            // Tabela pacientes
            String createPacientesTable = """
                CREATE TABLE IF NOT EXISTS pacientes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    idClinica INTEGER,
                    nome TEXT,
                    cpf TEXT,
                    dataNascimento TEXT,
                    telefone TEXT,
                    email TEXT,
                    endereco TEXT,
                    FOREIGN KEY (idClinica) REFERENCES clinicas(id) ON DELETE CASCADE
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
                    idClinica INTEGER,
                    nome TEXT,
                    descricao TEXT,
                    FOREIGN KEY (idClinica) REFERENCES clinicas(id) ON DELETE CASCADE
                );
            """;

            // Tabela medico
            String createMedicoTable = """
                CREATE TABLE IF NOT EXISTS medico (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    idClinica INTEGER,
                    nome TEXT,
                    crm INTEGER,
                    telefone TEXT,
                    email TEXT,
                    especialidadeId INTEGER,
                    FOREIGN KEY (idClinica) REFERENCES clinicas(id) ON DELETE CASCADE,
                    FOREIGN KEY (especialidadeId) REFERENCES especialidade(id) ON DELETE CASCADE
                );
            """;

            // Tabela consulta
            String createConsultaTable = """
                CREATE TABLE IF NOT EXISTS consulta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    idClinica INTEGER,
                    data TEXT,
                    hora TEXT,
                    status TEXT,
                    observacoes TEXT,
                    pacienteId INTEGER,
                    medicoId INTEGER,
                    FOREIGN KEY (idClinica) REFERENCES clinicas(id) ON DELETE CASCADE,
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
            statement.executeUpdate(createClinicasTable);

            System.out.println("Banco de dados inicializado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a estrutura do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

}