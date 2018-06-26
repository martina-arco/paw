<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/register.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <c:url value="/create" var="postPath"/>

    <div class="sufee-login d-flex align-content-center flex-wrap">
      <div class="container">
        <div class="login-content">
          <div class="login-logo">
            <%--<img class="align-content" src="images/logo.png" alt="">--%>
            <span class="align-content">Football Manager</span>
          </div>
          <div class="login-form">
            <form:form modelAttribute="registerForm" action="${postPath}" method="post">
              <div class="form-group">
                <form:label path="username">Username </form:label>
                <form:input type="text" path="username" class="form-control" />
                <form:errors path="username" cssClass="formError" element="p"/>
              </div>

              <div class="form-group">
                <form:label path="password">Password </form:label>
                <form:input type="password" path="password" class="form-control" />
                <form:errors path="password" cssClass="formError" element="p"/>
              </div>

              <div class="form-group">
                <form:label path="repeatPassword">Repeat password </form:label>
                <form:input type="password" path="repeatPassword" class="form-control" />
                <form:errors path="repeatPassword" cssClass="formError" element="p"/>
              </div>
              <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Register</button>
              <div class="register-link m-t-15 text-center">
                <p>Already have account ? <a href="<c:url value="/login"/>"> Sign in</a></p>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:genericpage>
