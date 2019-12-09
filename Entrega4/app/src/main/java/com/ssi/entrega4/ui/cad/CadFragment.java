package com.ssi.entrega4.ui.cad;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.ssi.entrega4.R;
import com.ssi.entrega4.classe.Pedido;
import com.ssi.entrega4.dao.ConfiguraFirebase;

public class CadFragment extends Fragment implements Button.OnClickListener{

    Button button;
    TextInputEditText nomeCliente;
    TextInputEditText telefone;
    TextInputEditText valorPedido;
    TextInputEditText dataPedido;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_cad_pedido, container, false);
        button = root.findViewById(R.id.btnCadPed);
        button.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        nomeCliente = root.findViewById(R.id.txtNomeCliente);
        telefone = root.findViewById(R.id.txtTelefone);
        dataPedido = root.findViewById(R.id.txtDataPedido);
        valorPedido = root.findViewById(R.id.txtValorPedido);
        Pedido pedido = new Pedido(nomeCliente.getText().toString(), telefone.getText().toString(), dataPedido.getText().toString(), Double.parseDouble(valorPedido.getText().toString()));
        cadastrar(pedido);
    }

    private void cadastrar(Pedido pedido) {
        DatabaseReference reference = ConfiguraFirebase.getNoRaiz();
        DatabaseReference produtos = reference.child("pedidos");
        //gera um identificador único para cada produto
        //salva o produto na base de dados
        produtos.push().setValue(pedido).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("cad", "deu certo");
                Toast.makeText(getContext(), "Sucesso ao cadastrar produto!", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("cad", "deu errado");
                        Toast.makeText(getContext(), "Erro ao cadastrar produto!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void limparCampos(){
        ((TextInputEditText)root.findViewById(R.id.txtNomeCliente)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtTelefone)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtDataPedido)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtValorPedido)).setText("");
    }
}
