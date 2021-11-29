package com.example.reto2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokeView extends RecyclerView.ViewHolder {

    private ImageView imgRow;
    private TextView nameRow;

      public PokeView(@NonNull View itemView) {
        super(itemView);

        imgRow = itemView.findViewById(R.id.imgRow);
        nameRow = itemView.findViewById(R.id.nameRow);
    }

    public ImageView getImgRow() {
        return imgRow;
    }

    public TextView getNameRow() {
        return nameRow;
    }

    public void listener(PokeAdapter pokeAdapter) {

    }
}
