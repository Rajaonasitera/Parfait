package com.example.dentiste1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.dentiste1.connection.Column;
import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.connection.Requete;

public class Prix extends Requete{
    @Column(contrainte = 1)
    int idPrix;
    @Column(contrainte = 2)
    int idPrestation;
    @Column(contrainte = 2)
    int idDent;
    @Column(contrainte = 0)
    double prix;

    public Prix(int idPrix, int idPrestation, int idDent, Double prix) {
        this.idPrix = idPrix;
        this.idPrestation = idPrestation;
        this.idDent = idDent;
        this.prix = prix;
    }
    public Prix() {
    }
    public int getIdPrix() {
        return idPrix;
    }
    public void setIdPrix(int idPrix) {
        this.idPrix = idPrix;
    }
    public int getIdPrestation() {
        return idPrestation;
    }
    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }
    public int getIdDent() {
        return idDent;
    }
    public void setIdDent(int idDent) {
        this.idDent = idDent;
    }
    public Double getPrix() {
        return prix;
    }
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Prix[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prix d = new Prix();
            Object[] o = d.select(c);
            List<Prix> all = new ArrayList<>();
//            System.out.println(o.length);
            for (int i = 0; i < o.length; i++) {
//                System.out.println(((Prix)o[i]).get());
                all.add((Prix)o[i]);
//                System.out.println(all.get(i).getPrix());

            }
            Prix[] reponse = new Prix[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Prix get(Connection c, int idPrix)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prix[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdPrix()==idPrix) {
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

    public Prix get(Connection c, int idPrestation,int idDent, Prix[] all)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            for (int i = 0; i < all.length; i++) {
//                System.out.println(all[i].getIdPrestation()+""+idPrestation+"--"+all[i].getIdDent()+""+idDent);
                if (all[i].getIdPrestation()==idPrestation&&all[i].getIdDent()==idDent) {
//                    System.out.println("ato");
//                    System.out.println(all[i].getPrix());
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
