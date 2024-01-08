package com.example.route1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.route1.connection.Connect;

public class Consultation {
    EtatDeLaRoute etatDeLaRoute;
    PrixAction[] allAction;
    Double argent;
    
    
    public Consultation(EtatDeLaRoute etatDeLaRoute, PrixAction[] allAction, Double argent) {
        this.etatDeLaRoute = etatDeLaRoute;
        this.allAction = allAction;
        this.argent = argent;
    }

    public Consultation() {
    }

    public EtatDeLaRoute getEtatDeLaRoute() {
        return etatDeLaRoute;
    }

    public void setEtatDeLaRoute(EtatDeLaRoute etatDeLaRoute) {
        this.etatDeLaRoute = etatDeLaRoute;
    }

    public PrixAction[] getAllAction() {
        return allAction;
    }

    public void setAllAction(PrixAction[] allAction) {
        this.allAction = allAction;
    }

    public Double getArgent() {
        return argent;
    }

    public void setArgent(Double argent) {
        this.argent = argent;
    }
    
    public Prestation getPrestationEncours(Connection c, EtatDeLaRoute etatDeLaRoute)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Prestation p = new Prestation();
            Etat_prestation ep = new Etat_prestation();
            Etat_prestation[] allEP = ep.getAll(c);
            int id = 0;
            for (int i = 0; i < allEP.length; i++) {
                if ((allEP[i].getMin()<etatDeLaRoute.getEtat()&&allEP[i].getMax()>etatDeLaRoute.getEtat())||allEP[i].getMax()==etatDeLaRoute.getEtat()||allEP[i].getMin()==etatDeLaRoute.getEtat()) {
                    id = allEP[i].getIdPrestation();
                }
            }
            if (id!=0) {
                p = p.get(c, id);
            }
        return p;
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Action[] actionForOneTeeth(Connection c, EtatDeLaRoute etat)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            EtatDeLaRoute etatDeLaRoute = etat;
            int et = 0;
            et = et + etat.getEtat();
            Prestation p = new Prestation();
            Prestation[] allP = p.getAll(c);
            Action encours = new Action();
            encours.setPrestation(this.getPrestationEncours(c, etatDeLaRoute));
            encours.setNombre(1);
            // etatDeLaRoute.setEtat(etatDeLaRoute.getEtat()+encours.getPrestation().getPlusEtat());
            et = et+p.getPlusEtat();
            List<Action> all = new ArrayList<>();
            if (et>=10){
                all.add(encours);
            }
            while (et<10) {
//                System.out.println("ato");
//                System.out.println(etatDeLaRoute.getEtat());
                p = this.getPrestationEncours(c, etatDeLaRoute);
//                System.out.println(p.getLibelle());
//                System.out.println("--"+encours.getPrestation().getLibelle());
                if (p.getIdPrestation()!=encours.getPrestation().getIdPrestation()) {
                    all.add(encours);
                    encours = new Action(p,1);
                    // etatDeLaRoute.setEtat(etatDeLaRoute.getEtat()+p.getPlusEtat());
                    et = et+p.getPlusEtat();
                }else if (p.getIdPrestation()==encours.getPrestation().getIdPrestation()){
                    encours.setNombre(encours.getNombre()+1);
                    // etatDeLaRoute.setEtat(etatDeLaRoute.getEtat()+p.getPlusEtat());
                    et = et+p.getPlusEtat();
                }
                if (et>=10){
                    all.add(encours);
                }
            }
            Action[] reponse = new Action[all.size()];
        return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Consultation analyseOneTeeth(Connection c, EtatDeLaRoute d)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Action[] all = this.actionForOneTeeth(c, d);
            PrixAction[] px = new PrixAction[all.length];
            Prix[] allP = (new Prix()).getAll(c);
            Prix prix = new Prix();
            double somme = 0;
            for (int i = 0; i < all.length; i++) {
//                System.out.println();
//                allP[0].
                prix = prix.get(c, all[i].getPrestation().getIdPrestation(), d.getPetiteRoute().getIdPetiteRoute(), allP);
                px[i] = new PrixAction(all[i],prix.getPrix());
//                px[i].setAction(all[i]);
//                px[i].setPrixUnitaire(prix.getPrix());
                somme = somme + (prix.getPrix()*all[i].getNombre());
            }
            Consultation con = new Consultation(d, px,somme);
            return con;
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    // public int valeurAbsolue(int nb){
    //     if (nb<0) {
    //         return nb*-1;
    //     }else{
    //         return nb;
    //     }
    // }

