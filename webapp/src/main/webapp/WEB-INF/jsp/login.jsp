<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:url value="/login" var="loginUrl" />
<form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="username">Username: </label>
        <input id="username" name="j_username" type="text"/>
    </div>
    <div>
        <label for="password">Password: </label>
        <input id="password" name="j_password" type="password"/>
    </div>
    <div>
        <label><input name="j_rememberme" type="checkbox"/> <spring:message code="remember_me"/></label>
    </div>
    <div>
        <input type="submit" value="Login!"/>
    </div>
</form>
Don't have an account?
<a href="<c:url value="/"/>"><input type="button" value="Create one!"/></a>
</body>
</html>