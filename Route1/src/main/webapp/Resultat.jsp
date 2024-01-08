
<%@ page import="java.util.List" %>
<%@ page import="com.example.route1.model.EtatDeLaRoute" %>
<%@ page import="com.example.route1.model.Consultation" %>
<%@ page import="com.example.route1.model.Prioritaire" %><%--
  Created by IntelliJ IDEA.
  User: taraj
  Date: 06/01/2024
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./Header.jsp"%>
<%
  Consultation[] allC = (Consultation[]) request.getAttribute("allC");
  EtatDeLaRoute[] allD = (EtatDeLaRoute[]) request.getAttribute("petiteRoute");
  Prioritaire p = (Prioritaire) request.getAttribute("prioritaire");
  double somme = (double)request.getAttribute("somme");
  double reste = (double)request.getAttribute("reste");
  double budget = (double)request.getAttribute("budget");
    System.out.println("hljhjdq 331");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<div class="page-wrapper">
  <div class="page-container">
    <div class="main-content">
<body>
  <table class="table table-bordered" style="width: 1000px; margin: auto">
    <tr>
        <th >Petite route</th>
        <th >Prix</th>
        <th colspan="3">Action</th>
    </tr>
    <% for (int i = 0; i < allC.length; i++) { %>
        <tr>
            <td ><% out.print(allC[i].getEtatDeLaRoute().getPetiteRoute().getLibelle()); %></td>
            <td ><% out.print(allC[i].getArgent()); %></td>
            <td>
            <% for (int j = 0; j < allC[i].getAllAction().length; j++) { %>
                <% out.print(allC[i].getAllAction()[j].getAction().getPrestation().getLibelle()); %>
            </br>
            <% } %>
            </td>
            <td>
            <% for (int j = 0; j < allC[i].getAllAction().length; j++) { %>
                <% out.print(allC[i].getAllAction()[j].getAction().getNombre()); %>
                </br>
            <% } %>
            </td>
            <td>
            <% for (int j = 0; j < allC[i].getAllAction().length; j++) { %>
            <% out.print(allC[i].getAllAction()[j].getPrixUnitaire()); %>
                </br>
            <% } %>
            </td>
        </tr>

    <% } %>
</table>
  <center style="width: 500px; height: 150px; margin: auto; border: 1px black dashed; border-radius: 5px;padding-top: 20px; margin-top: 10px; margin-bottom: 10px">
      <h2>Votre budget = <%out.print(budget);%></h2>
      <h2>Somme = <%out.print(somme);%></h2>
      <h2>reste = <%out.print(reste);%></h2>
  </center>

  <table class="table table-bordered" style="width: 1000px; margin: auto">
      <tr>
          <th>Numero</th>
          <th >Petite route</th>
          <th >Etat</th>
      </tr>
      <% for (int i = 0; i < allD.length; i++) { %>
      <tr>
          <td ><% out.print(allD[i].getPetiteRoute().getNumero());%></td>
          <td ><% out.print(allD[i].getPetiteRoute().getLibelle());%></td>
          <td ><% out.print(allD[i].getEtat());%></td>
      </tr>
      <% } %>
  </table>
    <h1 style="color: red"><%out.print(p.getLibelle());%></h1>
</body>
</div>
</div>
</div>
</html>
<%@include file="./Footer.jsp"%>