<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head></head>
<body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" id = "nav">
            <a class="navbar-brand" href="#">Team name</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a href = "<c:url value="youthAcademy.jsp" var="youthAcademyUrl"/>" class="nav-link"><spring:message code="youthAcademy"/> </a>
                    </li>
                    <li class="nav-item active">
                        <a href = "<c:url value="stadium.jsp" var="stadiumUrl"/>" class="nav-link"><spring:message code="stadium"/></a>
                    </li>
                    <li class="nav-item active">
                        <a href = "<c:url value="league.jsp" var="leagueUrl"/>" class="nav-link"><spring:message code="league"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <spring:message code="finances"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a href = "<c:url value="finance.jsp" var="financesUrl"/>" class="dropdown-item ">See finance</a>
                            <a href = "<c:url value="bankLoan.jsp" var="bankLoanUrl"/>" class="dropdown-item">Bank loan</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
</body>
</html>

