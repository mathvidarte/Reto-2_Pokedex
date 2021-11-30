package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.reto2.pokedex.Response;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;

import java.util.UUID;

public class AtraparActivity extends AppCompatActivity {

    private EditText atraparET, searchET;
    private Button atraparBtn, searchBtn;
    private RecyclerView pokeRecycler;

    private LinearLayoutManager manager;
    private PokeAdapter adapter;

    private String pokemonSearched;
    private String userId;
    private String selfId;
    private String photoUrl;

    private Pokemon pokemon;

    //String namePoke = searchET.getText().toString();



    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrapar);


        atraparET = findViewById(R.id.atraparET);
        atraparBtn = findViewById(R.id.atraparBtn);
        pokeRecycler = findViewById(R.id.pokeRecycler);
        searchET = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);

        manager = new LinearLayoutManager(this);
        pokeRecycler.setLayoutManager(manager);
        pokeRecycler.setHasFixedSize(true);
        adapter = new PokeAdapter(this);
        pokeRecycler.setAdapter(adapter);

        userId = getIntent().getExtras().getString("uuid");

        atraparBtn.setOnClickListener(this::catchPokemon);
        searchBtn.setOnClickListener(this::buscador);

        FirebaseFirestore.getInstance().collection("Pokedex").document(userId).collection("Pokemon").get().addOnCompleteListener(
                task -> {
                    for(DocumentSnapshot doc : task.getResult()){
                        Pokemon pokemon = doc.toObject(Pokemon.class);
                        adapter.addTheCatchPokemon(pokemon);
                        adapter.notifyDataSetChanged();
                    }
                }
        );
    }

    private void catchPokemon(View view) {
        pokemonSearched = atraparET.getText().toString();
        searchPokemon(pokemonSearched);
    }

    public void searchPokemon (String name) {

        new Thread(
                ()-> {
                    HTTPSWebUtilDomi web = new HTTPSWebUtilDomi();
                    String json = web.GETrequest(name);

                    //Deserializar
                    Gson gson = new Gson();
                    Response response = gson.fromJson(json, Response.class);
                    //volver string los int
                    String attack = Integer.toString(response.getStats()[1].getBase_stat());
                    String defense = Integer.toString(response.getStats()[2].getBase_stat());
                    String speed = Integer.toString(response.getStats()[5].getBase_stat());
                    String hp = Integer.toString(response.getStats()[0].getBase_stat());
                    photoUrl = response.getSprites().getFront_default();
                    Log.e("nombre", response.getForms()[0].getName());
                    Log.e("attack", attack);
                    Log.e("attack", defense);
                    Log.e("attack", speed);
                    Log.e("attack", hp);
                    Log.e("carga", photoUrl);

                    selfId = UUID.randomUUID().toString();

                    //Crear el obj Pokemon con los datos
                    Pokemon pokemon = new Pokemon(
                            userId,
                            selfId,
                            response.getForms()[0].getName(),
                            attack,
                            defense,
                            speed,
                            hp,
                            photoUrl);

                    //Agregar al recycler el obj
                    adapter.addTheCatchPokemon(pokemon);

                    //subir a la firebase
                    addPokemon(new Pokemon(
                            userId,
                            selfId,
                            response.getForms()[0].getName(),
                            attack,
                            defense,
                            speed,
                            hp,
                            response.getSprites().getFront_default()
                    ));
                }
        ).start();


    }

    //Leer lo que tengo en la db
    public void getMyPokemon () {
        db.collection("Pokedex").document(userId).collection("Pokemon")
                .get().addOnCompleteListener(
                        task -> {
                            for(DocumentSnapshot doc : task.getResult()) {
                                Pokemon pokemon = doc.toObject(Pokemon.class);
                                Log.e(">>>>", pokemon.getName());
                                Log.e("<<<<", pokemon.getAtaque());
                            }
                        }
        );
    }
    public void addPokemon (Pokemon pokemon) {
        FirebaseFirestore.getInstance().collection("Pokedex").document(userId).collection("Pokemon").document(selfId).set(pokemon);
    }

    //LO INTENTAMOS :(
    public void buscador (View view) {

        String nombreBuscado = searchET.getText().toString();

        Query query = db.collection("Pokedex").document(userId).collection("Pokemon").whereEqualTo("name", nombreBuscado);
        query.get().addOnCompleteListener(
                task -> {
                    //Si el pokemon no existe
                    if (task.getResult().size() == 0) {
                        Toast.makeText(this, "No Existe", Toast.LENGTH_SHORT).show();
                    }else {
                        Pokemon existingPokemon = null;
                        for(DocumentSnapshot doc : task.getResult()) {
                            existingPokemon = doc.toObject(Pokemon.class);
                            break;
                        }
                        if (existingPokemon.getName().equals(nombreBuscado)){
                            for(DocumentSnapshot doc : task.getResult()){
                                Pokemon pokemon = doc.toObject(Pokemon.class);
                                adapter.searchPokemon(pokemon);
                                adapter.addTheCatchPokemon(pokemon);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );
    }



}