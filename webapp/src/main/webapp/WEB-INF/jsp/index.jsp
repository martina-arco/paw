<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
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

                </div>

                Already have an account?
                <a href="<c:url value="/login"/>"><input type="button" value="Login!" class="btn btn-light"/></a>

            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>