package com.example.dentiste1.model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dentiste1.connection.Column;
import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.connection.Requete;

public class Dent extends Requete{
    
    @Column(contrainte = 1)
    int idDent;
    @Column(contrainte = 0)
    String libelle;
    @Column(contrainte = 0)
    int numero;
    @Column(contrainte = 2)
    int idPlace;
    
    public Dent(int idDent, String libelle, int numero, int idPlace) {
        this.idDent = idDent;
        this.libelle = libelle;
        this.numero = numero;
        this.idPlace = idPlace;
    }
    public Dent() {
    }
    public int getIdDent() {
        return idDent;
    }
    public void setIdDent(int idDent) {
        this.idDent = idDent;
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

    public Dent[] getAll(Connection c)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Dent d = new Dent();
            Object[] o = d.select(c);
            List<Dent> all = new ArrayList<>();
            for (int i = 0; i < o.length; i++) {
                all.add((Dent)o[i]);
            }
            Dent[] reponse = new Dent[all.size()];
            return all.toArray(reponse);
        } catch (Exception e) {
            throw e;
        }finally{
            if (testco==true) {
                c.close();
            }
        }
    }

    public Dent get(Connection c, int idDent)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            Dent[] all = this.getAll(c);
            for (int i = 0; i < all.length; i++) {
                if (all[i].getIdDent()==idDent) {
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

    public void insertDent(Connection c, String nify, String note)throws Exception{
        Boolean testco = false;
        try {
            if (c==null||c.isClosed()) {
                Connect co = new Connect();
                c = co.connecter();
                testco = true;
            }
            // Statement st = c.createStatement();

            Statement st = c.createStatement();

            if (nify.contains(";")==true) {
                String[] allD = nify.split(";");
                for (int i = 0; i < allD.length; i++) {
                    allD[i] = allD[i].replace("D","");
                }
                String[] allNo = note.split(";");
                int[] allN = new int[allNo.length];
                for (int i = 0; i < allNo.length; i++) {
                    allN[i] = Integer.parseInt(allNo[i]);
                }
                for (int i = 0; i < allN.length; i++) {
                    String sql = "INSERT INTO note(idDent,etat)VALUES("+allD[i]+","+allN[i]+");";
                    System.out.println(sql);
                    Boolean ok = st.execute(sql);
                }
            }
            if (nify.contains("-")==true) {
                String[] allD = nify.split("-");
                int[] allDe = new int[allD.length];
                for (int i = 0; i < allD.length; i++) {
                    allD[i] = allD[i].replace("D","");
                    allDe[i] = Integer.parseInt(allD[i]);
                    System.out.println(allD[i]);
                }
                int not = Integer.parseInt(note);
                for (int i = 0 ; i < allDe.length; i++) {
                    String sql = "insert into note(iddent,etat)values("+allDe[i]+","+not+");";
                    System.out.println(sql);
                    Boolean ok = st.execute(sql);
                }
            }else if (nify.contains("-")==false&&nify.contains(";")==false){
                nify = nify.replace("D","");
                String sql = "insert into note(iddent,etat)values("+nify+","+note+");";
                System.out.println(sql);
                Boolean ok = st.execute(sql);
            }
            c.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            if (testco==true&&c==null) {
                c.close();
            }
        }
    }

    
}
