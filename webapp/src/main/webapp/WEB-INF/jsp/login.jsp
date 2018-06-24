<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <c:url value="/login" var="loginUrl"/>

    <div class="contain">
      <div class="translucent"></div>
      <div class="content">

        <h2>Login</h2>

        <div class="container-fluid">
          <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
              <label for="username">Username </label>
              <input id="username" name="j_username" type="text" class="form-control"/>
            </div>

            <div class="form-group">
              <label for="password">Password </label>
              <input id="password" name="j_password" type="password" class="form-control"/>
            </div>

            <div class="form-group">
              <label><input name="j_rememberme" type="checkbox"/> <spring:message code="remember_me"/></label>
            </div>

            <input type="submit" value="Login!" class="btn btn-light"/>
          </form>

          Don't have an account?
          <a href="<c:url value="/"/>"><input type="button" value="Create one!" class="btn btn-light"/></a>

        </div>
      </div>
    </div>
  </jsp:body>
</t:genericpage>
