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
import com.ssi.entrega4.classe.Produto;
import com.ssi.entrega4.dao.ConfiguraFirebase;
import com.ssi.entrega4.edit.EditProdActivity;

import java.util.ArrayList;
import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MyViewHolder> {
    List<Produto> listaProdutos = new ArrayList<>();
    Context context;

    public AdapterProdutos(List<Produto> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterProdutos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_produtos, viewGroup, false);
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new AdapterProdutos.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProdutos.MyViewHolder myViewHolder, final int i) {
        //exibe os itens no Recycler
        final Produto p = listaProdutos.get(i);
        myViewHolder.nome.setText(p.getNome());
        myViewHolder.cor.setText(p.getCor());
        myViewHolder.precoVenda.setText(String.valueOf(p.getPrecoVenda()));
        myViewHolder.precoCusto.setText(String.valueOf(p.getPrecoCusto()));

        myViewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditProdActivity.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                intent.putExtra("id", p.getId());
                intent.putExtra("nome", p.getNome());
                intent.putExtra("cor", p.getCor());
                intent.putExtra("precoCusto", String.valueOf(p.getPrecoCusto()));
                intent.putExtra("precoVenda", String.valueOf(p.getPrecoVenda()));
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
                .setTitle("Deletando produto")
                .setMessage("Tem certeza que deseja deletar esse produto?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final DatabaseReference reference = ConfiguraFirebase.getNo("produtos");
                        //reference.child(listaPedidos.get(i)).removeValue();
                        reference.child(listaProdutos.get(position).getId()).removeValue();
                        listaProdutos.remove(position);
                        notifyItemRemoved(position);

                    }
                }).setNegativeButton("Não", null).show();
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaProdutos != null ? listaProdutos.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //dados da pessoa que serão exibidos no recycler
        //TextView codigo;
        TextView nome;
        TextView cor;
        TextView precoVenda;
        TextView precoCusto;

        Button editar;
        Button excluir;

        public MyViewHolder(View itemView) {
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            //codigo = itemView.findViewById(R.id.textViewCodigo);
            nome = itemView.findViewById(R.id.textViewNome);
            cor = itemView.findViewById(R.id.textViewCor);
            precoCusto = itemView.findViewById(R.id.textViewPrecoCusto);
            precoVenda = itemView.findViewById(R.id.textViewPrecoVenda);
            editar = itemView.findViewById(R.id.btnEditProd);
            excluir = itemView.findViewById(R.id.btnDelProd);
        }
    }
}
