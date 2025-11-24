package org.projeto.database.dao;

import org.projeto.database.DatabaseConnection;
import org.projeto.database.model.Especialidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    public void insert(Especialidade e) throws SQLException {
        String sql = "INSERT INTO especialidade (nome, descricao) VALUES (?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, e.getNome());
        stmt.setString(2, e.getDescricao());
        stmt.executeUpdate();
    }

    public Especialidade getById(int id) throws SQLException {
        String sql = "SELECT * FROM especialidade WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Especialidade e = new Especialidade();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setDescricao(rs.getString("descricao"));
            return e;
        }

        return null;
    }

    public List<Especialidade> getAll() throws SQLException {
        String sql = "SELECT * FROM especialidade";
        Connection conn = DatabaseConnection.getConnection();

        List<Especialidade> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Especialidade e = new Especialidade();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setDescricao(rs.getString("descricao"));
            lista.add(e);
        }

        return lista;
    }

    public void update(Especialidade e) throws SQLException {
        String sql = "UPDATE especialidade SET nome=?, descricao=? WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, e.getNome());
        stmt.setString(2, e.getDescricao());
        stmt.setInt(3, e.getId());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM especialidade WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}