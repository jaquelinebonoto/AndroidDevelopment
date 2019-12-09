package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssi.entrega2.R;
import com.ssi.entrega2.dao.PedidoRepository;
import com.ssi.entrega2.model.Pedido;

public class EditPedidoActivity extends AppCompatActivity {

    private int _id=0;
    EditText editTextNomeCliente ;
    EditText editTextTelefone;
    EditText editTextDataPedido;
    EditText editTextValorPedido;

    private EditText editTextData;
    Button botaoEditar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produto);
        editTextNomeCliente = this.findViewById(R.id.editTextNomeClienteEdit);
        editTextTelefone =  this.findViewById(R.id.editTextTelefoneEdit);
        editTextDataPedido =  this.findViewById(R.id.editTextDataPedidoEdit);
        //editTextValorPedido = this.findViewById(R.id.editTextValorPedidoEdit);
        botaoEditar =  this.findViewById(R.id.buttonEditar);


        //cria evento para o botão editar
        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterar();
            }
        });

        PedidoRepository pedidoRepository = new PedidoRepository(this);

        //Pega o id do objeto que foi passado como parâmetro
        Bundle extra =  this.getIntent().getExtras();
        _id = extra.getInt("_id");

        //Pega o bojeto usando o id
        Pedido pedido = pedidoRepository.getPedido(_id);
        editTextNomeCliente.setText(pedido.getNomeCliente());
        editTextTelefone.setText(pedido.getTelefone());
        editTextDataPedido.setText(pedido.getDataPedido());
    }

    private void alterar(){
        if(editTextNomeCliente.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editTextNomeCliente.requestFocus();
        }
        else if(editTextTelefone.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Telefone é obrigatório!", Toast.LENGTH_LONG).show();
            editTextTelefone.requestFocus();
        }
        else if(editTextDataPedido.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Data é obrigatório!", Toast.LENGTH_LONG).show();
            editTextDataPedido.requestFocus();
        }
        /*else if(editTextValorPedido.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Valor é obrigatório!", Toast.LENGTH_LONG).show();
            editTextValorPedido.requestFocus();
        }*/
        else {
            Pedido pedido = new Pedido();
            pedido.set_id(_id);
            pedido.setNomeCliente(editTextNomeCliente.getText().toString().trim());
            pedido.setTelefone(editTextTelefone.getText().toString().trim());
            pedido.setDataPedido(editTextDataPedido.getText().toString().trim());
            //pedido.setValorPedido(editTextValorPedido.getText().toString().trim());

            int linhasAfetadas = new PedidoRepository(this).update(pedido);
            String msg = "Registro alterado com sucesso! ";
            if(linhasAfetadas == 0 ) msg = "Registro não foi alterado! ";
            //mostrando caixa de diálogo de sucesso
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(msg);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Forçando que o código retorne para a tela de consulta
                    Intent intent = new Intent(getApplicationContext(), PedidoActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
        }
    }
}
