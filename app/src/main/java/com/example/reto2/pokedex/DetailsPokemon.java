package com.example.reto2.pokedex;

public class DetailsPokemon {
    private int base_stat;

    public DetailsPokemon() {
    }

    public DetailsPokemon(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }
}
