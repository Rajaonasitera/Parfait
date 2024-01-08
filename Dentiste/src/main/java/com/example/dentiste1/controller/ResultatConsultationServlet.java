package com.example.dentiste1.controller;

import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.model.Consultation;
import com.example.dentiste1.model.Dent;
import com.example.dentiste1.model.EtatDuDent;
import com.example.dentiste1.model.Prioritaire;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResultatConsultationServlet", value = "/ResultatConsultationServlet")
public class ResultatConsultationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connect co = new Connect();
            Connection c = co.connecter();
            Dent d = new Dent();
            Dent[] allD = d.getAll(c);
            List<EtatDuDent> all = new ArrayList<>();
            EtatDuDent ed = new EtatDuDent();
            for (int i = 0; i < allD.length; i++) {
                EtatDuDent et = new EtatDuDent();
                et.setDent(allD[i]);
                et.setEtat(Integer.parseInt(request.getParameter("dent "+allD[i].getNumero())));
//                System.out.println(et.getEtat());
//                all.add(new EtatDuDent(allD[i],Integer.parseInt(request.getParameter("dent "+allD[i].getNumero()))));
//                System.out.println(Integer.parseInt(request.getParameter("dent "+allD[i].getNumero())));
                all.add(et);
//                System.out.println(all.get(i).getDent().getIdDent());
//                System.out.println(all.get(i).getEtat());
            }
//            String test = request.getParameter("dent 1");
//            System.out.println(test);
            EtatDuDent[] allE = new EtatDuDent[all.size()];
            allE = all.toArray(allE);
            allE = ed.getAllDentsMalade(allE);
            System.out.println(all.get(0).getEtat());
            List<EtatDuDent> tst = all;
            request.setAttribute("dent",allE);
//            System.out.println(allE.length);
            int idPrioritaire = Integer.parseInt(request.getParameter("prioritaire"));
            double budget = Double.parseDouble(request.getParameter("budget"));
            Prioritaire p = new Prioritaire();
            p = p.get(c,idPrioritaire);
            Consultation con = new Consultation();
            Consultation[] allC = con.getAllConsultationValide(c,allE,p,budget);
            double somme = 0.0;
            for (int i = 0; i < allC.length; i++) {
//                System.out.println(allC[i].getEtatDuDent().getDent().getIdDent());
                for (int j = 0; j < allC[i].getAllAction().length; j++) {
                    somme = somme + (allC[i].getAllAction()[j].getPrixUnitaire()*allC[i].getAllAction()[j].getAction().getNombre());
//                    System.out.println("--"+allC[i].getAllAction()[j].getAction().getPrestation().getLibelle());
                }
            }
            double reste = budget - somme;
            request.setAttribute("allC",allC);
            request.setAttribute("somme",somme);
            request.setAttribute("reste",reste);
            request.setAttribute("budget",budget);
            request.setAttribute("prioritaire",p);

            String lien = "Resultat";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(lien+".jsp");
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
