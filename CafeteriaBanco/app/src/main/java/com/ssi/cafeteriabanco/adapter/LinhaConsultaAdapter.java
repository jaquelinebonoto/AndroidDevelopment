package com.ssi.cafeteriabanco.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ssi.cafeteriabanco.R;
import com.ssi.cafeteriabanco.dao.BebidaRepository;
import com.ssi.cafeteriabanco.model.Bebida;
import com.ssi.cafeteriabanco.view.EditBebidaActivity;
import com.ssi.cafeteriabanco.view.ListBebidaActivity;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    List<Bebida> bebidas =  new ArrayList<>();
    BebidaRepository bebidaRepository ;

    //Cria objeto do tipo que lista as tarefas
    private ListBebidaActivity listarBebidas;

    //Construtor que recebe a ativida como parametro e a lista de tarefas que vai retornar do BD
    public LinhaConsultaAdapter(ListBebidaActivity listarBebidas, List<Bebida> bebidas ) {
        this.bebidas       =  bebidas;
        this.listarBebidas  =  listarBebidas;
        this.layoutInflater     = (LayoutInflater) this.listarBebidas.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bebidaRepository  = new BebidaRepository(listarBebidas);
    }

    //Retorna a quantidade de objetos que esta na lista
    @Override
    public int getCount(){
        return bebidas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método converte os valores de um item  da lista de Tarefas para uma linha do ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha,null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        TextView textViewCodigo = viewLinhaLista.findViewById(R.id.textViewCodigo);
        TextView textViewNome  =  viewLinhaLista.findViewById(R.id.textViewNome);
        TextView textViewDescricao = viewLinhaLista.findViewById(R.id.textViewDescricao);
        TextView textViewData = viewLinhaLista.findViewById(R.id.textViewData);
        Button buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(bebidas.get(position).get_id()));
        textViewNome.setText(bebidas.get(position).getNome());
        textViewDescricao.setText(bebidas.get(position).getDescricao());

        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                Integer retorno = bebidaRepository.delete(bebidas.get(position).get_id());
                if(retorno == 0)
                    mensagem = "Erro ao excluir registro!";
                Toast.makeText(listarBebidas, mensagem, Toast.LENGTH_LONG).show();
                atualizaLista();
            }
        });

        //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarBebidas, EditBebidaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("_id", bebidas.get(position).get_id());
                listarBebidas.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    //atualizando a lista após excluir registro
    public void atualizaLista(){
        this.bebidas.clear();
        this.bebidas = bebidaRepository.getAll();
        this.notifyDataSetChanged();
    }
}
