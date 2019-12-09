package br.edu.ifrs.progweb3.dao;



import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.progweb3.model.Tarefa;
import io.realm.Realm;


public class TarefaDAO {
    public String insert(Tarefa tarefa) {
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            try {
                // Realm não tem um auto incremento, logo é necessário fazer o código abaixo
                tarefa.setId(realm.where(Tarefa.class).max("id").intValue() + 1);
            } catch (Exception ex) {
                tarefa.setId(1);
            }

            //para inserir dados é necessário abrir uma transaction e no final do bloco chamar o método commitTransaction()
            realm.insert(tarefa);
            realm.commitTransaction();
            return "Registro Inserido com sucesso";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inserir registro";
        }
    }



    public Integer delete(final Tarefa tarefa){
        try{
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    tarefa.deleteFromRealm();
                }
            });
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Tarefa> getAll(){
        Realm realm = Realm.getDefaultInstance();
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.addAll(realm.where(Tarefa.class).findAll());
        return tarefas;
    }
    public int update(Tarefa tarefa ){
        try{
            Realm realm = Realm.getDefaultInstance();
            Tarefa t = realm.where(Tarefa.class).equalTo("id",tarefa.getId()).findFirst();
            realm.beginTransaction();
            t.setNome(tarefa.getNome());
            t.setDescricao(tarefa.getDescricao());
            t.setData(tarefa.getData());
            realm.commitTransaction();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
