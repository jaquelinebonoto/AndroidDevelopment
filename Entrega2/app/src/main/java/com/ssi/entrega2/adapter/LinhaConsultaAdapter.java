package com.ssi.entrega2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ssi.entrega2.R;
import com.ssi.entrega2.dao.ProdutoRepository;
import com.ssi.entrega2.model.Produto;
import com.ssi.entrega2.view.EditProdutoActivity;
import com.ssi.entrega2.view.ProdutoActivity;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    List<Produto> produtos = new ArrayList<>();
    ProdutoRepository produtoRepository;

    //Cria objeto do tipo que lista os produtos
    private ProdutoActivity listarProdutos;

    //Construtor que recebe a ativdade como parametro e a lista de produtos que vai retornar do BD
    public LinhaConsultaAdapter(ProdutoActivity listarProdutos, List<Produto> produtos ) {
        this.produtos       =  produtos;
        this.listarProdutos  =  listarProdutos;
        this.layoutInflater     = (LayoutInflater) this.listarProdutos.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        produtoRepository   = new ProdutoRepository(listarProdutos);
    }

    //Retorna a quantidade de objetos que esta na lista
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

    //Método converte os valores de um item  da lista de Produtos para uma linha do ListView

    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha, null);


        //vincula os campos do arquivo de layout aos objetos cadastrados
        TextView textViewCodigo = viewLinhaLista.findViewById(R.id.textViewCodigo);
        TextView textViewNome = viewLinhaLista.findViewById(R.id.textViewNome);
        TextView textViewCor = viewLinhaLista.findViewById(R.id.textViewCor);
        TextView textViewPrecoCusto = viewLinhaLista.findViewById(R.id.textViewPrecoCusto);
        TextView textViewPrecoVenda = viewLinhaLista.findViewById(R.id.textViewPrecoVenda);
        TextView textViewLucro = viewLinhaLista.findViewById(R.id.textViewLucro);

        Button buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(produtos.get(position).get_id()));
        textViewNome.setText(produtos.get(position).getNome());
        textViewCor.setText(produtos.get(position).getCor());
        //textViewPrecoCusto.setText(((StringBuilder) produtos.get(position).getPrecoCusto())););


        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                Integer retorno = produtoRepository.delete(produtos.get(position).get_id());
                if (retorno == 0)
                    mensagem = "Erro ao excluir registro!";
                Toast.makeText(listarProdutos, mensagem, Toast.LENGTH_LONG).show();
                atualizaLista();
            }
        });

        //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarProdutos, EditProdutoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("_id",produtos.get(position).get_id());
                listarProdutos.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    //atualizando a lista após excluir registro
    public void atualizaLista(){
        this.produtos.clear();
        this.produtos = produtoRepository.getAll();
        this.notifyDataSetChanged();
    }
}
