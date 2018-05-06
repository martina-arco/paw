<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
    <c:url value="/stadium" var="stadiumUrl" />

    <jsp:include page="navBar.jsp"/>

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">

            <div class="row big-height">
                <div class="col">
                    <h3 class="indent"><spring:message code="currentSeats"/> </h3>
                </div>
            </div>

            <div class="row">
                <table class="table width35 table-borderless table-center">
                    <tbody>

                    <tr>
                        <td class="width60"><spring:message code="lowSeats"/></td> 
                        <td>40</td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="mediumSeats"/></td>
                        <td>40</td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="highSeats"/></td>
                        <td>40</td> 
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="row big-height">
                <div class="col">
                    <h3 class="indent"><spring:message code="expandStadium"/> </h3>
                </div>
            </div>

            <form:form method="post">
            <div class="row">
                <table class="table width35 table-borderless table-center">


                    <tbody>

                    <tr>
                        <td class="width60"><spring:message code="buyLowSeats"/></td> 
                        <td>
                            <select class="form-control">
                                <c:forEach var="i" begin = "1" end = "50">
                                    <option>${i}</option>
                                </c:forEach>
                            </select>
                        </td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="buyMediumSeats"/></td>
                        <td>
                            <select class="form-control">
                                <c:forEach var="i" begin = "1" end = "50">
                                    <option>${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="buyHighSeats"/></td>
                        <td>
                            <select class="form-control">
                                <c:forEach var="i" begin = "1" end = "50">
                                    <option>${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    </tbody>


                </table>
            </div>

            <div class="container right">
                <input type="submit" value="Save" class="btn btn-light">
            </div>

            </form:form>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
