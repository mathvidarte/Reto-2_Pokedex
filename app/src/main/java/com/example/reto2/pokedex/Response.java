package com.example.reto2.pokedex;

public class Response {
    private MyPokemon [] forms;
    private DetailsPokemon [] stats;
    private PokePhoto sprites;

    public Response() {
    }

    public Response(MyPokemon[] forms, DetailsPokemon[] stats, PokePhoto sprites) {
        this.forms = forms;
        this.stats = stats;
        this.sprites = sprites;
    }

    public MyPokemon[] getForms() {
        return forms;
    }

    public void setForms(MyPokemon[] forms) {
        this.forms = forms;
    }

    public DetailsPokemon[] getStats() {
        return stats;
    }

    public void setStats(DetailsPokemon[] stats) {
        this.stats = stats;
    }

    public PokePhoto getSprites() {
        return sprites;
    }

    public void setSprites(PokePhoto sprites) {
        this.sprites = sprites;
    }
}
