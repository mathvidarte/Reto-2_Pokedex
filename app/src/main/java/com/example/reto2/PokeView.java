package com.example.reto2;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokeView extends RecyclerView.ViewHolder {

    private ImageView imgRow;
    private TextView nameRow;

    private Pokemon pokemon;

    private AtraparActivity atrapar;

      public PokeView(@NonNull View itemView) {
        super(itemView);


        imgRow = itemView.findViewById(R.id.imgRow);
        nameRow = itemView.findViewById(R.id.nameRow);

        nameRow.setOnClickListener(this::openDetails);
    }

    public void openDetails (View view) {
        //Intent intent = new Intent(, DetalleActivity.class);
        //atrapar.startActivity(intent);
        //Log.e(">>>>>>", "HOLI");

    }

    public void setPokemon (Pokemon pokemon) {
          this.pokemon = pokemon;
    }

    public ImageView getImgRow() {
        return imgRow;
    }

    public TextView getNameRow() {
        return nameRow;
    }

}
