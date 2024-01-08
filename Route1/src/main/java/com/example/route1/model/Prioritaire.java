package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.route1.connection.Column;
import com.example.route1.connection.Connect;
import com.example.route1.connection.Requete;

public class Prioritaire extends Requete{
    @Column(contrainte = 1)
    int idPrioritaire;
    @Column(contrainte = 0)
    String libelle;
    @Column(contrainte = 2)
    int idPlace;

    public Prioritaire(int idPrioritaire, String libelle, int idPlace) {
        this.idPrioritaire = idPrioritaire;
        this.libelle = libelle;
        this.idPlace = idPlace;
    }
    public Prioritaire() {
    }
    public int getIdPrioritaire() {
        return idPrioritaire;
    }
    public void setIdPrioritaire(int idPrioritaire) {
        this.idPrioritaire = idPrioritaire;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public Prioritaire[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prioritaire d = new Prioritaire();
            Object[] o = d.select(c);
            List<Prioritaire> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Prioritaire)o[i]);
            }
            Prioritaire[] reponse = new Prioritaire[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Prioritaire get(Connection c, int idPrioritaire)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prioritaire[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdPrioritaire()==idPrioritaire) {
                    return all[i];
                }
            } 
            return null;
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    

    
}
