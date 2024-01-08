package com.example.dentiste1.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;

import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.model.Dent;

@WebServlet(name = "InsertDentServlet", value = "/InsertDentServlet")
public class InsertDentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connect co = new Connect();
            Connection c = co.connecter();
            String nify = request.getParameter("nify");
            String note = request.getParameter("note");
            Dent d = new Dent();
            d.insertDent(c, nify, note);
            response.sendRedirect("insertDent.jsp");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
