package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.UUID;

public class DetalleActivity extends AppCompatActivity {

    private Pokemon pokemon;
    private ImageView imgDetail;
    private TextView defenseTV, attackTV, speedTV, hpTV, nameTV;
    private Button baibai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imgDetail = findViewById(R.id.imgDetail);
        defenseTV = findViewById(R.id.defense);
        attackTV = findViewById(R.id.attack);
        speedTV = findViewById(R.id.speed);
        hpTV = findViewById(R.id.hp);
        nameTV = findViewById(R.id.nameDetail);
        baibai = findViewById(R.id.baibai);

        String pokeText = getIntent().getExtras().getString("pokemonText");
        Log.e("ReciboText", pokeText);
        Gson gson = new Gson();
        pokemon = gson.fromJson(pokeText, Pokemon.class);
        Log.e("ReciboOBJ", pokemon.getName());

        cargarDetalle();

        baibai.setOnClickListener(
                v -> {
                    //String id = FirebaseFirestore.getInstance().collection("Pokedex").document(pokemon.getId()).collection("Pokemon").getId();
                    FirebaseFirestore.getInstance().collection("Pokedex").document(pokemon.getId()).collection("Pokemon").document(pokemon.getSelfId()).delete();
                    Intent intent = new Intent(this, AtraparActivity.class);
                    intent.putExtra("uuid", pokemon.getId());
                    startActivity(intent);
                    finish();
                }
        );
    }

    public void cargarDetalle () {
        nameTV.setText(pokemon.getName());
        defenseTV.setText(pokemon.getDefensa());
        attackTV.setText(pokemon.getAtaque());
        speedTV.setText(pokemon.getVelocidad());
        hpTV.setText(pokemon.getVida());
        Glide.with(this).load(pokemon.getImg()).into(imgDetail);
    }
}