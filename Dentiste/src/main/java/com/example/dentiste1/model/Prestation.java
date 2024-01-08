package com.example.dentiste1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.dentiste1.connection.Column;
import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.connection.Requete;

public class Prestation extends Requete{
    @Column(contrainte = 1)
    int idPrestation;
    @Column(contrainte = 0)
    String libelle;
    @Column(contrainte = 0)
    int plusEtat;

    public Prestation(int idPrestation, String libelle, int plusEtat) {
        this.idPrestation = idPrestation;
        this.libelle = libelle;
        this.plusEtat = plusEtat;
    }
    public Prestation() {
    }
    public int getIdPrestation() {
        return idPrestation;
    }
    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getPlusEtat() {
        return plusEtat;
    }
    public void setPlusEtat(int plusEtat) {
        this.plusEtat = plusEtat;
    }

    public Prestation[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prestation d = new Prestation();
            Object[] o = d.select(c);
            List<Prestation> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Prestation)o[i]);
            }
            Prestation[] reponse = new Prestation[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Prestation get(Connection c, int idPrestation)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prestation[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdPrestation()==idPrestation) {
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
