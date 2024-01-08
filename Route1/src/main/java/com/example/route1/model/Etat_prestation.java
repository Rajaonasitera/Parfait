package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.route1.connection.Column;
import com.example.route1.connection.Connect;
import com.example.route1.connection.Requete;

public class Etat_prestation extends Requete{
    @Column(contrainte = 1)
    int idEtat_prestation;
    @Column(contrainte = 2)
    int idPrestation;
    @Column(contrainte = 0)
    int min;
    @Column(contrainte = 0)
    int max;
    public Etat_prestation(int idEtat_prestation, int idPrestation, int min, int max) {
            this.idEtat_prestation = idEtat_prestation;
            this.idPrestation = idPrestation;
            this.min = min;
            this.max = max;
    }

    public Etat_prestation() {
    }

    public int getIdEtat_prestation() {
        return idEtat_prestation;
    }

    public void setIdEtat_prestation(int idEtat_prestation) {
        this.idEtat_prestation = idEtat_prestation;
    }

    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Etat_prestation[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Etat_prestation d = new Etat_prestation();
            Object[] o = d.select(c);
            List<Etat_prestation> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Etat_prestation)o[i]);
            }
            Etat_prestation[] reponse = new Etat_prestation[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Etat_prestation get(Connection c, int idEtat_prestation)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Etat_prestation[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdEtat_prestation()==idEtat_prestation) {
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
