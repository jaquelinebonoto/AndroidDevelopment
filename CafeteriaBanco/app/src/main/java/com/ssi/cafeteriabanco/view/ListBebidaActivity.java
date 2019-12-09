package com.ssi.cafeteriabanco.view;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.ssi.cafeteriabanco.R;
import com.ssi.cafeteriabanco.adapter.LinhaConsultaAdapter;
import com.ssi.cafeteriabanco.dao.BebidaRepository;
import com.ssi.cafeteriabanco.model.Bebida;
import java.util.List;

public class ListBebidaActivity extends AppCompatActivity {
    private ListView listBebidas;
    private BebidaRepository bebidaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bebidas);
        listBebidas = this.findViewById(R.id.lista);
        this.getAll();
    }
    protected  void getAll(){
        BebidaRepository cafeRepository = new BebidaRepository(this);
        List<Bebida> bebida = bebidaRepository.getAll();
        listBebidas.setAdapter(new LinhaConsultaAdapter(this, bebida));
    }
}
