package com.ssi.applistas;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListaUFsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ListView lista = getListView();
        //busca a lista que está no string.xml
        String ufs[] = getResources().getStringArray(R.array.UFs);
        ArrayAdapter<Uf> listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Uf.ufs);
        lista.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView lista, View view, int position, long id) {
        String itemLista = lista.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Você selecionou o ítem: " + itemLista, Toast.LENGTH_LONG).show();
        //FAZER ABRIR UMA INTEÇÃO PASSANDO COMO PARÂMETRO O ESTADO QUE FOI SELECIONADO E MOSTRAR NO MAPA A SUA LOCALIZAÇÃO
    }

}