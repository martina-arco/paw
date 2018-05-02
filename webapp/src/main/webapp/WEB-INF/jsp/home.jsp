<%--
  Created by IntelliJ IDEA.
  User: martina
  Date: 02/05/2018
  Time: 14:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>" />
</head>
<body>
    <c:url value="/home" var="homeUrl" />

    <ul class="nav">
        <li ><a href = "<c:url value="finance.jsp" var="financesUrl"/>"><c:out value="Team name"/></a></li>
        <li ><a href = "<c:url value="finance.jsp" var="financesUrl"/>">Finances</a></li>
        <li ><a href = "<c:url value="youthAcademy.jsp" var="youthAcademyUrl"/>">Youth academy</a></li>
        <li ><a href = "<c:url value="stadium.jsp" var="stadiumUrl"/>">Stadium</a></li>
        <li ><a href = "<c:url value="league.jsp" var="leagueUrl"/>">League</a></li>
    </ul>

    <div class = "leftHalf">

        <div class="trust">
            <h4>Board trust: </h4>
            <h4>Fun trust: </h4>
        </div>

        <div class = "player">
            <h2>Perez (Age: 28)</h2>

            <hr>

            <h3>Attributes</h3>

            <div class = "container">
                <div class="left">
                    <h4>Finishing</h4>
                    <h4>Defending</h4>
                    <h4>Passing</h4>
                </div>
                <div class="right">
                    <h4>Potential</h4>
                    <h4>Goalkeeping</h4>
                    <h4>Fitness</h4>
                </div>
            </div>

            <hr>

            <h3>Finance</h3>

            <div class = "container">
                <div class="left">
                    <h4>Salary</h4>
                </div>
                <div class="right">
                    <h4>Value</h4>
                </div>
            </div>

            <hr>

            <div class = "center">
                <a href="<c:url value="/"/>"><input class = "leftBtn"  type="button" value="Sell"/></a>
                <a href="<c:url value="/"/>"><input type="button" value="Contract"/></a>
                <a href="<c:url value="/"/>"><input class = "rightBtn" type="button" value="Retire"/></a>
            </div>
        </div>

        <a href="<c:url value="/"/>"><input type="button" value="Formation"/></a>
    </div>

    <div class="rightHalf">

        <table>
            <tr>
                <th>Name</th>
                <th>Salary</th>
                <th>Value</th>
                <th>Fitness</th>
                <th>Age</th>
            </tr>

            <%--<c:forEach items="${playerName}" var="player">--%>
                <%--<tr>--%>
                    <%--<td>${player.name}</td>--%>
                    <%--<td>${player.salary}</td>--%>
                    <%--<td>${player.value}</td>--%>
                    <%--<td>${player.fitness}</td>--%>
                    <%--<td>${player.age}</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
                <tr>
                    <td><a href="<c:url value="/"/>">Perez</a></td>
                    <td>3,000</td>
                    <td>400k</td>
                    <td>93</td>
                    <td>35</td>
                </tr>

            <tr>
                <td>Perez</td>
                <td>3,000</td>
                <td>400k</td>
                <td>93</td>
                <td>35</td>
            </tr>
            <tr>
                <td>Perez</td>
                <td>3,000</td>
                <td>400k</td>
                <td>93</td>
                <td>35</td>
            </tr>
        </table>

    </div>


</body>
</html>
