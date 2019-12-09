package com.ssi.entrega4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ssi.entrega4.MainActivity;
import com.ssi.entrega4.R;

public class InfoActivity extends AppCompatActivity{

    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);

            }


        });
    }
    }


