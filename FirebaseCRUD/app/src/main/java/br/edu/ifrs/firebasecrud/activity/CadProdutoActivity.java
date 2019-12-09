package br.edu.ifrs.firebasecrud.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import br.edu.ifrs.firebasecrud.R;
import br.edu.ifrs.firebasecrud.classe.Produto;
import br.edu.ifrs.firebasecrud.dao.ConfiguraFirebase;


public class CadProdutoActivity extends AppCompatActivity {
    //método que pega o nó raiz
    private DatabaseReference reference = ConfiguraFirebase.getNoRaiz();
    //método que pega o nó produtos
    //private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("produtos");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);
    }
    public void cadProduto(View view){
        String nome = ((EditText)findViewById(R.id.edtNomeProd)).getText().toString();
        String descricao = ((EditText)findViewById(R.id.edtDescProd)).getText().toString();
        Double valor = Double.parseDouble(((EditText)findViewById(R.id.edtValorProd)).getText().toString());
        Produto prod = new Produto(nome, descricao, valor);
        DatabaseReference produtos = reference.child("produtos");
        //gera um identificador único para cada produto
        //salva o produto na base de dados
        produtos.push().setValue(prod).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(CadProdutoActivity.this, "Sucesso ao cadastrar produto!", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CadProdutoActivity.this, "Erro ao cadastrar produto!", Toast.LENGTH_SHORT).show();
                    }
         });
    }
    private void limparCampos(){
        ((EditText)findViewById(R.id.edtNomeProd)).setText("Nome");
        ((EditText)findViewById(R.id.edtDescProd)).setText("Descrição");
        ((EditText)findViewById(R.id.edtValorProd)).setText("Valor unitário");
    }
}
