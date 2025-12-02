package org.projeto.database.dao;

import org.projeto.database.DatabaseConnection;
import org.projeto.database.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insert(Paciente p) throws SQLException {
        String sql = "INSERT INTO pacientes (idClinica, nome, cpf, dataNascimento, telefone, email, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, p.getidClinica());
        stmt.setString(2, p.getNome());
        stmt.setString(3, p.getCpf());
        stmt.setString(4, p.getDataNascimento());
        stmt.setString(5, p.getTelefone());
        stmt.setString(6, p.getEmail());
        stmt.setString(7, p.getEndereco());

        stmt.executeUpdate();
    }

    public Paciente getById(int id) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Paciente p = new Paciente();
            p.setId(rs.getInt("id"));
            p.setidClinica(rs.getInt("idClinica"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setDataNascimento(rs.getString("dataNascimento"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            p.setEndereco(rs.getString("endereco"));
            return p;
        }

        return null;
    }

    public List<Paciente> getAll() throws SQLException {
        String sql = "SELECT * FROM pacientes";
        Connection conn = DatabaseConnection.getConnection();

        List<Paciente> lista = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Paciente p = new Paciente();
            p.setId(rs.getInt("id"));
            p.setidClinica(rs.getInt("idClinica"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setDataNascimento(rs.getString("dataNascimento"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            p.setEndereco(rs.getString("endereco"));

            lista.add(p);
        }

        return lista;
    }

    public List<Paciente> getAll(int idClinica) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE idClinica = ?";
        Connection conn = DatabaseConnection.getConnection();

        List<Paciente> lista = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idClinica);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Paciente p = new Paciente();
            p.setId(rs.getInt("id"));
            p.setidClinica(rs.getInt("idClinica"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setDataNascimento(rs.getString("dataNascimento"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            p.setEndereco(rs.getString("endereco"));

            lista.add(p);
        }

        return lista;
    }

    public void update(Paciente p) throws SQLException {
        String sql = "UPDATE pacientes SET nome=?, cpf=?, dataNascimento=?, telefone=?, email=?, endereco=? WHERE id=?";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getCpf());
        stmt.setString(3, p.getDataNascimento());
        stmt.setString(4, p.getTelefone());
        stmt.setString(5, p.getEmail());
        stmt.setString(6, p.getEndereco());
        stmt.setInt(7, p.getId());

        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
    }

}