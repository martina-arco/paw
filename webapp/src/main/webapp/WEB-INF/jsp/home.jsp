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
    <c:url value="/home" var="homeUrl" />

    <jsp:include page="navBar.jsp" />

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">
            <div class="row">
                <div class="col-sm">

                    <div class="row">
                        <div class="col-3">
                            <h5><spring:message code="boardTrust"/> </h5>
                        </div>
                        <div class="col">
                            <p><c:out value="${team.boardTrust}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <h5><spring:message code="fanTrust"/> </h5>
                        </div>
                        <div class="col">
                            <p><c:out value="${team.fanTrust}"/></p>
                        </div>
                    </div>

                    <div class = "player green">

                        <jsp:include page="playerInfo.jsp"/>

                        <div class="btn-toolbar justify-content-between right" role="toolbar">
                            <%--<div class="btn-group mr-2" role="group">--%>
                                <%--<a href="<c:url value="/"/>"><button class="btn btn-light"><spring:message code="sell"/> </button></a>--%>
                            <%--</div>--%>
                            <%--<div class="btn-group mr-2" role="group">--%>
                                    <%--<a href="<c:url value="/"/>"><button class="btn btn-light"><spring:message code="contract"/> </button></a>--%>
                            <%--</div>--%>
                            <div class="btn-group mr-2" role="group">
                                <a href="<c:url value="/retirePlayer"/>"><button class="btn btn-light"><spring:message code="retire"/> </button></a>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col">
                            <a href="<c:url value="/formation"/>"><button class="button"><spring:message code="formation"/> </button></a>
                        </div>
                        <div class="col">
                            <a href="<c:url value="/match"/>"><button class="button"><spring:message code="playMatch"/> </button></a>
                        </div>
                    </div>

                </div>

                <div class="col">

                    <table class="table table-hover bg-white small table-sm">

                        <thead>
                        <tr>
                            <th scope="col"><spring:message code="name"/> </th>
                            <th scope="col"><spring:message code="salary"/> </th>
                            <th scope="col"><spring:message code="value"/> </th>
                            <th scope="col"><spring:message code="fitness"/> </th>
                            <th scope="col"><spring:message code="age"/></th>
                        </tr>
                        </thead>

                        <tbody>

                            <%--<c:forEach var = "i" begin = "1" end = "23">--%>
                                <%--<tr>--%>
                                    <%--<th scope="row"><a href="<c:url value="/home/${i}" />">Perez</a></th>--%>
                                    <%--<td>3,000</td> --%>
                                    <%--<td>400k</td> --%>
                                    <%--<td>93</td> --%>
                                    <%--<td>35</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>

                            <c:forEach items="${players}" var="player">
                                <tr>
                                    <th scope="row"><a href="<c:url value="/home/${i}" />"><c:out value="${player.name}"/></a></th>
                                    <td><c:out value="${player.salary}"/></td>
                                    <td><c:out value="${player.value}"/></td>
                                    <td><c:out value="${player.fitness}"/></td>
                                    <td><c:out value="${player.age}"/></td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>

                </div>

            </div>

        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
