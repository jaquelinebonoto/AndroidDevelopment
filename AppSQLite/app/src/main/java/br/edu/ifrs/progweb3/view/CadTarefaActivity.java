package br.edu.ifrs.progweb3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.dao.TarefaRepository;


public class CadTarefaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tarefa);
        Button botao = findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaRepository tarefaRepository = new TarefaRepository(getBaseContext());
                EditText nome = findViewById(R.id.editTextNome);
                EditText descricao =  findViewById((R.id.editTextDescricao));
                EditText data =  findViewById(R.id.editTextData);
                String resultado = tarefaRepository.insert(nome.getText().toString(), descricao.getText().toString(), data.getText().toString());
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
    }
    private void limparCampos(){
        EditText nome = findViewById(R.id.editTextNome);
        EditText descricao = findViewById((R.id.editTextDescricao));
        EditText data = findViewById(R.id.editTextData);
        nome.setText("");
        descricao.setText("");
        data.setText("");
    }
}
