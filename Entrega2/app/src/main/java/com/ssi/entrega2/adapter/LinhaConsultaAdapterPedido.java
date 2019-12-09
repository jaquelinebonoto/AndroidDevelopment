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
import com.ssi.entrega2.dao.PedidoRepository;
import com.ssi.entrega2.model.Pedido;
import com.ssi.entrega2.view.EditPedidoActivity;
import com.ssi.entrega2.view.PedidoActivity;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultaAdapterPedido extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    List<Pedido> pedidos = new ArrayList<>();
    PedidoRepository pedidoRepository;

    //Cria objeto do tipo que lista os produtos
    private PedidoActivity listarPedidos;

    //Construtor que recebe a ativdade como parametro e a lista de produtos que vai retornar do BD
    public LinhaConsultaAdapterPedido(PedidoActivity listarPedidos, List<Pedido> pedidos ) {
        this.pedidos       =  pedidos;
        this.listarPedidos  =  listarPedidos;
        this.layoutInflater     = (LayoutInflater) this.listarPedidos.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        pedidoRepository   = new PedidoRepository(listarPedidos);
    }

    //Retorna a quantidade de objetos que esta na lista
    @Override
    public int getCount(){
        return pedidos.size();
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
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_ped, null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        TextView textViewCodigo = viewLinhaLista.findViewById(R.id.textViewCodigo);
        TextView textViewNomeCliente = viewLinhaLista.findViewById(R.id.textViewNomeCliente);
        TextView textViewTelefone = viewLinhaLista.findViewById(R.id.textViewTelefone);
        TextView textViewDataPedido = viewLinhaLista.findViewById(R.id.textViewDataPedido);
        TextView textViewValorPedido = viewLinhaLista.findViewById(R.id.textViewValorPedido);

        Button buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(pedidos.get(position).get_id()));
        textViewNomeCliente.setText(pedidos.get(position).getNomeCliente());
        textViewTelefone.setText(pedidos.get(position).getTelefone());
        textViewDataPedido.setText(pedidos.get(position).getDataPedido());
        //textViewValorPedido.set(pedidos.get(position).getValorPedido());
        //textViewPrecoCusto.setText(((StringBuilder) produtos.get(position).getPrecoCusto())););


        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                Integer retorno = pedidoRepository.delete(pedidos.get(position).get_id());
                if (retorno == 0)
                    mensagem = "Erro ao excluir registro!";
                Toast.makeText(listarPedidos, mensagem, Toast.LENGTH_LONG).show();
                atualizaLista();
            }
        });



    //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarPedidos, EditPedidoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("_id",pedidos.get(position).get_id());
                listarPedidos.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    //atualizando a lista após excluir registro
    public void atualizaLista(){
        this.pedidos.clear();
        this.pedidos = pedidoRepository.getAll();
        this.notifyDataSetChanged();
    }

}
