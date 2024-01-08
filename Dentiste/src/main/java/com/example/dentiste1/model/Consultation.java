package com.example.dentiste1.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.example.dentiste1.connection.Connect;

public class Consultation {
    EtatDuDent etatDuDent;
    PrixAction[] allAction;
    Double argent;
    
    
    public Consultation(EtatDuDent etatDuDent, PrixAction[] allAction, Double argent) {
        this.etatDuDent = etatDuDent;
        this.allAction = allAction;
        this.argent = argent;
    }

    public Consultation() {
    }

    public EtatDuDent getEtatDuDent() {
        return etatDuDent;
    }

    public void setEtatDuDent(EtatDuDent etatDuDent) {
        this.etatDuDent = etatDuDent;
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
    
    public Prestation getPrestationEncours(Connection c, EtatDuDent etatDuDent)throws Exception{
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
                if ((allEP[i].getMin()<etatDuDent.getEtat()&&allEP[i].getMax()>etatDuDent.getEtat())||allEP[i].getMax()==etatDuDent.getEtat()||allEP[i].getMin()==etatDuDent.getEtat()) {
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

    public Action[] actionForOneTeeth(Connection c, EtatDuDent etat)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            EtatDuDent etatDuDent = etat;
            int et = 0;
            et = et + etat.getEtat();
            Prestation p = new Prestation();
            Prestation[] allP = p.getAll(c);
            Action encours = new Action();
            encours.setPrestation(this.getPrestationEncours(c, etatDuDent));
            encours.setNombre(1);
            // etatDuDent.setEtat(etatDuDent.getEtat()+encours.getPrestation().getPlusEtat());
            et = et+p.getPlusEtat();
            List<Action> all = new ArrayList<>();
            if (et>=10){
                all.add(encours);
            }
            while (et<10) {
//                System.out.println("ato");
//                System.out.println(etatDuDent.getEtat());
                p = this.getPrestationEncours(c, etatDuDent);
//                System.out.println(p.getLibelle());
//                System.out.println("--"+encours.getPrestation().getLibelle());
                if (p.getIdPrestation()!=encours.getPrestation().getIdPrestation()) {
                    all.add(encours);
                    encours = new Action(p,1);
                    // etatDuDent.setEtat(etatDuDent.getEtat()+p.getPlusEtat());
                    et = et+p.getPlusEtat();
                }else if (p.getIdPrestation()==encours.getPrestation().getIdPrestation()){
                    encours.setNombre(encours.getNombre()+1);
                    // etatDuDent.setEtat(etatDuDent.getEtat()+p.getPlusEtat());
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

    public Consultation analyseOneTeeth(Connection c, EtatDuDent d)throws Exception{
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
                prix = prix.get(c, all[i].getPrestation().getIdPrestation(), d.getDent().getIdDent(), allP);
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

    public int valeurAbsolue(int nb){
        if (nb<0) {
            return nb*-1;
        }else{
            return nb;
        }
    }

    public List<EtatDuDent> getTrieDents(List<EtatDuDent> all, int idPriorite){
        int nb = 0;
        if (idPriorite==1) {
            nb=8;
        }
        if (idPriorite==2) {
            nb=28;
        }
        for (int i = 0; i < all.size(); i++) {
            all.get(i).setOrdre(valeurAbsolue(all.get(i).getDent().getNumero()-nb));
        }

        Comparator<EtatDuDent> comparator = Comparator.comparingInt(obj->obj.getOrdre());
        Collections.sort(all,comparator);
        return all;
    }

    // public EtatDuDent[] TriePriorite(Connection c, EtatDuDent[] allDentMalade, Prioritaire prioritaire)throws Exception{
    //     Boolean testco = false;
    //     try {
    //         if (c==null||c.isClosed()) {
    //             Connect co = new Connect();
    //             c = co.connecter();
    //             testco = true;
    //         }
    //         List<EtatDuDent> all = new ArrayList<>();
    //         for (int i = 0; i < allDentMalade.length; i++) {
    //             if (allDentMalade[i].getDent().getIdPlace()==prioritaire.getIdPlace()) {
    //                 all.add(allDentMalade[i]);
    //             }
    //         }
    //         for (int i = 0; i < allDentMalade.length; i++) {
    //             if (allDentMalade[i].getDent().getIdPlace()!=prioritaire.getIdPlace()) {
    //                 all.add(allDentMalade[i]);
    //             }
    //         }
    //         EtatDuDent[] reponse = new EtatDuDent[all.size()];
    //         return all.toArray(reponse);
    //     } catch (Exception e) {
    //         throw e;
    //     }finally{
    //         if (testco==true) {
    //             c.close();
    //         }
    //     }
    // }

    public EtatDuDent[] TriePriorite(Connection c, EtatDuDent[] allDentMalade, Prioritaire prioritaire)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            List<EtatDuDent> all1 = new ArrayList<>();
            for (int i = 0; i < allDentMalade.length; i++) {
                if (allDentMalade[i].getDent().getIdPlace()==prioritaire.getIdPlace()) {
                    all1.add(allDentMalade[i]);
                }
            }
            all1 = getTrieDents(all1, prioritaire.getIdPrioritaire());
            List<EtatDuDent> all2 = new ArrayList<>();
            for (int i = 0; i < allDentMalade.length; i++) {
                if (allDentMalade[i].getDent().getIdPlace()!=prioritaire.getIdPlace()) {
                    all2.add(allDentMalade[i]);
                }
            }
            all2 = getTrieDents(all2, prioritaire.getIdPrioritaire());
            List<EtatDuDent> all = new ArrayList<>();
            for (int i = 0; i < all1.size(); i++) {
                all.add(all1.get(i));
            }
            for (int i = 0; i < all2.size(); i++) {
                all.add(all2.get(i));
            }
            EtatDuDent[] reponse = new EtatDuDent[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Consultation[] getAllConsultationValide(Connection c,EtatDuDent[] allEtatDent, Prioritaire prioritaire, Double budget)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            EtatDuDent[] allDentMalade = (new EtatDuDent()).getAllDentsMalade(allEtatDent);
            EtatDuDent[] allDentMaladeTrier = this.TriePriorite(c, allDentMalade, prioritaire);
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
