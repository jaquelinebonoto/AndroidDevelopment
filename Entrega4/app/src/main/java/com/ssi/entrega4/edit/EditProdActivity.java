package com.ssi.entrega4.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssi.entrega4.MainActivity;
import com.ssi.entrega4.R;
import com.ssi.entrega4.classe.Pedido;
import com.ssi.entrega4.classe.Produto;

public class EditProdActivity extends AppCompatActivity implements Button.OnClickListener{
    Button button;
    String chave;
    TextInputEditText nome;
    TextInputEditText cor;
    TextInputEditText precoCusto;
    TextInputEditText precoVenda;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prod);
        Intent intent = getIntent();
        nome = findViewById(R.id.txtNome);
        nome.setText(intent.getStringExtra("nome"));
        cor = findViewById(R.id.txtCor);
        cor.setText(intent.getStringExtra("cor"));
        precoCusto = findViewById(R.id.txtPrecoCusto);
        precoCusto.setText(intent.getStringExtra("precoCusto"));
        precoVenda = findViewById(R.id.txtPrecoVenda);
        precoVenda.setText(intent.getStringExtra("precoVenda"));
        chave = intent.getStringExtra("chave");
        button = findViewById(R.id.btnEditProd);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("produtos").child(chave);
        Produto p = new Produto(
                nome.getText().toString(),
                cor.getText().toString(),
                Double.parseDouble(precoCusto.getText().toString()),
                Double.parseDouble(precoVenda.getText().toString()) );
        reference.setValue(p);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
