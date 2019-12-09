package br.edu.ifrs.crudfirebase;

public class Artista {
    private String id;
    private String nome;
    private String areaAtuacao;

    public Artista(){}
    public Artista( String nome, String areaAtuacao) {
        this.nome = nome;
        this.areaAtuacao = areaAtuacao;
    }
    public Artista(String id, String nome, String areaAtuacao) {
        this.id = id;
        this.nome = nome;
        this.areaAtuacao = areaAtuacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                '}';
    }
}
