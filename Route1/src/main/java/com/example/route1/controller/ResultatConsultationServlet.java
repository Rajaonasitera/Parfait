package com.example.route1.controller;

import com.example.route1.connection.Connect;
import com.example.route1.model.Consultation;
import com.example.route1.model.EtatDeLaRoute;
import com.example.route1.model.PetiteRoute;
import com.example.route1.model.Prioritaire;
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
            int id = Integer.parseInt(request.getParameter("id"));
            PetiteRoute d = new PetiteRoute();
            PetiteRoute[] allD = d.getAllByRoute(c,id);
            List<EtatDeLaRoute> all = new ArrayList<>();
            EtatDeLaRoute ed = new EtatDeLaRoute();
            for (int i = 0; i < allD.length; i++) {
                EtatDeLaRoute et = new EtatDeLaRoute();
                et.setPetiteRoute(allD[i]);
                et.setEtat(Integer.parseInt(request.getParameter("petiteRoute "+allD[i].getNumero())));
//                System.out.println(et.getEtat());
//                all.add(new EtatDeLaRoute(allD[i],Integer.parseInt(request.getParameter("PetiteRoute "+allD[i].getNumero()))));
//                System.out.println(Integer.parseInt(request.getParameter("PetiteRoute "+allD[i].getNumero())));
                all.add(et);
//                System.out.println(all.get(i).getPetiteRoute().getIdPetiteRoute());
//                System.out.println(all.get(i).getEtat());
            }
//            String test = request.getParameter("PetiteRoute 1");
//            System.out.println(test);
            EtatDeLaRoute[] allE = new EtatDeLaRoute[all.size()];
            allE = all.toArray(allE);
            allE = ed.getAllPetiteRoutesMalade(allE);
            System.out.println(all.get(0).getEtat());
            List<EtatDeLaRoute> tst = all;
            request.setAttribute("petiteRoute",allE);
//            System.out.println(allE.length);
            int idPrioritaire = Integer.parseInt(request.getParameter("prioritaire"));
            double budget = Double.parseDouble(request.getParameter("budget"));
            Prioritaire p = new Prioritaire();
            p = p.get(c,idPrioritaire);
            Consultation con = new Consultation();
            Consultation[] allC = con.getAllConsultationValide(c,allE,p,budget);
            double somme = 0.0;
            for (int i = 0; i < allC.length; i++) {
//                System.out.println(allC[i].getEtatDeLaRoute().getPetiteRoute().getIdPetiteRoute());
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
