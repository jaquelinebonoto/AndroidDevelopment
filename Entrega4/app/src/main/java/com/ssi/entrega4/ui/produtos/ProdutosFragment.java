package com.ssi.entrega4.ui.produtos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ssi.entrega4.R;
import com.ssi.entrega4.adapter.AdapterProdutos;
import com.ssi.entrega4.classe.Produto;
import com.ssi.entrega4.dao.ConfiguraFirebase;
import com.ssi.entrega4.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ProdutosFragment extends Fragment {
    //definição do objeto que vai acessar o recycler que está visível
    //no layout do fragmento
    RecyclerView recyclerView;
    List<Produto> listaProdutos;
    AdapterProdutos myAdapter;
    Context context;


    public static ProdutosFragment newInstance() {
        return new ProdutosFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragment_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_produtos, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        //configurar o adapter - que formata que o layout de cada item do recycler
        context = root.getContext();
        carregaProdutos();




        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 2);
        StaggeredGridLayoutManager layoutManager2 =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        //adiciona um separador entre os elementos da lista
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayout.VERTICAL));


        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager2);

      /*  recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //método para quando sofre um click rápido
                //método que recebe a linha do Recycler que sofreu o click
                Toast.makeText(getContext(), "Item pressionado com click: "+ Pessoa.inicializaLista().get(position).getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //método para quando sofre um click longo
                Toast.makeText(getContext(), "Item pressionado com click longo: "+ Pessoa.inicializaLista().get(position).getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));*/
        return root;
    }

    private void carregaProdutos(){
        final DatabaseReference reference = ConfiguraFirebase.getNo("produtos");

        listaProdutos = new ArrayList<>();
        Log.d("produto", listaProdutos.toString());
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
                    listaProdutos.add(produto);
                    Log.d("produto", produto.toString());
                }
                myAdapter = new AdapterProdutos(listaProdutos, context);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
