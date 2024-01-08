package com.example.route1.model;

public class PrixAction {
    Action action;
    Double prixUnitaire;
    public PrixAction(Action action, Double prixUnitaire) {
        this.action = action;
        this.prixUnitaire = prixUnitaire;
    }
    public PrixAction() {
    }
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public Double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
