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

    <div class="contain">
      <div class = "translucent"></div>
      <div class = "content">

        <h2>Register</h2>
        <div class = "container-fluid">

          <form:form modelAttribute="registerForm" action="${postPath}" method="post">

            <div class="form-group">
              <form:label path="username"><h8>Username</h8> </form:label>
              <form:input type="text" path="username" class="form-control" />
              <form:errors path="username" cssClass="formError" element="p"/>
            </div>

            <div class="form-group">
              <form:label path="password"><h8>Password</h8> </form:label>
              <form:input type="password" path="password" class="form-control" />
              <form:errors path="password" cssClass="formError" element="p"/>
            </div>

            <div class="form-group">
              <form:label path="repeatPassword"><h8>Repeat password</h8> </form:label>
              <form:input type="password" path="repeatPassword" class="form-control" />
              <form:errors path="repeatPassword" cssClass="formError" element="p"/>
            </div>

            <input type="submit" value="Register!" class="btn btn-light"/>

          </form:form>


          Already have an account?
          <a href="<c:url value="/login"/>"><input type="button" value="Login!" class="btn btn-light"/></a>

        </div>
      </div>
    </div>
    </div>
  </jsp:body>
</t:genericpage>
