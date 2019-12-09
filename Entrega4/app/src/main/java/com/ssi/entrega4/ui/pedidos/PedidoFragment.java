package com.ssi.entrega4.ui.pedidos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ssi.entrega4.R;
import com.ssi.entrega4.adapter.AdapterPedidos;
import com.ssi.entrega4.classe.Pedido;
import com.ssi.entrega4.dao.ConfiguraFirebase;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class PedidoFragment extends Fragment {
    //definição do objeto que vai acessar o recycler que está visível
    //no layout do fragmento
    RecyclerView recyclerView;
    List<Pedido> listaPedidos;
    AdapterPedidos myAdapter;
    Context context;


    public static PedidoFragment newInstance() {
        return new PedidoFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragment_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_pedido, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        //configurar o adapter - que formata que o layout de cada item do recycler
        context = root.getContext();
        carregaPedidos();




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

    private void carregaPedidos(){
        final DatabaseReference reference = ConfiguraFirebase.getNo("pedidos");

        listaPedidos = new ArrayList<>();
        Log.d("pedido", listaPedidos.toString());
        //associar os eventos ao nó produtos para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Pedido pedido = ds.getValue(Pedido.class);
                    pedido.setId(ds.getKey());
                    listaPedidos.add(pedido);
                    Log.d("pedido", pedido.toString());
                }
                myAdapter = new AdapterPedidos(listaPedidos, context);
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
