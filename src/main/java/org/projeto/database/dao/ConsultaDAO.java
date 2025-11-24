package org.projeto.database.dao;

import org.projeto.database.DatabaseConnection;
import org.projeto.database.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void insert(Consulta c) throws SQLException {
        String sql = """
                INSERT INTO consulta (data, hora, status, observacoes, pacienteId, medicoId)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, c.getData());
        stmt.setString(2, c.getHora());
        stmt.setString(3, c.getStatus());
        stmt.setString(4, c.getObservacoes());
        stmt.setInt(5, c.getPacienteId());
        stmt.setInt(6, c.getMedicoId());

        stmt.executeUpdate();
    }

    public Consulta getById(int id) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Consulta c = new Consulta();
            c.setId(rs.getInt("id"));
            c.setData(rs.getString("data"));
            c.setHora(rs.getString("hora"));
            c.setStatus(rs.getString("status"));
            c.setObservacoes(rs.getString("observacoes"));
            c.setPacienteId(rs.getInt("pacienteId"));
            c.setMedicoId(rs.getInt("medicoId"));
            return c;
        }

        return null;
    }

    public List<Consulta> getAll() throws SQLException {
        String sql = "SELECT * FROM consulta";
        Connection conn = DatabaseConnection.getConnection();

        List<Consulta> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Consulta c = new Consulta();
            c.setId(rs.getInt("id"));
            c.setData(rs.getString("data"));
            c.setHora(rs.getString("hora"));
            c.setStatus(rs.getString("status"));
            c.setObservacoes(rs.getString("observacoes"));
            c.setPacienteId(rs.getInt("pacienteId"));
            c.setMedicoId(rs.getInt("medicoId"));

            lista.add(c);
        }

        return lista;
    }

    public void update(Consulta c) throws SQLException {
        String sql = """
                UPDATE consulta SET data=?, hora=?, status=?, observacoes=?, pacienteId=?, medicoId=?
                WHERE id=?
                """;

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, c.getData());
        stmt.setString(2, c.getHora());
        stmt.setString(3, c.getStatus());
        stmt.setString(4, c.getObservacoes());
        stmt.setInt(5, c.getPacienteId());
        stmt.setInt(6, c.getMedicoId());
        stmt.setInt(7, c.getId());

        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}