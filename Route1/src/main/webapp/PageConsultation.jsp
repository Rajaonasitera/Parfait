<%@ page import="com.example.route1.model.Prioritaire" %>
<%@ page import="com.example.route1.model.PetiteRoute" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./Header.jsp"%>
<%
    PetiteRoute[] allR = (PetiteRoute[]) request.getAttribute("petiteRoute");
    Prioritaire[] allP = (Prioritaire[]) request.getAttribute("prioritaire");
    int id = (Integer)request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<div class="page-wrapper">
    <div class="page-container">

        <div class="main-content">
                <body style="text-align: center">
                <h1></h1>
                <br>
                <h4>Entrez l'etat des routes</h4>

                            <form action="ResultatConsultationServlet" method="get">
                                <input type="hidden" name="id" value="<%out.print(id);%>">
                                    <%for (int i = 0; i < allR.length; i++) {%>
                                        <p><%out.print(allR[i].getLibelle());%>
                                            <select name="petiteRoute <%out.print(allR[i].getIdPetiteRoute());%>" id="">
                                            <%
                                                for (int j =10; j > -1 ; j--) {%>
                                                <option value="<%out.print(j);%>"><%out.print(j);%></option>
                                                <%}%>
                                            </select>
                                        </p>
                                    <%}%>
                                <div style="text-align: center">
                                    <div style="margin-top: 30px">
                                        <h4>Votre priorite : </h4>
                                    <select name="prioritaire" id="" style="background-color: white; border: 1px solid black;border-radius: 5px; ">
                                        <%for (int j = 0; j < allP.length; j++) {%>
                                        <%System.out.println(allP[j].getLibelle());%>
                                        <option value="<%out.print(allP[j].getIdPrioritaire());%>"><%out.print(allP[j].getLibelle());%></option>
                                        <%}%>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group" style="margin-top: 20px">
                                    <h4 style="text-align: center">Votre Budget</h4>
<%--                                    <label for="budget" class="control-label mb-1">Budget</label>--%>
                                    <input id="budget" name="budget" type="number" class="form-control">
                                </div>

                                <div style="width: 400px; margin: auto" >
                                    <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                        <span id="payment-button-amount">Faire la consultation</span>
                                    </button>
                                </div>
                            </form>
                        </div>
                </body>
        </div>
    </div>
</div>
<%@include file="./Footer.jsp"%>