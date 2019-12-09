package com.ssi.cafeteriabanco.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDUtils extends SQLiteOpenHelper {

    private static final String BASE_DE_DADOS = "CAFETERIA";
    private static final int VERSAO = 1;

    public BDUtils (Context context){
        super(context,BASE_DE_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE BEBIDA (");
        criarTabela.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" NOME  TEXT    NOT NULL,");
        criarTabela.append(" DESCRICAO   TEXT    NOT NULL");
        db.execSQL(criarTabela.toString());

    }

    /*Método abaixo é executado quando troa a versão do BD*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CAFETERIA");
        onCreate(db);
    }

    /*Método usado para obter a conexão com o BD*/
    public SQLiteDatabase getConexao(){
        return this.getWritableDatabase();
    }
}
