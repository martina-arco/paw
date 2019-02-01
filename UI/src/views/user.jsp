<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:body>
    <h2><spring:message code="user.greeting" arguments="${user.username}"/></h2>
    <h5><spring:message code="user.id" arguments="${user.id}"/></h5>
  </jsp:body>
</t:genericpage>
