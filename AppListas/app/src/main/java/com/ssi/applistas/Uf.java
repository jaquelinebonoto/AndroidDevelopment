package com.ssi.applistas;

public class Uf {
    private String nome;
    private String sigla;

    public static final Uf[] ufs = {
            new Uf("Rio Grande do Sul", "RS"),
            new Uf("Santa Catarina", "SC"),
            new Uf("Paraná", "PR")
    };
    public Uf(){};

    public Uf(String nome, String sigla){
            this.nome = nome;
            this.sigla = sigla;
        }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public static Uf[] getUfs() {
        return ufs;
    }

    @Override
    public String toString() {
        return sigla + " - " + nome;
    }
}
