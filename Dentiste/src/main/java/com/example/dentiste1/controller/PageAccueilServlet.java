package com.example.dentiste1.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;

import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.model.Dent;
import com.example.dentiste1.model.Prioritaire;

@WebServlet(name = "PageAccueilServlet", value = "/PageAccueilServlet")
public class PageAccueilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connect co = new Connect();
            Connection c = co.connecter();

            Dent d = new Dent();
            Dent[] allD = d.getAll(c);
            Prioritaire p = new Prioritaire();
            Prioritaire[] allP = p.getAll(c);
            request.setAttribute("dent", allD);            
            request.setAttribute("prioritaire", allP);
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
