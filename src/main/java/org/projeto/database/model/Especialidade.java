package org.projeto.database.model;

public class Especialidade {
    private int id;
    private int idClinica;
    private String nome;
    private String descricao;

    public Especialidade() {}

    public Especialidade(int idClinica, String nome, String descricao) {
        this.idClinica = idClinica;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public int getIdClinica() {
        return idClinica;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}