package com.example.dentiste1.model;

public class Action {
    Prestation prestation;
    int nombre;
    public Action(Prestation prestation, int nombre) {
        this.prestation = prestation;
        this.nombre = nombre;
    }
    public Action() {
    }
    public Prestation getPrestation() {
        return prestation;
    }
    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }
    public int getNombre() {
        return nombre;
    }
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
}
