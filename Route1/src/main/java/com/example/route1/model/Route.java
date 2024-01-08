package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.route1.connection.Column;
import com.example.route1.connection.Connect;
import com.example.route1.connection.Requete;

public class Route extends Requete{
    @Column(contrainte = 1)
    int idRoute;
    @Column(contrainte = 0)
    String libelle;
    @Column(contrainte = 0)
    double km;

    public Route(int idRoute, String libelle, double km) {
        this.idRoute = idRoute;
        this.libelle = libelle;
        this.km = km;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public Route() {
    }
    
    public int getIdRoute() {
        return idRoute;
    }
    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Route[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Route d = new Route();
            Object[] o = d.select(c);
            List<Route> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Route)o[i]);
            }
            Route[] reponse = new Route[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Route get(Connection c, int idRoute)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Route[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdRoute()==idRoute) {
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
