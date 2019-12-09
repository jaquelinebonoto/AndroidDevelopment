package com.ssi.entrega3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssi.entrega3.R;
import com.ssi.entrega3.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.MyViewHolder>{
        List<Pedido> listaPedidos = new ArrayList<>();

        public AdapterPedidos(List<Pedido> pedidos) {
            this.listaPedidos = pedidos;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
            //convertendo o XML em uma visualização
            //cria um objeto do tipo view
            View itemList = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_card_pedidos, viewGroup, false);
            //retorna o itemList que é passado para o construtor da MyViewHolder
            return new MyViewHolder(itemList);
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterPedidos.MyViewHolder myViewHolder, int i) {
            //exibe os itens no Recycler
            Pedido p = listaPedidos.get(i);
            myViewHolder.nomeCliente.setText(p.getNomeCliente());
            myViewHolder.telefone.setText(p.getTelefone());
            myViewHolder.dataPedido.setText(p.getDataPedido());
            //myViewHolder.precoCusto.setText((() p.getPrecoCusto()));
            //precoVendamyViewHolder.cor.setText(p.getCor());
            //LucromyViewHolder.cor.setText(p.getCor());
        }

        @Override
        public int getItemCount() {
            //retorna a quantidade de itens que será exibida
            return listaPedidos.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            //dados da pessoa que serão exibidos no recycler
            //TextView codigo;
            TextView nomeCliente;
            TextView telefone;
            TextView dataPedido;
            TextView valorPedido;

            public MyViewHolder(View itemView){
                super(itemView);
                //passa uma referência para os componentes que estão na interface
                //codigo = itemView.findViewById(R.id.textViewCodigo);
                nomeCliente = itemView.findViewById(R.id.textViewNomeCliente);
                telefone = itemView.findViewById(R.id.textViewTelefone);
                dataPedido = itemView.findViewById(R.id.textViewDataPedido);
                valorPedido = itemView.findViewById(R.id.textViewValorPedido);
            }
        }
}