    // public List<EtatDeLaRoute> getTrieDents(List<EtatDeLaRoute> all, int idPriorite)throws Exception{
    //     int nb = getIsa(all.get(0).getPetiteRoute().getIdRoute())/2;
        
    //     for (int i = 0; i < all.size(); i++) {
    //         all.get(i).setOrdre(valeurAbsolue(all.get(i).getPetiteRoute().getNumero()-nb));
    //     }

    //     Comparator<EtatDeLaRoute> comparator = Comparator.comparingInt(obj->obj.getOrdre());
    //     Collections.sort(all,comparator);
    //     return all;
    // }

    // public int getIsa(int idRoute)throws Exception{
    //     try {
    //         PetiteRoute r = new PetiteRoute();
    //         Object[] o = r.select(null);
    //         return o.length;
    //     } catch (Exception e) {
    //         throw e;
    //         // TODO: handle exception
    //     }
    // }




    public EtatDeLaRoute[] TriePriorite(Connection c, EtatDeLaRoute[] allPetiteRoutesMalade, Prioritaire prioritaire)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            List<EtatDeLaRoute> all = new ArrayList<>();
            for (int i = 0; i < allPetiteRoutesMalade.length; i++) {
                if (allPetiteRoutesMalade[i].getPetiteRoute().getIdPlace()==prioritaire.getIdPlace()) {
                    all.add(allPetiteRoutesMalade[i]);
                }
            }
            for (int i = 0; i < allPetiteRoutesMalade.length; i++) {
                if (allPetiteRoutesMalade[i].getPetiteRoute().getIdPlace()!=prioritaire.getIdPlace()) {
                    all.add(allPetiteRoutesMalade[i]);
                }
            }
            EtatDeLaRoute[] reponse = new EtatDeLaRoute[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    // public EtatDeLaRoute[] TriePriorite(Connection c, EtatDeLaRoute[] allDentMalade, Prioritaire prioritaire)throws Exception{
    //     Boolean testco = false;
    //     try {
    //         if (c==null||c.isClosed()) {
    //             Connect co = new Connect();
    //             c = co.connecter();
    //             testco = true;
    //         }
    //         List<EtatDeLaRoute> all1 = new ArrayList<>();
    //         for (int i = 0; i < allDentMalade.length; i++) {
    //             if (allDentMalade[i].getPetiteRoute().getIdPlace()==prioritaire.getIdPlace()) {
    //                 all1.add(allDentMalade[i]);
    //             }
    //         }
    //         all1 = getTrieDents(all1, prioritaire.getIdPrioritaire());
    //         List<EtatDeLaRoute> all2 = new ArrayList<>();
    //         for (int i = 0; i < allDentMalade.length; i++) {
    //             if (allDentMalade[i].getPetiteRoute().getIdPlace()!=prioritaire.getIdPlace()) {
    //                 all2.add(allDentMalade[i]);
    //             }
    //         }
    //         all2 = getTrieDents(all2, prioritaire.getIdPrioritaire());
    //         List<EtatDeLaRoute> all = new ArrayList<>();
    //         for (int i = 0; i < all1.size(); i++) {
    //             all.add(all1.get(i));
    //         }
    //         for (int i = 0; i < all2.size(); i++) {
    //             all.add(all2.get(i));
    //         }
    //         EtatDeLaRoute[] reponse = new EtatDeLaRoute[all.size()];
    //         return all.toArray(reponse);
    //     } catch (Exception e) {
    //         throw e;
    //     }finally{
    //         if (testco==true) {
    //             c.close();
    //         }
    //     }
    // }

    public Consultation[] getAllConsultationValide(Connection c,EtatDeLaRoute[] allEtatPetiteRoute, Prioritaire prioritaire, Double budget)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            EtatDeLaRoute[] allDentMalade = (new EtatDeLaRoute()).getAllPetiteRoutesMalade(allEtatPetiteRoute);
            EtatDeLaRoute[] allDentMaladeTrier = this.TriePriorite(c, allDentMalade, prioritaire);
            Double prix = budget;
            List<Consultation> all = new ArrayList<>();
            Consultation con = new Consultation();
            for (int i = 0; i < allDentMaladeTrier.length; i++) {
                con = this.analyseOneTeeth(c, allDentMaladeTrier[i]);
                if (prix - con.getArgent()>=0) {
                    all.add(con);
                    prix = prix - con.getArgent();
                }else if (prix - con.getArgent()<0) {
                    break;
                }
            }
            Consultation[] reponse = new Consultation[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }
    
}
