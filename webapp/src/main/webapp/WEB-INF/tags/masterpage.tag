<%@tag description="Master Page Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="active"%>

<t:genericpage>
    <jsp:attribute name="styles">
        <link rel="stylesheet" href="<c:url value="/assets/css/background.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/assets/css/navbar.css"/>"/>
        <jsp:invoke fragment="styles"/>
    </jsp:attribute>
  <jsp:attribute name="scripts">
        <jsp:invoke fragment="scripts"/>
    </jsp:attribute>
  <jsp:body>
    <t:navBar active="${active}"/>
    <div id="right-panel" class="right-panel">
      <jsp:doBody/>
    </div>
  </jsp:body>
</t:genericpage>