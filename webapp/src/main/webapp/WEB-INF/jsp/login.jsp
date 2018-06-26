<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/register.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <c:url value="/login" var="loginUrl"/>

    <%--<div class="contain">
      <div class="translucent"></div>
      <div class="content">

        <h2>Login</h2>

        <div class="container-fluid">
          <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">


            <div class="form-group">
              <label><input name="j_rememberme" type="checkbox"/> <spring:message code="remember_me"/></label>
            </div>

            <input type="submit" value="Login!" class="btn btn-light"/>
          </form>

          Don't have an account?
          <a href="<c:url value="/register"/>"><input type="button" value="Create one!" class="btn btn-light"/></a>

        </div>
      </div>
    </div>--%>

    <div class="sufee-login d-flex align-content-center flex-wrap">
      <div class="container">
        <div class="login-content">
          <div class="login-logo">
              <%--<img class="align-content" src="images/logo.png" alt="Holis">--%>
            <span class="align-content">Football Manager</span>
          </div>
          <div class="login-form">
            <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
              <div class="form-group">
                <label for="username">Username </label>
                <input id="username" name="j_username" type="text" class="form-control" placeholder="Username"/>
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="j_password" type="password" class="form-control" placeholder="Password"/>
              </div>
              <div class="checkbox">
                <label>
                  <input name="j_rememberme" type="checkbox"/> <spring:message code="remember_me"/>
                </label>
              </div>
              <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Sign in</button>

              <div class="register-link m-t-15 text-center">
                <p>Don't have account ? <a href="<c:url value="/register"/>"> Sign Up Here</a></p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:genericpage>
