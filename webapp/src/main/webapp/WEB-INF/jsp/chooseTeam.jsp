<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
    <c:url value="/chooseTeam" var="chooseTeamUrl" />
    <c:url value="/processForm" var="postPath"/>

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">

            <h2>Choose team</h2>

            <form:form modelAttribute="chooseTeamForm" action="${postPath}" method="post">

                <div class="form-group">
                    <c:forEach items="${teamList}" var="team">
                        <form:radiobutton path="teamChosen" value="${team.id}"/>${team.name}
                    </c:forEach>
                </div>

                <div class="right">
                    <input type="submit" value="Start >>" class="btn btn-light"/>
                </div>

            </form:form>

        </div>
    </div>
</body>
</html>
