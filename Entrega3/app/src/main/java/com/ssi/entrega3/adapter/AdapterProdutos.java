package com.ssi.entrega3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssi.entrega3.R;
import com.ssi.entrega3.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MyViewHolder> implements Filterable {
    List<Produto> listaProdutos;
    //para realizar cópia da lista inicial
    List<Produto> listaProdutos2;

        public AdapterProdutos(List<Produto> produtos) {
            this.listaProdutos = produtos;
            //criando cópia da lista inicial para realizar a busca
            listaProdutos2 = new ArrayList<>(produtos);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
            //convertendo o XML em uma visualização
            //cria um objeto do tipo view
            View itemList = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_card, viewGroup, false);
            //retorna o itemList que é passado para o construtor da MyViewHolder
            return new MyViewHolder(itemList);
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterProdutos.MyViewHolder myViewHolder, int i) {
            //exibe os itens no Recycler
            Produto p = listaProdutos.get(i);
            myViewHolder.nome.setText(p.getNome());
            myViewHolder.cor.setText(p.getCor());
            //myViewHolder.precoCusto.setText((() p.getPrecoCusto()));
            //precoVendamyViewHolder.cor.setText(p.getCor());
            //LucromyViewHolder.cor.setText(p.getCor());
        }

        @Override
        public int getItemCount() {
            //retorna a quantidade de itens que será exibida
            return listaProdutos.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            //dados da pessoa que serão exibidos no recycler
            TextView nome;
            TextView cor;
            TextView precoCusto;
            TextView precoVenda;
            TextView lucro;

            public MyViewHolder(View itemView){
                super(itemView);
                //passa uma referência para os componentes que estão na interface
                nome = itemView.findViewById(R.id.textViewNome);
                cor = itemView.findViewById(R.id.textViewCor);
                precoCusto = itemView.findViewById(R.id.textViewPrecoCusto);
                precoVenda = itemView.findViewById(R.id.textViewPrecoVenda);
                lucro = itemView.findViewById(R.id.textViewLucro);
            }
        }

        @Override
        public Filter getFilter(){
            return exampleFilter;
        }

        private Filter exampleFilter = new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Produto> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(listaProdutos2);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Produto item : listaProdutos2) {
                        if (item.getNome().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaProdutos.clear();
                listaProdutos.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
}


