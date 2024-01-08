package com.example.dentiste1.model;

import java.util.ArrayList;
import java.util.List;

public class EtatDuDent {
    Dent dent;
    int etat;
    int ordre;
    public int getOrdre() {
        return ordre;
    }
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    public EtatDuDent() {
    }
    public EtatDuDent(Dent dent, int etat) {
        this.dent = dent;
        this.etat = etat;
    }
    public Dent getDent() {
        return dent;
    }
    public void setDent(Dent dent) {
        this.dent = dent;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public EtatDuDent[] getAllDentsMalade(EtatDuDent[] allD){
        List<EtatDuDent> all = new ArrayList<>();
        for (int i = 0; i < allD.length; i++) {
            if (allD[i].getEtat()!=10) {
                all.add(allD[i]);
                System.out.println("rezkhrk");
            }
        }
        EtatDuDent[] reponse = new EtatDuDent[all.size()];
        return all.toArray(reponse);
    }
    
}
