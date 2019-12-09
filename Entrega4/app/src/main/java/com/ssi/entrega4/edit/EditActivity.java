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

public class EditActivity extends AppCompatActivity implements Button.OnClickListener{
    Button button;
    String chave;
    TextInputEditText nomeCliente;
    TextInputEditText telefone;
    TextInputEditText dataPedido;
    TextInputEditText valorPedido;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        nomeCliente = findViewById(R.id.txtNomeCliente);
        nomeCliente.setText(intent.getStringExtra("nomeCliente"));
        telefone = findViewById(R.id.txtTelefone);
        telefone.setText(intent.getStringExtra("telefone"));
        dataPedido = findViewById(R.id.txtDataPedido);
        dataPedido.setText(intent.getStringExtra("dataPedido"));
        valorPedido = findViewById(R.id.txtValorPedido);
        valorPedido.setText(intent.getStringExtra("valorPedido"));
        chave = intent.getStringExtra("chave");
        button = findViewById(R.id.btnEditPed);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("pedidos").child(chave);
        Pedido p = new Pedido(nomeCliente.getText().toString(), telefone.getText().toString(), dataPedido.getText().toString(), Double.parseDouble(valorPedido.getText().toString()) );
        reference.setValue(p);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
