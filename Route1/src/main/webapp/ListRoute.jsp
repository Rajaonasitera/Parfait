<%@ page import="com.example.route1.model.Route" %><%--
  Created by IntelliJ IDEA.
  User: taraj
  Date: 07/01/2024
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="./Header.jsp"%>
<%
    Route[] allR = (Route[]) request.getAttribute("allR");
%>
<html>
<div class="page-wrapper">
    <div class="page-container">
        <div class="main-content">
<body>
  <form action="PageAccueilServlet" method="get">
      <div style="width: 400px; height: 200px; border: 1px solid black; border-radius: 5px; margin: auto; text-align: center; padding-top: 50px">
    <p>Route :<select name="route" id="" style="background-color: white; border: none  ">
        <%for (int j = 0; j < allR.length; j++) {%>
        <option value="<%out.print(allR[j].getIdRoute());%>"><%out.print(allR[j].getLibelle());%></option>
        <%}%>
    </select>
    </p>
    <input type="submit" value="Voir les petites route">
      </div>
  </form>
</body>
        </div>
    </div>
</div>
</html>
<%@include file="./Footer.jsp"%>
