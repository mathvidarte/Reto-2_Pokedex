package com.example.reto2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.UUID;

public class PokeAdapter extends RecyclerView.Adapter<PokeView> {

    private ArrayList <Pokemon> pokemons;

    public PokeAdapter () {
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
        return skeleton;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeView skeleton, int position) {

        Pokemon pokemon = pokemons.get(position);
        skeleton.getNameRow().setText(pokemon.getName());

        //skeleton.getImgRow().setImageBitmap(pokemon.getImg());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


}
