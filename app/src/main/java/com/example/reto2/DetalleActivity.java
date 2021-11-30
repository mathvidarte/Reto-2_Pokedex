package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView defenseTV, attackTV, speedTV, hpTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imgDetail = findViewById(R.id.imgDetail);
        defenseTV = findViewById(R.id.defense);
        attackTV = findViewById(R.id.attack);
        speedTV = findViewById(R.id.speed);
        hpTV = findViewById(R.id.hp);
    }
}