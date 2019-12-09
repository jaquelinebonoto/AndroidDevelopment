package com.ssi.entrega4.ui.cad;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.ssi.entrega4.R;
import com.ssi.entrega4.classe.Produto;
import com.ssi.entrega4.dao.ConfiguraFirebase;


public class CadProdFragment extends Fragment implements Button.OnClickListener{

    Button button;
    TextInputEditText nome;
    TextInputEditText cor;
    TextInputEditText precoCusto;
    TextInputEditText precoVenda;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_cad_produtos, container, false);
        button = root.findViewById(R.id.btnCadProd);
        button.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        nome = root.findViewById(R.id.txtNome);
        cor = root.findViewById(R.id.txtCor);
        precoCusto = root.findViewById(R.id.txtPrecoCusto);
        precoVenda = root.findViewById(R.id.txtPrecoVenda);
        Produto produto = new Produto(nome.getText().toString(), cor.getText().toString(), Double.parseDouble(precoCusto.getText().toString()),Double.parseDouble(precoVenda.getText().toString()));
        cadastrar(produto);
    }

    private void cadastrar(Produto produto) {
        DatabaseReference reference = ConfiguraFirebase.getNoRaiz();
        DatabaseReference produtos = reference.child("produtos");
        //gera um identificador único para cada produto
        //salva o produto na base de dados
        produtos.push().setValue(produto).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        ((TextInputEditText)root.findViewById(R.id.txtNome)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtCor)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtPrecoCusto)).setText("");
        ((TextInputEditText)root.findViewById(R.id.txtPrecoVenda)).setText("");
    }
}
