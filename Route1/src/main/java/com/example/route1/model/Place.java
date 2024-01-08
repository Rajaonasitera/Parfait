package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.route1.connection.Column;
import com.example.route1.connection.Connect;
import com.example.route1.connection.Requete;

public class Place extends Requete {
    @Column(contrainte = 1)
    int idPlace;
    @Column(contrainte = 0)
    String libelle;

    public Place(int idPlace, String libelle) {
        this.idPlace = idPlace;
        this.libelle = libelle;
    }
    public Place() {
    }
    public int getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Place[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Place d = new Place();
            Object[] o = d.select(c);
            List<Place> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Place)o[i]);
            }
            Place[] reponse = new Place[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Place get(Connection c, int idPlace)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Place[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdPlace()==idPlace) {
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
