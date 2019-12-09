package com.ssi.entrega2.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssi.entrega2.R;
import com.ssi.entrega2.dao.PedidoRepository;

public class CadPedidoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_pedido);

        Button botao = findViewById(R.id.button);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PedidoRepository produtoRepository = new PedidoRepository(getBaseContext());
                EditText nomecliente = findViewById(R.id.editTextNomeCliente);
                EditText telefone = findViewById(R.id.editTextTelefone);
                EditText dataPedido = findViewById(R.id.editTextDataPedido);
                EditText valorPedido = findViewById(R.id.editTextValorPedido);
                String resultado = produtoRepository.insert(
                        nomecliente.getText().toString(),
                        telefone.getText().toString(),
                        dataPedido.getText().toString(),
                        Double.parseDouble(valorPedido.getText().toString())
                );
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
    }

    private void limparCampos(){
        EditText nomecliente = findViewById(R.id.editTextNomeCliente);
        EditText telefone = findViewById(R.id.editTextTelefone);
        EditText dataPedido = findViewById(R.id.editTextDataPedido);
        EditText valorPedido = findViewById(R.id.editTextValorPedido);
        nomecliente.setText("");
        telefone.setText("");
        dataPedido.setText("");
        valorPedido.setText("");
    }
}
