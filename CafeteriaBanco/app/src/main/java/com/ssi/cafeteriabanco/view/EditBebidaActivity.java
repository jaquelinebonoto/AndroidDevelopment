package com.ssi.cafeteriabanco.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ssi.cafeteriabanco.R;
import com.ssi.cafeteriabanco.dao.BebidaRepository;
import com.ssi.cafeteriabanco.model.Bebida;

public class EditBebidaActivity extends AppCompatActivity {
    private int _id=0;
    EditText editTextNome ;
    EditText editTextDesc;
    Button botaoEditar ;
    private DatePickerDialog datePickerDialogData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bebida);
        //editTextNome = this.findViewById(R.id.editTextNomeEdit);
        //editTextDesc =  this.findViewById(R.id.editTextDescricaoEdit);
        botaoEditar =  this.findViewById(R.id.buttonEditar);

        //cria evento para o botão editar
        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterar();
            }
        });

        BebidaRepository bebidaRepository = new BebidaRepository(this);

        //Pega o id do objeto que foi passado como parâmetro
        Bundle extra =  this.getIntent().getExtras();
        _id = extra.getInt("_id");

        //Pega o bojeto usando o id
        Bebida bebida = bebidaRepository.getTarefa(_id);

        editTextNome.setText(bebida.getNome());
        editTextDesc.setText(bebida.getDescricao());
            }
    private void alterar(){
        if(editTextNome.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editTextNome.requestFocus();
        }
        else if(editTextDesc.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Descrição é obrigatório!", Toast.LENGTH_LONG).show();
            editTextDesc.requestFocus();
        } else {
            Bebida bebida = new Bebida();
            bebida.set_id(_id);
            bebida.setNome(editTextNome.getText().toString().trim());
            bebida.setDescricao(editTextDesc.getText().toString().trim());
            int linhasAfetadas = new BebidaRepository(this).update(bebida);
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
                    Intent intent = new Intent(getApplicationContext(), ListBebidaActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
        }
    }

}

