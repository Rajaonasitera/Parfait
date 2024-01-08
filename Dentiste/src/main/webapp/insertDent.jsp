<%--
  Created by IntelliJ IDEA.
  User: taraj
  Date: 08/01/2024
  Time: 07:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="./Header.jsp"%>
<html>

<div class="page-wrapper">
    <div class="page-container">

        <div class="main-content">
            <center>
<body>
    <form action="InsertDentServlet" method="get">
      <p style="margin-bottom: 10px">Nify : <input type="text" name="nify" style="border-bottom: 1px black solid"></p>
      <p style="margin-bottom: 10px">Note : <input type="text" name="note" style="border-bottom: 1px black solid"></p>
        <input type="submit" value="inserer" style="background-color: #0a58ca; width: 100px; height: 50px;text-align: center">

    </form>
</body>
            </center>
        </div>
    </div>
</html>
<%@include file="./Footer.jsp"%>
