package com.example.route1.controller;

import com.example.route1.connection.Connect;
import com.example.route1.model.Route;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "PageListRouteServlet", value = "/PageListRouteServlet")
public class PageListRouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connect co = new Connect();
            Connection c = co.connecter();
            Route r = new Route();
            Route[] allR = r.getAll(c);
            request.setAttribute("allR",allR);
            String lien = "ListRoute";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(lien+".jsp");
            requestDispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
