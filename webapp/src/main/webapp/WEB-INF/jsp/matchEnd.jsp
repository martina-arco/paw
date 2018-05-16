<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/match.css"/>"/>
</head>
<body>
    <c:url value="/matchEnd" var="matchEndUrl" />

    <jsp:include page="navBar.jsp" />

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">

            <div class="row">
                <div class="col">
                    <table class="table bg-white width60 table-sm">

                        <thead class="green">
                        <tr>
                            <th scope="col" ><spring:message code="home"/> </th>
                            <th scope="col" colspan="2"><spring:message code="score"/> </th>
                            <th scope="col"><spring:message code="away"/> </th>
                        </tr>
                        </thead>

                        <tbody>

                        <%--<c:forEach var = "i" begin = "1" end = "6">--%>
                            <%--<tr>--%>
                                <%--<td>River</td> --%>
                                <%--<td>1</td> --%>
                                <%--<td>0</td>--%>
                                <%--<td>Boca</td> --%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>

                        <c:forEach items="${matches}" var="match">
                            <tr>
                                <td>${match.home.name}</td>
                                <td>${match.homeScore}</td>
                                <td>${match.awayScore}</td>
                                <td>${match.away.name}</td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>

                <div class="col">

                    <div class="row green center big-height">

                        <div class="col">
                            <h5><c:out value="${match.home.name}"/></h5>
                        </div>
                        <div class="col">
                            <h5><spring:message code="homeAndAwayScore" arguments="${match.homeScore},${match.awayScore}"/></h5>
                        </div>
                        <div class="col">
                            <h5><c:out value="${match.away.name}"/></h5>
                        </div>
                    </div>

                    <div class="container center">
                        <h5><c:out value="${stadium.name}"/></h5>
                    </div>

                    <h6><spring:message code="goals"/> </h6>

                    <table class="table width60 table-borderless table-center">
                        <tbody>
                            <c:forEach items="${match.events}" var="event">
                                <c:if test="${event.type == 'SCORE' || event.type == 'YELLOW_CARD' || event.type == 'RED_CARD'}">
                                    <tr>
                                        <td>
                                        <c:if test="${event.p1.teamId == match.homeId}">
                                            <spring:message code="event.${event.type}" arguments="${event.minute},${event.p1.name}"/>
                                        </c:if>
                                        </td>
                                        <td>
                                        <c:if test="${event.p1.teamId == match.awayId}">
                                            <spring:message code="event.${event.type}" arguments="${event.minute},${event.p1.name}"/>
                                        </c:if>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>


                                <%--<c:forEach items="${homeScores}" var="score">--%>
                                    <%--<tr>--%>
                                        <%--<td>${score.key} ${score.value}'</td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>

                                <%--<c:forEach items="${awayScores}" var="score">--%>
                                    <%--<tr>--%>
                                        <%--<td></td>--%>
                                        <%--<td>${score.key} ${score.value}'</td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>

                        </tbody>
                    </table>

                    <%-- Esto de playerStats por ahora no
                    <h6><spring:message code="matchStats"/> </h6>

                    <table class="table width60 table-borderless table-center">
                        <tbody>

                        <tr>
                            <td><c:out value="${homeSaves}"/></td> 
                            <td><spring:message code="saves"/> </td> 
                            <td><c:out value="${awaySaves}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${homePasses}"/></td>
                            <td><spring:message code="passes"/> </td> 
                            <td><c:out value="${awayPasses}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${homeTackles}"/></td>
                            <td><spring:message code="tackles"/> </td> 
                            <td><c:out value="${awayTackles}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${homeAssists}"/></td>
                            <td><spring:message code="assists"/> </td> 
                            <td><c:out value="${awayAssists}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${homeFouls}"/></td>
                            <td><spring:message code="fouls"/> </td> 
                            <td><c:out value="${awayFouls}"/></td>
                        </tr>
                        </tbody>
                    </table>
                    --%>

                </div>
            </div>
        </div>
    </div>
</body>
</html>

