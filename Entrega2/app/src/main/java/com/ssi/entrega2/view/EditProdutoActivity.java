package com.ssi.entrega2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ssi.entrega2.R;
import com.ssi.entrega2.dao.ProdutoRepository;
import com.ssi.entrega2.model.Produto;

public class EditProdutoActivity extends AppCompatActivity {

        private int _id=0;
        EditText editTextNome ;
        EditText editTextPrecoCusto;
        EditText editTextPrecoVenda;
        EditText editTextLucro;

        private EditText editTextData;
        Button botaoEditar ;
        private DatePickerDialog datePickerDialogData;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_produto);
            editTextNome = this.findViewById(R.id.editTextNomeEdit);
            editTextPrecoCusto =  this.findViewById(R.id.editTextPrecoCustoEdit);
            //editTextPrecoVenda =  this.findViewById(R.id.editTextPrecoVendaEdit);
            //editTextLucro = this.findViewById(R.id.editTextLucroEdit);
            botaoEditar =  this.findViewById(R.id.buttonEditar);


            //cria evento para o botão editar
            botaoEditar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    alterar();
                }
            });

            ProdutoRepository produtoRepository = new ProdutoRepository(this);

            //Pega o id do objeto que foi passado como parâmetro
            Bundle extra =  this.getIntent().getExtras();
            _id = extra.getInt("_id");

            //Pega o bojeto usando o id
            Produto produto = produtoRepository.getProduto(_id);
            editTextNome.setText(produto.getNome());
        }

        private void alterar(){
            if(editTextNome.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
                editTextNome.requestFocus();
            }
            else {
                Produto produto = new Produto();
                produto.set_id(_id);
                produto.setNome(editTextNome.getText().toString().trim());
                int linhasAfetadas = new ProdutoRepository(this).update(produto);
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
                        Intent intent = new Intent(getApplicationContext(), ProdutoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.show();
            }
        }

}
