package com.ssi.applistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lista;
    Uri geoLocation = Uri.parse("geo:0,0?q=-30.0264, " + "-51.2233058(IFRS POA)?z=15");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.listaPrincipal);
        //o this informa que esta classe irá tratar os eventos desta lista
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> a, View view, int position, long id) {
        if(position == 0){ //clicou em Ufs
            //chamar uma explicita
            Intent intent = new Intent (this, ListaUFsActivity.class);
            startActivity(intent);
        } if (position == 1){ //clicou em Campus Poa
            //chamar uma implicita
            showMap(geoLocation);
        } if (position == 2) { //clicou em sobre
            //chamar uma toast
            Toast.makeText(getApplicationContext(), "Meu App", Toast.LENGTH_LONG).show();
        }
    }

    public void showMap(Uri geoLocation){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
