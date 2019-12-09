package com.ssi.cafeteriabanco.model;

import com.ssi.cafeteriabanco.R;

public class Bebida {
    private String nome;
    private String descricao;
    private int _id;

    public Bebida(){};
    public Bebida(int _id, String nome, String descricao){
        this._id = _id;
        this.nome = nome;
        this.descricao = descricao;
    };

    public int get_id() {return _id;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public void set_id(int id) {
    }
}
