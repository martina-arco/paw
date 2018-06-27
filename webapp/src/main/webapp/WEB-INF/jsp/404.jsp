<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/register.css"/>"/>
  </jsp:attribute>

    <jsp:body>
      <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
          <div class="login-content" style="margin-top: 35vh;">
            <div class="login-form" style="text-align: center;">
              <h1><spring:message code="404"/></h1>
            </div>
          </div>
        </div>
      </div>
    </jsp:body>

</t:genericpage>