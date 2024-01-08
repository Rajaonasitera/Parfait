<%@ page import="com.example.dentiste1.model.Dent" %>
<%@ page import="com.example.dentiste1.model.Prioritaire" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./Header.jsp"%>
<%
    Dent[] allD = (Dent[]) request.getAttribute("dent");
    Prioritaire[] allP = (Prioritaire[]) request.getAttribute("prioritaire");
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
                <h4>Entrez l'etat de vos dents</h4>
                <div>
                <img src="styles/images/icon/yeux.png" style="width: 600px; height: 300px">
                </div>
                <div>
                <img src="styles/images/icon/nez.png" style="width: 100px; height: 100px; margin-bottom: 50px">
                </div>
                <div style="width: 1100px ; margin: auto; height: 160px; border: 1px solid black;padding-left: 40px; border-radius: 70px; text-align: start">
                            <form action="ResultatConsultationServlet" method="get">
                                <div>
                                    <%for (int i = 0; i < 16; i++) {%>
                                    <div style="width: 60px;height: 60px; border: 1px solid black;border-radius: 5px; display: inline-grid; margin-bottom: 20px; margin-top: 10px;">
                                    <p><%out.print(allD[i].getNumero());%></p>
                                        <select name="dent <%out.print(allD[i].getNumero());%>" id="" style="background-color: white; border: none  ">
                                            <%for (int j = 10; j > -1; j--) {%>
                                                <option value="<%out.print(j);%>"><%out.print(j);%></option>
                                            <%}%>
                                        </select>
                                        </div>
                                    <%}%>
                                </div>
                                <div >
                                    <%for (int i = 16; i < 32; i++) {%>
                                    <div style="width: 60px;height: 60px; border: 1px solid black;border-radius: 5px; display: inline-grid">
                                    <p><%out.print(allD[i].getNumero());%></p>
                                    <select name="dent <%out.print(allD[i].getIdDent());%>" id="" style="background-color: white; border: none  ">
                                        <%for (int j = 10; j > -1; j--) {%>
                                        <option value="<%out.print(j);%>"><%out.print(j);%></option>
                                        <%}%>
                                    </select>
                                    </div>
                                    <%}%>
                                </div>
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