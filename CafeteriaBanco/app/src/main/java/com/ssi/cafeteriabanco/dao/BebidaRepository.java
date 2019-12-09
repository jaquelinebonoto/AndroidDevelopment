package com.ssi.cafeteriabanco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ssi.cafeteriabanco.model.Bebida;
import com.ssi.cafeteriabanco.util.BDUtils;

import java.util.ArrayList;
import java.util.List;

public class BebidaRepository{

        private BDUtils bdUtil;

        public BebidaRepository(Context context){
            bdUtil =  new BDUtils(context);
        }

        public String insert(String nome, String descricao){
            ContentValues valores = new ContentValues();
            valores.put("NOME", nome);
            valores.put("DESCRICAO", descricao);
            long resultado = bdUtil.getConexao().insert("BEBIDA", null, valores);
            if (resultado ==-1) {
                bdUtil.close();
                return "Erro ao inserir registro";
            }
            return "Registro Inserido com sucesso";
        }
        public Integer delete(int codigo){
            Integer linhasAfetadas = bdUtil.getConexao().delete("BEBIDA","_id = ?", new String[]{Integer.toString(codigo)});
            bdUtil.close();
            return linhasAfetadas;
        }

        public List<Bebida> getAll(){
            List<Bebida> bebidas = new ArrayList<>();
            // monta a consulta
            StringBuilder stringBuilderQuery = new StringBuilder();
            stringBuilderQuery.append("SELECT _ID, NOME, DESCRICAO, DATA ");
            stringBuilderQuery.append("FROM  BEBIDA ");
            stringBuilderQuery.append("ORDER BY NOME");
            //consulta os registros que estão no BD
            Cursor cursor = bdUtil.getConexao().rawQuery(
                    stringBuilderQuery.toString(),
                    null);
            //aponta cursos para o primeiro registro
            cursor.moveToFirst();
            Bebida bebida = null;
            //Percorre os registros até atingir o fim da lista de registros
            while (!cursor.isAfterLast()){
                // Cria objetos do tipo tarefa
                bebida =  new Bebida();
                //adiciona os dados no objeto
                bebida.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
                bebida.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                bebida.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
                //adiciona o objeto na lista
                bebidas.add(bebida);
                //aponta para o próximo registro
                cursor.moveToNext();
            }
            bdUtil.close();
            //retorna a lista de objetos
            return bebidas;
        }
        public Bebida getTarefa(int codigo){
            Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM BEBIDA WHERE _ID = "+ codigo,null);
            cursor.moveToFirst();
            Bebida b = new Bebida();
            b.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
            b.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            b.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
            bdUtil.close();
            return b;
        }

        public int update(Bebida bebida){
            ContentValues contentValues =  new ContentValues();
            contentValues.put("NOME",       bebida.getNome());
            contentValues.put("DESCRICAO",   bebida.getDescricao());
            //atualiza o objeto usando a chave
            int retorno = bdUtil.getConexao().update("BEBIDA", contentValues, "_id = ?", new String[]{Integer.toString(bebida.get_id())});
            bdUtil.close();
            return retorno;
        }
}

