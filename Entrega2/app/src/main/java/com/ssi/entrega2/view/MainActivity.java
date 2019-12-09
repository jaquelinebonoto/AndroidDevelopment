package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ssi.entrega2.R;
import com.ssi.entrega2.model.Produto;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button botaoEscolhido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaOpcoes = this.findViewById(R.id.lista);
        String[] itens = {"Produtos Lista", "Produto Cadastro", "Pedidos Lista", "Pedido Cadastro", "Busca", "Sobre"};
        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);
        listaOpcoes.setAdapter(arrayItens);
        listaOpcoes.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(getApplicationContext(), ProdutoActivity.class);
            startActivity(intent);
        }else if (position == 1) {
            Intent intent = new Intent(getApplicationContext(), CadProdutoActivity.class);
            startActivity(intent);
        }else if (position == 2) {
            Intent intent = new Intent(getApplicationContext(), PedidoActivity.class);
            startActivity(intent);
        }else if (position == 3) {
            Intent intent = new Intent(getApplicationContext(), CadPedidoActivity.class);
            startActivity(intent);
        }else if (position == 4) {
            Intent intent = new Intent(getApplicationContext(), BuscaActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
            startActivity(intent);
        }
    }
}
