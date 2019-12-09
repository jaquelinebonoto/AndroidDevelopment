package com.ssi.entrega2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ssi.entrega2.model.Pedido;
import com.ssi.entrega2.model.Produto;
import com.ssi.entrega2.util.BdUtil;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private BdUtil bdutil;

    public PedidoRepository(Context context){
        bdutil = new BdUtil(context);
    }

    public String insert (String nomeCliente, String telefone, String dataPedido, double valorPedido){
        ContentValues valores = new ContentValues();
        valores.put("NOMECLIENTE", nomeCliente);
        valores.put("TELEFONE", telefone);
        valores.put("DATAPEDIDO", dataPedido);
        valores.put("VALORPEDIDO", valorPedido);
        long resultado = bdutil.getConexao().insert("PEDIDO", null, valores);

        if(resultado == -1){
            bdutil.close();
            return "Erro ao inserir o registro do produto";
        }
        return "Produto inserido com sucesso!";
    }

    //não entendi muito bem este código
    public Integer delete(int codigo){
        Integer linhasAfetadas = bdutil.getConexao().delete(
                "PEDIDO",
                "_id = ?",
                new String[]{Integer.toString(codigo)});
        bdutil.close();
        return linhasAfetadas;
    }

    public List<Pedido> getAll(){
        List<Pedido> pedidos = new ArrayList<>();

        //montando a QUERY
        StringBuilder stringBuilderConsulta = new StringBuilder();
        stringBuilderConsulta.append("SELECT _ID, NOMECLIENTE, TELEFONE, DATAPEDIDO, VALORPEDIDO ");
        stringBuilderConsulta.append("FROM  PEDIDO ");
        stringBuilderConsulta.append("ORDER BY NOMECLIENTE");


        //ACESSANDO OS REGISTROS DO BANCO com a query que foi montada acima
        Cursor cursor = bdutil.getConexao().rawQuery(
                stringBuilderConsulta.toString(),
                null);

        //inicializando o cursor
        cursor.moveToFirst();

        //instancia um produto vazio
        Pedido pedido = null;

        //Percorre os registros e coloca na lista criada lá em cima
        while (!cursor.isAfterLast()){
            pedido = new Pedido();

            pedido.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("NOMECLIENTE")));
            pedido.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
            pedido.setDataPedido(cursor.getString(cursor.getColumnIndex("DATAPEDIDO")));
            pedido.setValorPedido(cursor.getDouble(cursor.getColumnIndex("VALORPEDIDO")));

            pedidos.add(pedido);
            cursor.moveToNext();
        }
        bdutil.close();
        return pedidos;
    }

    public Pedido getPedido(int codigo){
        Cursor cursor = bdutil.getConexao().rawQuery(
                "SELECT * FROM PEDIDO WHERE _ID = " + codigo,
                null);
        cursor.moveToFirst();
        Pedido p = new Pedido();
        p.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
        p.setNomeCliente(cursor.getString(cursor.getColumnIndex("NOMECLIENTE")));
        p.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
        p.setDataPedido(cursor.getString(cursor.getColumnIndex("DATAPEDIDO")));
        p.setValorPedido(cursor.getDouble(cursor.getColumnIndex("VALORPEDIDO")));
        bdutil.close();
        return p;
    }

    public int update(Pedido pedido){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("NOME",       pedido.getNomeCliente());
        contentValues.put("TELEFONE", pedido.getTelefone());
        contentValues.put("DATAPEDIDO",   pedido.getDataPedido());
        contentValues.put("VALORPEDIDO",   pedido.getValorPedido());
        //atualiza o objeto usando a chave
        int retorno = bdutil.getConexao().update(
                "PEDIDO",
                contentValues,
                "_id = ?",
                new String[]{Integer.toString(pedido.get_id())});
        bdutil.close();
        return retorno;
    }
}
