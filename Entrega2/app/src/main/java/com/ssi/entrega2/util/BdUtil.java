package com.ssi.entrega2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdUtil extends SQLiteOpenHelper {

    private static final String BASE_DE_DADOS = "BANCODEDADOS";
    private static final int VERSAO = 3;

    public BdUtil(Context context){
        super(context,BASE_DE_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder criarTabela = new StringBuilder();
        StringBuilder criarTabela2 = new StringBuilder();

        criarTabela.append(" CREATE TABLE PRODUTO ( ");
        criarTabela.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" NOME  TEXT    NOT NULL,");
        criarTabela.append(" COR  TEXT,");
        criarTabela.append(" PRECOCUSTO   REAL    NOT NULL,");
        criarTabela.append(" PRECOVENDA REAL, ");
        criarTabela.append(" LUCRO REAL) ");

        criarTabela2.append(" CREATE TABLE PEDIDO ( ");
        criarTabela2.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela2.append(" NOMECLIENTE  TEXT    NOT NULL,");
        criarTabela2.append(" TELEFONE   TEXT    NOT NULL,");
        criarTabela2.append(" DATAPEDIDO TEXT, ");
        criarTabela2.append(" VALORPEDIDO REAL)");



        db.execSQL(criarTabela.toString());
        db.execSQL(criarTabela2.toString());
    }

    /*Método abaixo é executado quando troa a versão do BD*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PRODUTO");
        db.execSQL("DROP TABLE IF EXISTS PEDIDO");
        onCreate(db);

    }

    /*Método usado para obter a conexão com o BD*/
    public SQLiteDatabase getConexao(){
        return this.getWritableDatabase();
    }

}
