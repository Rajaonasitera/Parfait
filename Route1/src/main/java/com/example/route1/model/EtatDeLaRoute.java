package com.example.route1.model;

import java.util.ArrayList;
import java.util.List;

public class EtatDeLaRoute {
    PetiteRoute petiteRoute;
    int etat;
    int ordre;
    public int getOrdre() {
        return ordre;
    }
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    public EtatDeLaRoute() {
    }
    public EtatDeLaRoute(PetiteRoute petiteRoute, int etat) {
        this.petiteRoute = petiteRoute;
        this.etat = etat;
    }
    public PetiteRoute getPetiteRoute() {
        return petiteRoute;
    }
    public void setPetiteRoute(PetiteRoute petiteRoute) {
        this.petiteRoute = petiteRoute;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public EtatDeLaRoute[] getAllPetiteRoutesMalade(EtatDeLaRoute[] allE){
        List<EtatDeLaRoute> all = new ArrayList<>();
        for (int i = 0; i < allE.length; i++) {
            if (allE[i].getEtat()!=10) {
                all.add(allE[i]);
                // System.out.println("rezkhrk");
            }
        }
        EtatDeLaRoute[] reponse = new EtatDeLaRoute[all.size()];
        return all.toArray(reponse);
    }
    
}
