package com.example.reto2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.api.Context;

import java.util.ArrayList;
import java.util.UUID;

public class PokeAdapter extends RecyclerView.Adapter<PokeView> implements AtraparActivity.OnURLListener {

    //private Context context;

    private ArrayList <Pokemon> pokemons;
    private String theUrl;
    private AtraparActivity atrapar;

    public PokeAdapter () {
        //this.context = context;
        pokemons = new ArrayList<>();


    }

    public void addTheCatchPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        notifyItemInserted(pokemons.size()-1);
    }

    @NonNull
    @Override
    public PokeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokerow,parent, false);
        PokeView skeleton = new PokeView(row);
        skeleton.setListener(this);
        return skeleton;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeView skeleton, int position) {

        Pokemon pokemon = pokemons.get(position);
        skeleton.getNameRow().setText(pokemon.getName());
        //skeleton.listener(this);
        //Glide.with(atrapar).load(theUrl).centerCrop().into(skeleton.getImgRow());


        //skeleton.getImgRow().setImageBitmap(pokemon.getImg());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    //RECIBO DEL PATRÓN OBSERVER
    @Override
    public void onURL(String url) {
        Log.e("recibo", url);
    }
}
