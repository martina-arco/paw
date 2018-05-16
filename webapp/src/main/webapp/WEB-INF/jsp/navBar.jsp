<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head></head>
<body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" id = "nav">
            <a class="navbar-brand" href = "<c:url value="/home"/>"><spring:message code="team"/> </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav mr-auto">
                    <%--<li class="nav-item ">--%>
                        <%--<a href = "<c:url value="/youthAcademy"/>" class="nav-link"><spring:message code="youthAcademy"/> </a>--%>
                    <%--</li>--%>
                    <li class="nav-item">
                        <a href = "<c:url value="/stadium"/>" class="nav-link"><spring:message code="stadium"/></a>
                    </li>
                    <li class="nav-item">
                        <a href = "<c:url value="/league"/>" class="nav-link"><spring:message code="league"/></a>
                    </li>
                    <li class="nav-item">
                        <a href = "<c:url value="/finance"/>" class="nav-link"><spring:message code="finance"/></a>
                    </li>
                </ul>
                <span class="nav-item navbar-nav">
                    <a href = "<c:url value="/logout"/>" class="nav-link"><spring:message code="logout"/></a>
                </span>
            </div>
        </nav>
</body>
</html>

