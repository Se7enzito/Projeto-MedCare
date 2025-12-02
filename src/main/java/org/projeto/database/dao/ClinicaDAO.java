package org.projeto.database.dao;

import org.projeto.database.DatabaseConnection;
import org.projeto.database.model.Clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicaDAO {

    public void insert(Clinica c) throws SQLException {
        String sql = "INSERT INTO clinicas (email, senha) VALUES (?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, c.getEmail());
        stmt.setString(2, c.getSenha());
        stmt.executeUpdate();
    }

    public Clinica getById(int id) throws SQLException {
        String sql = "SELECT * FROM clinicas WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Clinica c = new Clinica();
            c.setId(rs.getInt("id"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            return c;
        }

        return null;
    }

    public List<Clinica> getAll() throws SQLException {
        String sql = "SELECT * FROM clinicas";
        Connection conn = DatabaseConnection.getConnection();

        List<Clinica> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Clinica c = new Clinica();
            c.setId(rs.getInt("id"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            lista.add(c);
        }

        return lista;
    }

    public Clinica getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM clinicas WHERE email = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Clinica c = new Clinica();
            c.setId(rs.getInt("id"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            return c;
        }

        return null;
    }

    public void update(Clinica c) throws SQLException {
        String sql = "UPDATE clinicas SET email=?, senha=? WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, c.getEmail());
        stmt.setString(2, c.getSenha());
        stmt.setInt(3, c.getId());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clinicas WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}