package br.edu.ifrs.progweb3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.adapter.LinhaConsultaAdapter;
import br.edu.ifrs.progweb3.dao.TarefaDAO;
import br.edu.ifrs.progweb3.model.Tarefa;

public class ListTarefasActivity extends AppCompatActivity {
    private ListView listTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tarefas);
        listTarefas = this.findViewById(R.id.listViewTarefas);
        this.getAll();
    }
     protected  void getAll(){
         TarefaDAO tarefaDAO = new TarefaDAO();
         List<Tarefa> tarefas = tarefaDAO.getAll();
         listTarefas.setAdapter(new LinhaConsultaAdapter(this, tarefas));
         if (tarefas != null) {
             for (Tarefa t : tarefas) {
                 Log.d("BD", t.getNome());
             }
         }
    }

}
