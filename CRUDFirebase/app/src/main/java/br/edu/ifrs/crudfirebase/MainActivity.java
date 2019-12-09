package br.edu.ifrs.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference artistasReference;
    DatabaseReference filmesReference;
    String idArtista ="";
    String idFilme ="";
    ArrayList<Artista> artistasList = new ArrayList();
    ArrayList<Filme> filmesList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        artistasReference = FirebaseDatabase.getInstance().getReference().child("artistas");

        //C - cadastrando um artista
       idArtista = artistasReference.push().getKey();
        Artista artista = new Artista(idArtista, "Johnny Depp", "Filmes");
        artistasReference.child(idArtista).setValue(artista).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("cad", "artista deu certo");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("cad", "artista deu errado");
        }});

        //R - Recuperando os artistas
        /*artistasReference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Artista artistaObj = ds.getValue(Artista.class);
                    artistasList.add(artistaObj);
                    Log.d("list", "adicionou na lista => " + artistaObj.toString());
                }
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //U - Atualizando um objeto
       /* artistasReference = FirebaseDatabase.getInstance().getReference("artistas").child(idArtista);
        artista.setAreaAtuacao("Música");
        artistasReference.setValue(artista);*/

        //D - Deletando um objeto
       /* artistasReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistasReference.child(idArtista).removeValue();
                Log.d("del", "deu certo");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("cad", "deu errado");
            }
        });*/

        //C - cadastrando um filme
        filmesReference = FirebaseDatabase.getInstance().getReference().child("filmes");

        idFilme = filmesReference.push().getKey();
        idArtista = "-LtKl4dKMmeUqIBT3vdH";
        Filme filme = new Filme(idFilme, "Alice Através do Espelho", "Ação", idArtista);

        filmesReference.child(idFilme).setValue(filme).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("cad", "filme deu certo");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Log.d("cad", "filme deu errado");
        }});

        //R - Recuperando os filmes
        filmesReference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Filme filmeObj = ds.getValue(Filme.class);
                    filmesList.add(filmeObj);
                    Log.d("list", "adicionou na lista => " + filmeObj.toString());
                }
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Listar todos os filmes de um artista
        Query query1 = filmesReference.orderByChild("idArtista").equalTo(idArtista);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Filme filmeObj = ds.getValue(Filme.class);
                    filmesList.add(filmeObj);
                    Log.d("query", "filmes do artista => " + filmeObj.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }
}
