package com.ssi.cafeteriabanco.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ssi.cafeteriabanco.R;
import com.ssi.cafeteriabanco.dao.BebidaRepository;

public class CadBebidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_bebida);
        Button botao = findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BebidaRepository bebidaRepository = new BebidaRepository(getBaseContext());
                EditText nome = findViewById(R.id.editTextNome);
                EditText descricao =  findViewById((R.id.editTextDescricao));
                String resultado = bebidaRepository.insert(nome.getText().toString(), descricao.getText().toString());
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
