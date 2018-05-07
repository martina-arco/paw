<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
    <c:url value="/finance" var="financeUrl" />

    <jsp:include page="navBar.jsp"/>

    <div class="contain">
        <div class = "translucent"></div>
        <div class = "content">

            <div class="row big-height">
                <div class="col">
                    <h3 class="indent"><spring:message code="money"/></h3>
                </div>
                <div class="col">
                    <h5><c:out value="${money}"/></h5>
                </div>
            </div>

            <div class="row big-height">
                <div class="col">
                    <h3 class="indent"><spring:message code="income"/> </h3>
                </div>
            </div>

            <div class="row">
                <table class="table width35 table-borderless table-center">
                    <tbody>

                    <tr>
                        <td class="width60"><spring:message code="playersSold"/></td> 
                        <td><spring:message code="currency" arguments="${playersSold}"/></td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="bankLoan"/></td>
                        <td><spring:message code="currency" arguments="${bankLoanIncome}"/></td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="ticketsSold"/></td>
                        <td><spring:message code="currency" arguments="${ticketsSold}"/></td> 
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="row big-height">
                <div class="col">
                    <h3 class="indent"><spring:message code="expenses"/> </h3>
                </div>
            </div>

            <div class="row">
                <table class="table width35 table-borderless table-center">
                    <tbody>

                    <tr>
                        <td class="width60"><spring:message code="playersBought"/></td> 
                        <td><spring:message code="currency" arguments="${playersBought}"/></td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="bankLoan"/></td>
                        <td><spring:message code="currency" arguments="${bankLoanExpense}"/></td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="stadiumExpansion"/></td>
                        <td><spring:message code="currency" arguments="${stadiumExpansion}"/></td> 
                    </tr>
                    <tr>
                        <td class="width60"><spring:message code="salaries"/></td>
                        <td><spring:message code="currency" arguments="${salaries}"/></td> 
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="container right">
                <a href="<c:url value="/"/>"><button class="btn btn-light"><spring:message code="askBankLoan"/> </button></a>
            </div>
        </div>
    </div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>
