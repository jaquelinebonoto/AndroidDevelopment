package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.ssi.entrega2.R;
import com.ssi.entrega2.adapter.LinhaConsultaAdapter;
import com.ssi.entrega2.adapter.LinhaConsultaAdapterPedido;
import com.ssi.entrega2.dao.PedidoRepository;
import com.ssi.entrega2.model.Pedido;

import java.util.List;

public class PedidoActivity extends AppCompatActivity {

    private ListView listPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        listPedidos = (ListView)this.findViewById(R.id.listViewPedidos);
        this.getAll();
    }

    protected void getAll(){
        PedidoRepository pedidoRepository = new PedidoRepository(this);
        List<Pedido> pedidos = pedidoRepository.getAll();
        listPedidos.setAdapter(new LinhaConsultaAdapterPedido(this, pedidos));
    }
}
