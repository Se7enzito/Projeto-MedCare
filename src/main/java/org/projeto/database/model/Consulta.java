package org.projeto.database.model;

import org.projeto.database.dao.MedicoDAO;
import org.projeto.database.dao.PacienteDAO;

import java.sql.SQLException;

public class Consulta {
    private int id;
    private String data;
    private String hora;
    private String status;
    private String observacoes;
    private int pacienteId;
    private int medicoId;

    public Consulta() {}

    public Consulta(String data, String hora, String status, String observacoes, int pacienteId, int medicoId) {
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.observacoes = observacoes;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    private String getPacienteNome() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        
        return pacienteDAO.getById(pacienteId).getNome();
    }

    private String getMedicoNome() throws SQLException {
        MedicoDAO medicoDAO = new MedicoDAO();

        return medicoDAO.getById(medicoId).getNome();
    }

    @Override
    public String toString() {
        try {
            return getPacienteNome() + ": " + getMedicoNome() + " - " + data + " | " + status;
        } catch (SQLException e) {
            return "Inválido";
        }
    }

}