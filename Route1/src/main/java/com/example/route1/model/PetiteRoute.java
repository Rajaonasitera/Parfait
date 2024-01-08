package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.route1.connection.Column;
import com.example.route1.connection.Connect;
import com.example.route1.connection.Requete;

public class PetiteRoute extends Requete{
    
    @Column(contrainte = 1)
    int idPetiteRoute;
    @Column(contrainte = 2)
    int idRoute;
    @Column(contrainte = 0)
    String libelle;
    @Column(contrainte = 0)
    int numero;
    @Column(contrainte = 2)
    int idPlace;
    
   

    public PetiteRoute() {
    }

    public PetiteRoute(int idPetiteRoute, int idRoute, String libelle, int numero, int idPlace) {
        this.idPetiteRoute = idPetiteRoute;
        this.idRoute = idRoute;
        this.libelle = libelle;
        this.numero = numero;
        this.idPlace = idPlace;
    }

    public int getIdPetiteRoute() {
        return idPetiteRoute;
    }

    public void setIdPetiteRoute(int idPetiteRoute) {
        this.idPetiteRoute = idPetiteRoute;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }
    
    public PetiteRoute[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            PetiteRoute d = new PetiteRoute();
            Object[] o = d.select(c);
            List<PetiteRoute> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((PetiteRoute)o[i]);
            }
            PetiteRoute[] reponse = new PetiteRoute[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public PetiteRoute get(Connection c, int idPetiteRoute)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            PetiteRoute[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdPetiteRoute()==idPetiteRoute) {
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

    public PetiteRoute[] getAllByRoute(Connection c, int idRoute)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            List<PetiteRoute> allP = new ArrayList<>();
            PetiteRoute[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdRoute()==idRoute) {
                    allP.add(all[i]);
                }
            } 
            PetiteRoute[] reponse = new PetiteRoute[allP.size()];
            return allP.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    

    

    
}
