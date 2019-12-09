package com.ssi.entrega3.ui.produtos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ssi.entrega3.R;
import com.ssi.entrega3.adapter.AdapterProdutos;
import com.ssi.entrega3.listener.RecyclerItemClickListener;
import com.ssi.entrega3.model.Produto;

public class ProdutosFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;

    //para tratar os eventos da barra de baixo
    BottomNavigationView menu;

    public static ProdutosFragment newInstance() {
        return new ProdutosFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        //configurar o adapter - que formata que o layout de cada item do recycler
        AdapterProdutos myAdapter = new AdapterProdutos(Produto.inicializaLista());
        recyclerView.setAdapter(myAdapter);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        //menu
        menu = root.findViewById(R.id.bottomNav);
        menu.setOnNavigationItemSelectedListener(this);

        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //método para quando sofre um click rápido
                //método que recebe a linha do Recycler que sofreu o click
                Toast.makeText(getContext(), "Item pressionado com click: "+ Produto.inicializaLista().get(position).getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //método para quando sofre um click longo
                Toast.makeText(getContext(), "Item pressionado com click longo: "+ Produto.inicializaLista().get(position).getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
        return root;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bottomnavcad : {
                Toast.makeText(getContext(), "bottom na cad funcionou! ", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.bottomnavdel: {
                break;
            }
            case R.id.bottomnavedit: {
                break;
            }
            case R.id.bottomnavlist: {
                break;
            }
            case R.id.bottomnavsearch: {
                break;
            }
        }
    return true;
    }
}
