package br.edu.ifrs.progweb3.model;

/*
* Annotations usadas no Realm
@Required - diz ao Realm que o campo é necessário.
@Ignore  - informa que o campo não será persistido no arquivo/disco
@Index - adiciona um índice ao campo. Isso faz com que as consultas fiquem mais rápidas
@PrimaryKey - define que o campo é indexado
Para mais detalhes sobre as anotações e mapeamentos veja o exemplo:
https://github.com/realm/realm-java/blob/master/examples/introExample/src/main/java/io/realm/examples/intro/model/Person.java
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//Toda model deve ser definida como subclasse de RealmObject para indicar que essa classe terá uma
//tabela correspondente no banco de dados
//para passar objetos entre atividades é necessário implementar a interface Parcelable
public class Tarefa extends RealmObject implements Serializable, Parcelable {
    @PrimaryKey
    //a anotação PrimaryKey define que id será chave primária
    private int id;
    private String nome;
    private String descricao;
    private String data;

    public Tarefa(){}
    public Tarefa(String nome, String descricao, String data) {
        this(-1, nome, descricao, data);
    }
    public Tarefa(int id, String nome, String descricao, String data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }
    private Tarefa(Parcel from){
        id = from.readInt();
        nome = from.readString();
        descricao = from.readString();
        data = from.readString();
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    /*método que retorna um inteiro que será usado como identificador único dessa classe no projeto
    * Observe que: CADA classe do seu projeto (que será incluída como parâmetro de uma intenção) deve ter um
    * valor diferente*/
    public int describeContents() {
        return 0;
    }

    @Override
    /*método usado para serializar - transforma em bytes - os atributos da classe*/
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(data);
    }

    /*o atributo estático CREATOR da classe Parcelable, deve ser definido em todas as classes que implementam Parcelable.
    * Esse atributo é responsável por criar o objeto a partir de um Parcel para desserializar este
    * O método createFromParcel() chama o construtor privado da classe Tarefa, que recebe um Parcel, que permite ler os bytes
    * e passá-los para os atributos */
    public static final Creator<Tarefa>
        CREATOR = new Creator<Tarefa>(){
           public Tarefa createFromParcel(Parcel in){
               return new Tarefa(in);
           }
           public Tarefa[] newArray(int size){
               return new Tarefa[size];
        }
    };
}
