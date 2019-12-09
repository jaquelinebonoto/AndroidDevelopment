package br.edu.ifrs.ciclovidaatividade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "método onCreate()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity", "método onStart()");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity", "método onRestart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", "método onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", "método onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity", "método onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity", "método onDestroy()");
    }
}
