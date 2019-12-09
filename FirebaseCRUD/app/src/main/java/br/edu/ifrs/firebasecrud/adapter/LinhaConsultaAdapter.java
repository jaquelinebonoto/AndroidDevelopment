package br.edu.ifrs.firebasecrud.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.edu.ifrs.firebasecrud.R;
import br.edu.ifrs.firebasecrud.activity.ListProdutoActivity;
import br.edu.ifrs.firebasecrud.classe.Produto;
import br.edu.ifrs.firebasecrud.dao.ConfiguraFirebase;

public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    ArrayList<Produto> produtos =  new ArrayList<>();


    //Cria objeto do tipo que lista as tarefas
    private ListProdutoActivity listarProdutos;

    //Construtor que recebe a ativida como parametro e a lista de tarefas que vai retornar do BD
    public LinhaConsultaAdapter(ListProdutoActivity listarProdutos, ArrayList<Produto> produtos ) {
        this.produtos       =  produtos;
        this.listarProdutos  =  listarProdutos;
        this.layoutInflater     = (LayoutInflater) this.listarProdutos.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Retorna a quantidade de objetos que sta na lista
    @Override
    public int getCount(){
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método converte os valoes de um item  da lista de Tarefas para uma linha do ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha,null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        TextView textViewNome  = viewLinhaLista.findViewById(R.id.textViewNome);
        TextView textViewDescricao =  viewLinhaLista.findViewById(R.id.textViewDescricao);
        TextView textViewValor = viewLinhaLista.findViewById(R.id.textViewValor);
        Button buttonExcluir =  viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar =  viewLinhaLista.findViewById(R.id.buttonEditar);


        textViewNome.setText(produtos.get(position).getNome());
        textViewDescricao.setText(produtos.get(position).getDescricao());
        textViewValor.setText(String.valueOf(produtos.get(position).getValorUnitario()));

        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                //usa o objeto produto para fazer a exclusão
                final Produto produto = produtos.get(position);
                final DatabaseReference reference = ConfiguraFirebase.getNo("produtos");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        produtos.remove(position);
                        reference.child(produto.getId()).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
       });
       this.notifyDataSetChanged();

        //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EXERCÍCIO FAZER A TAREFA DE EDIÇÃO USANDO A LÓGICA DA EXCLUSÃO
            }
        });
        return viewLinhaLista;
    }
}