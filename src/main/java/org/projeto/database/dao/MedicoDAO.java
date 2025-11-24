package org.projeto.database.dao;

import org.projeto.database.DatabaseConnection;
import org.projeto.database.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void insert(Medico m) throws SQLException {
        String sql = "INSERT INTO medico (nome, crm, telefone, email, especialidadeId) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, m.getNome());
        stmt.setInt(2, m.getCrm());
        stmt.setString(3, m.getTelefone());
        stmt.setString(4, m.getEmail());
        stmt.setInt(5, m.getEspecialidadeId());

        stmt.executeUpdate();
    }

    public Medico getById(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getInt("crm"));
            m.setTelefone(rs.getString("telefone"));
            m.setEmail(rs.getString("email"));
            m.setEspecialidadeId(rs.getInt("especialidadeId"));
            return m;
        }
        return null;
    }

    public List<Medico> getAll() throws SQLException {
        String sql = "SELECT * FROM medico";
        Connection conn = DatabaseConnection.getConnection();

        List<Medico> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getInt("crm"));
            m.setTelefone(rs.getString("telefone"));
            m.setEmail(rs.getString("email"));
            m.setEspecialidadeId(rs.getInt("especialidadeId"));
            lista.add(m);
        }

        return lista;
    }

    public void update(Medico m) throws SQLException {
        String sql = "UPDATE medico SET nome=?, crm=?, telefone=?, email=?, especialidadeId=? WHERE id=?";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, m.getNome());
        stmt.setInt(2, m.getCrm());
        stmt.setString(3, m.getTelefone());
        stmt.setString(4, m.getEmail());
        stmt.setInt(5, m.getEspecialidadeId());
        stmt.setInt(6, m.getId());

        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}