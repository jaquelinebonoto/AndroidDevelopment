package com.ssi.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout layoutEmail;
    private TextInputEditText txtEmail;
    private TextInputEditText txtSenha;
    private TextInputLayout layoutSenha;

    private Button btnLogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layoutEmail = findViewById(R.id.layoutEmail);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        layoutSenha = findViewById(R.id.layoutSenha);

        btnLogar = findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampo();
                Snackbar snackbar = Snackbar.make(view, "Login correto", Snackbar.LENGTH_LONG);
                snackbar.show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void validarCampo(){
        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Informe o seu email");
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(txtSenha.getText().toString().isEmpty()){
            layoutSenha.setErrorEnabled(true);
            layoutSenha.setError("Informe sua senha");
        }else{
            layoutEmail.setErrorEnabled(false);
        }
        Log.d("validacao", "saiu no validar");
    }
}
