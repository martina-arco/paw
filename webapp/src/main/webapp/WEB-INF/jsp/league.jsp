<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>"/>

</head>
<body>
    <c:url value="/league" var="leagueUrl" />

    <jsp:include page="navBar.jsp" />

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">

            <div class="row">
                <div class="col">

                    <h5><spring:message code="upcomingMatches"/> </h5>

                    <table class="table bg-white small center">

                        <tbody>

                        <c:forEach var = "i" begin = "1" end = "5">
                            <tr>
                                <td>River</td> 
                            </tr>
                        </c:forEach>

                        <%--<c:forEach items="upcomingMatches" var="match">--%>
                            <%--<tr>--%>
                                <%--<td><c:out value="match"/></td> --%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>

                        </tbody>

                    </table>


                </div>

                <div class="col">

                    <table class="table bg-white small center">

                        <thead>
                        <tr>
                            <th scope="col"><spring:message code="team"/> </th>
                            <th scope="col"><spring:message code="points"/> </th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var = "i" begin = "1" end = "5">
                            <tr>
                                <td>River</td> 
                                <td>20</td> 
                            </tr>
                        </c:forEach>

                        <%--<c:forEach items="teams" var="team">--%>
                            <%--<tr>--%>
                                <%--<td><c:out value="${team.key}"/></td> --%>
                                <%--<td><c:out value="${team.value}"/></td> --%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>

                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    </div>

</body>
</html>
