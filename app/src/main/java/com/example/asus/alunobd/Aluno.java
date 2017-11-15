package com.example.asus.alunobd;

/**
 * Created by Asus on 01/11/2017.
 */

public class Aluno {

    String id;
    String nome;
    String ra;

    public Aluno(String id, String nome, String ra){

        this.id = id;
        this.nome = nome;
        this.ra = ra;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
