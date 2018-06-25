<%@tag description="Master Page Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>

<t:genericpage>
    <jsp:attribute name="styles">
        <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>"/>
        <jsp:invoke fragment="styles"/>
    </jsp:attribute>
  <jsp:attribute name="scripts">
        <jsp:invoke fragment="scripts"/>
    </jsp:attribute>
  <jsp:body>
    <jsp:include page="navBar.jsp"/>
    <jsp:doBody/>
  </jsp:body>
</t:genericpage>