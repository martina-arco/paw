<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
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
                        <a href="<c:url value="/"/>"><input type="button" value="Make Changes" class="btn btn-light"/></a>
                    </div>
                    <div class="col text-md-right">
                        <p>25'</p>
                    </div>
                    <div class="col-3">
                        <div class="progress">
                            <div class="progress-bar green" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>

                <table class="table bg-white">

                    <thead class = "green">
                    <tr>
                        <th scope="col" class="width15"><spring:message code="stadium"/> </th>
                        <th scope="col" class="width15"><spring:message code="home"/> </th>
                        <th scope="col" colspan="2" class="width20"><spring:message code="score"/> </th>
                        <th scope="col" class="width15"><spring:message code="away"/> </th>
                        <th scope="col" class="width35"><spring:message code="events"/></th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var = "i" begin = "1" end = "6">
                        <tr>
                            <td class="width15">Monumental</td>
                            <td class="width15">River</td> 
                            <td class="width10" id="homeScore">1</td> 
                            <td class="width10" id="awayScore">0</td>
                            <td class="width15">Boca</td> 
                            <td class="width35" id="event">Gol</td>
                        </tr>
                    </c:forEach>

                    </tbody>

                    <%--<c:forEach items="${matches}" var="match">--%>
                        <%--<tr>--%>
                            <%--<td>${match.home.stadium}</td>--%>
                            <%--<td>${match.home}</td>--%>
                            <%--<td>${match.homeScore}</td>--%>
                            <%--<td>${match.awayScore}</td>--%>
                            <%--<td>${match.away}</td>--%>
                            <%--<td>${match.event}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>

                </table>
            </div>
    </div>


    <script src="/js/match.js"\></script>

</body>
</html>
