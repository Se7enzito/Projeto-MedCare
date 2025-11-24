package org.projeto.database.model;

public class Medico {
    private int id;
    private String nome;
    private int crm;
    private String telefone;
    private String email;
    private int especialidadeId;

    public Medico() {}

    public Medico(String nome, int crm, String telefone, String email, int especialidadeId) {
        this.nome = nome;
        this.crm = crm;
        this.telefone = telefone;
        this.email = email;
        this.especialidadeId = especialidadeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEspecialidadeId(int especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public int getId() {
        return id;
    }

    public int getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public int getEspecialidadeId() {
        return especialidadeId;
    }

    @Override
    public String toString() {
        return nome;
    }

}