package com.example.route1.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;

import com.example.route1.connection.Connect;
import com.example.route1.model.PetiteRoute;
import com.example.route1.model.Prioritaire;

@WebServlet(name = "PageAccueilServlet", value = "/PageAccueilServlet")
public class PageAccueilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connect co = new Connect();
            Connection c = co.connecter();
            int id = Integer.parseInt(request.getParameter("route"));
            PetiteRoute r = new PetiteRoute();
            PetiteRoute[] allR = r.getAllByRoute(c,id);
            Prioritaire p = new Prioritaire();
            Prioritaire[] allP = p.getAll(c);
            request.setAttribute("petiteRoute", allR);
            request.setAttribute("prioritaire", allP);
            request.setAttribute("id",id);
            String lien = "PageConsultation";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(lien+".jsp");
            requestDispatcher.forward(request,response);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
