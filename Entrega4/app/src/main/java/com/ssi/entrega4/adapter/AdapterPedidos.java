package com.ssi.entrega4.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.ssi.entrega4.R;
import com.ssi.entrega4.classe.Pedido;
import com.ssi.entrega4.dao.ConfiguraFirebase;
import com.ssi.entrega4.edit.EditActivity;

import java.util.ArrayList;
import java.util.List;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.MyViewHolder>{
    List<Pedido> listaPedidos = new ArrayList<>();
    Context context;

    public AdapterPedidos(List<Pedido> listaPedidos, Context context) {
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPedidos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_pedidos, viewGroup, false);
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPedidos.MyViewHolder myViewHolder, final int i) {
        //exibe os itens no Recycler
        final Pedido p = listaPedidos.get(i);
        myViewHolder.nomeCliente.setText(p.getNomeCliente());
        myViewHolder.telefone.setText(p.getTelefone());
        myViewHolder.dataPedido.setText(p.getDataPedido());
        myViewHolder.valorPedido.setText(String.valueOf(p.getValorPedido()));
        //myViewHolder.precoCusto.setText((() p.getPrecoCusto()));
        //precoVendamyViewHolder.cor.setText(p.getCor());
        //LucromyViewHolder.cor.setText(p.getCor());

        myViewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                intent.putExtra("id", p.getId());
                intent.putExtra("nomeCliente", p.getNomeCliente());
                intent.putExtra("telefone", p.getTelefone());
                intent.putExtra("dataPedido", p.getDataPedido());
                intent.putExtra("valorPedido", String.valueOf(p.getDataPedido()));
                context.getApplicationContext().startActivity(intent);
            }
        });

        myViewHolder.excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(i);
            }
        });
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando pedido")
                .setMessage("Tem certeza que deseja deletar esse pedido?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final DatabaseReference reference = ConfiguraFirebase.getNo("pedidos");
                        //reference.child(listaPedidos.get(i)).removeValue();
                        reference.child(listaPedidos.get(position).getId()).removeValue();
                        listaPedidos.remove(position);
                        notifyItemRemoved(position);

                    }}).setNegativeButton("Não", null).show();
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaPedidos != null ? listaPedidos.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        //TextView codigo;
        TextView nomeCliente;
        TextView telefone;
        TextView dataPedido;
        TextView valorPedido;

        Button editar;
        Button excluir;

        public MyViewHolder(View itemView) {
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            //codigo = itemView.findViewById(R.id.textViewCodigo);
            nomeCliente = itemView.findViewById(R.id.textViewNomeCliente);
            telefone = itemView.findViewById(R.id.textViewTelefone);
            dataPedido = itemView.findViewById(R.id.textViewDataPedido);
            valorPedido = itemView.findViewById(R.id.textViewValorPedido);
            editar = itemView.findViewById(R.id.btnEditPed);
            excluir = itemView.findViewById(R.id.btnDelPed);
        }
    }
}
