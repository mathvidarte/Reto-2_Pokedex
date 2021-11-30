package com.example.reto2;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.api.Context;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.UUID;

public class PokeAdapter extends RecyclerView.Adapter<PokeView> {

    //private Context context;

    private ArrayList <Pokemon> pokemons;
    private String theUrl;
    private AtraparActivity atrapar;

    public PokeAdapter (AtraparActivity context) {
        pokemons = new ArrayList<>();
        this.atrapar=context;


    }

    public void addTheCatchPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        notifyItemInserted(pokemons.size()-1);
    }

    public void searchPokemon (Pokemon pokemon) {
        pokemons.clear();

    }

    @NonNull
    @Override
    public PokeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokerow,parent, false);
        PokeView skeleton = new PokeView(row);
        return skeleton;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeView skeleton, int position) {

        Pokemon pokemon = pokemons.get(position);
        skeleton.getNameRow().setText(pokemon.getName());

        skeleton.setPokemon(pokemon);
        //skeleton.listener(this);
        Glide.with(atrapar).load(pokemon.getImg()).centerCrop().into(skeleton.getImgRow());
        skeleton.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(atrapar, DetalleActivity.class);
                Gson gson = new Gson();
                String pokemontext = gson.toJson(pokemon);
                intent.putExtra("pokemonText", pokemontext);
                atrapar.startActivity(intent);
                atrapar.finish();

            }
        });


        //skeleton.getImgRow().setImageBitmap(pokemon.getImg());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

}
