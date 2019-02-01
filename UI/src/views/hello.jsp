<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:body>
    <h2><spring:message code="user.greeting" arguments="${user.username}"/></h2>
  </jsp:body>
</t:genericpage>
