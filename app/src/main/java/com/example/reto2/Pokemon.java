package com.example.reto2;

public class Pokemon {
    private String id;
    private String selfId;
    private String name;
    private String defensa;
    private String ataque;
    private String velocidad;
    private String vida;
    private String img;

    public Pokemon() {
    }

    public Pokemon(String id, String selfId, String name, String defensa, String ataque, String velocidad, String vida, String img) {
        this.id = id;
        this.selfId = selfId;
        this.name = name;
        this.defensa = defensa;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.vida = vida;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getVida() {
        return vida;
    }

    public void setVida(String vida) {
        this.vida = vida;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }
}
