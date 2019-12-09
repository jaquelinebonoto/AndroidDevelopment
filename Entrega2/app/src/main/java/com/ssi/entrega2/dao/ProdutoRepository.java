package com.ssi.entrega2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.ssi.entrega2.model.CorStatic;
import com.ssi.entrega2.model.Produto;
import com.ssi.entrega2.util.BdUtil;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private BdUtil bdutil;

    public ProdutoRepository(Context context){
        bdutil = new BdUtil(context);
    }

    public String insert (String nome, String cor, double precoCusto, double precoVenda, double lucro){
        ContentValues valores = new ContentValues();
        valores.put("NOME", nome);
        valores.put("COR", cor);
        valores.put("PRECOCUSTO", precoCusto);
        valores.put("PRECOVENDA", precoVenda);
        valores.put("LUCRO", lucro);
        long resultado = bdutil.getConexao().insert("PRODUTO", null, valores);

        if(resultado == -1){
            bdutil.close();
            return "Erro ao inserir o registro do produto";
        }
        return "Produto inserido com sucesso!";
    }

    //não entendi muito bem este código
    public Integer delete(int codigo){
        Integer linhasAfetadas = bdutil.getConexao().delete(
                "PRODUTO",
                "_id = ?",
                new String[]{Integer.toString(codigo)});
        bdutil.close();
        return linhasAfetadas;
    }

    public List<Produto> getAll(){
        List<Produto> produtos = new ArrayList<>();

        //montando a QUERY
        StringBuilder stringBuilderConsulta = new StringBuilder();
        stringBuilderConsulta.append("SELECT _ID, NOME, COR, PRECOCUSTO, PRECOVENDA, LUCRO ");
        stringBuilderConsulta.append("FROM  PRODUTO ");
        stringBuilderConsulta.append("ORDER BY NOME");


        //ACESSANDO OS REGISTROS DO BANCO com a query que foi montada acima
        Cursor cursor = bdutil.getConexao().rawQuery(
                stringBuilderConsulta.toString(),
                null);

        //inicializando o cursor
        cursor.moveToFirst();

        //instancia um produto vazio
        Produto produto = null;

        //Percorre os registros e coloca na lista criada lá em cima
        while (!cursor.isAfterLast()){
            produto = new Produto();
            CorStatic cor = new CorStatic();

            produto.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            produto.setCor(cursor.getString(cursor.getColumnIndex("COR")));
            produto.setPrecoCusto(cursor.getDouble(cursor.getColumnIndex("PRECOCUSTO")));
            produto.setPrecoVenda(cursor.getDouble(cursor.getColumnIndex("PRECOVENDA")));
            produto.setLucro(cursor.getDouble(cursor.getColumnIndex("LUCRO")));

            produtos.add(produto);
            cursor.moveToNext();
        }
        bdutil.close();
        return produtos;
    }

    public Produto getProduto(int codigo){
        Cursor cursor = bdutil.getConexao().rawQuery(
                "SELECT * FROM PRODUTO WHERE _ID = " + codigo,
                null);
        cursor.moveToFirst();
        Produto p = new Produto();
        p.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
        p.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
        p.setCor(cursor.getString(cursor.getColumnIndex("COR")));
        p.setPrecoCusto(cursor.getDouble(cursor.getColumnIndex("PRECOCUSTO")));
        p.setPrecoVenda(cursor.getDouble(cursor.getColumnIndex("PRECOVENDA")));
        p.setLucro(cursor.getDouble(cursor.getColumnIndex("LUCRO")));
        bdutil.close();
        return p;
    }

    public int update(Produto produto){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("NOME",       produto.getNome());
        contentValues.put("COR", produto.getCor());
        contentValues.put("PRECOCUSTO",   produto.getPrecoCusto());
        contentValues.put("PRECOVENDA",   produto.getPrecoVenda());
        contentValues.put("LUCRO",   produto.getLucro());
        //atualiza o objeto usando a chave
        int retorno = bdutil.getConexao().update(
                "PRODUTO",
                contentValues,
                "_id = ?",
                new String[]{Integer.toString(produto.get_id())});
        bdutil.close();
        return retorno;
    }
}
