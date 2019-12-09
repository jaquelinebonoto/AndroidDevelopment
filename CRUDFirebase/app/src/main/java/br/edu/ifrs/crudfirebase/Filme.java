package br.edu.ifrs.crudfirebase;

public class Filme {
    private String id;
    private String nome;
    private String genero;
    private String idArtista;

    public Filme(){}
    public Filme(String id, String nome, String genero, String idArtista) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idArtista = idArtista;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", idArtista='" + idArtista + '\'' +
                '}';
    }
}
