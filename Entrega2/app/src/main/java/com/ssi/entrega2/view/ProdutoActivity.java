package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ssi.entrega2.R;
import com.ssi.entrega2.adapter.LinhaConsultaAdapter;
import com.ssi.entrega2.dao.ProdutoRepository;
import com.ssi.entrega2.model.Produto;

import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    private ListView listProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        listProdutos = (ListView)this.findViewById(R.id.listViewProdutos);
        this.getAll();
    }

    protected void getAll(){
        ProdutoRepository produtoRepository = new ProdutoRepository(this);
        List<Produto> produtos = produtoRepository.getAll();
        listProdutos.setAdapter(new LinhaConsultaAdapter(this, produtos));
    }
}
