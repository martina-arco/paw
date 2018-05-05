<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
    <c:url value="/login" var="loginUrl" />

    <div class="contain">
        <div class = "translucent"></div>
            <div class = "content">

                <h2>Login</h2>

                <div class = "container-fluid">

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
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>