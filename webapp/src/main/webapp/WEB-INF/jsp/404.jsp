<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage>

    <jsp:body>
        <c:url value="/404" var="404Url"/>
        <h1><spring:message code="404"/></h1>
    </jsp:body>

</t:genericpage>