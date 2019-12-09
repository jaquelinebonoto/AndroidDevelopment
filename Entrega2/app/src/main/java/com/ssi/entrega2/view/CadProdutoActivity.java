package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ssi.entrega2.R;
import com.ssi.entrega2.dao.ProdutoRepository;

public class CadProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        Button botao = findViewById(R.id.button);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProdutoRepository produtoRepository = new ProdutoRepository(getBaseContext());
                EditText nome = findViewById(R.id.editTextNome);
                Spinner cor = findViewById(R.id.spinner);
                EditText precoCusto =  findViewById((R.id.editTextPrecoCusto));
                EditText precoVenda =  findViewById((R.id.editTextPrecoVenda));
                EditText lucro =  findViewById((R.id.editTextLucro));

                 String resultado = produtoRepository.insert(
                        nome.getText().toString(),
                        cor.getPrompt().toString(),
                        Double.parseDouble(precoCusto.getText().toString()),
                        Double.parseDouble(precoVenda.getText().toString()),
                        Double.parseDouble(lucro.getText().toString())
                        );
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
    }

    private void limparCampos(){
        EditText nome = findViewById(R.id.editTextNome);
        EditText precoCusto = findViewById((R.id.editTextPrecoCusto));
        EditText precoVenda = findViewById(R.id.editTextPrecoVenda);
        EditText lucro = findViewById(R.id.editTextLucro);
        nome.setText("");
        precoCusto.setText("");
        precoVenda.setText("");
        lucro.setText("");
    }
}
