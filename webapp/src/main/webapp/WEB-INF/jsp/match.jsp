<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/match.css"/>"/>
</head>
<body>
    <c:url value="/match" var="matchUrl" />
    <jsp:include page="navBar.jsp" />

    <div class="contain">
        <div class = "translucent"></div>
            <div class = "content">

                <div class="row">
                    <div class="col">
                        <a href="<c:url value="/"/>"><input type="button" value="Change conditions" class="btn btn-light"/></a>
                    </div>
                    <div class="col text-md-right">
                        <p>25'</p>
                    </div>
                    <div class="col-3">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>



                <table class="table">

                    <thead>
                    <tr>
                        <th scope="col" class="width1"><spring:message code="stadium"/> </th>
                        <th scope="col" class="width1"><spring:message code="home"/> </th>
                        <th scope="col" colspan="2" class="width4"><spring:message code="score"/> </th>
                        <th scope="col" class="width1"><spring:message code="away"/> </th>
                        <th scope="col" class="width2"><spring:message code="events"/></th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var = "i" begin = "1" end = "6">
                        <tr>
                            <td class="width1">Monumental</td>
                            <td class="width1">River</td> 
                            <td class="width3">1</td> 
                            <td class="width3">0</td>
                            <td class="width1">Boca</td> 
                            <td class="width2">Gol</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <%--<c:forEach items="${playerName}" var="player">--%>
                    <%--<tr>--%>
                    <%--<td>${player.name}</td>--%>
                    <%--<td>${player.salary}</td>--%>
                    <%--<td>${player.value}</td>--%>
                    <%--<td>${player.fitness}</td>--%>
                    <%--<td>${player.age}</td>--%>
                    <%--</tr>--%>
                    <%--</c:forEach>--%>

                </table>
            </div>
    </div>


</body>
</html>
