package br.edu.ifrs.firebasecrud.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.edu.ifrs.firebasecrud.adapter.LinhaConsultaAdapter;
import br.edu.ifrs.firebasecrud.classe.Produto;
import br.edu.ifrs.firebasecrud.dao.ConfiguraFirebase;


public class ListProdutoActivity extends ListActivity {
    private ArrayList<Produto> listProdutos = new ArrayList<>();
    ListView lista = null;
    ArrayAdapter<Produto> listAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listProdutos = new ArrayList<>();
        lista = getListView();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listProdutos);

        DatabaseReference reference = ConfiguraFirebase.getNo("produtos");
        listProdutos = new ArrayList<>();
        //associar os eventos ao nó produtos para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Produto produto = ds.getValue(Produto.class);
                    produto.setId(ds.getKey());
                    listProdutos.add(produto);
                  }
                lista.setAdapter(new LinhaConsultaAdapter(ListProdutoActivity.this, listProdutos));
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
