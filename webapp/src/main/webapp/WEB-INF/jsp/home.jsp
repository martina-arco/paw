<%--
  Created by IntelliJ IDEA.
  User: martina
  Date: 02/05/2018
  Time: 14:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<div>
    <c:url value="/home" var="homeUrl" />

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Team name</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a href = "<c:url value="youthAcademy.jsp" var="youthAcademyUrl"/>" class="nav-link">Youth Academy</a>
                </li>
                <li class="nav-item active">
                    <a href = "<c:url value="stadium.jsp" var="stadiumUrl"/>" class="nav-link">Stadium</a>
                </li>
                <li class="nav-item active">
                    <a href = "<c:url value="league.jsp" var="leagueUrl"/>" class="nav-link">League</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Finances
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a href = "<c:url value="finance.jsp" var="financesUrl"/>" class="dropdown-item">See finance</a>
                        <a href = "<c:url value="bankLoan.jsp" var="bankLoanUrl"/>" class="dropdown-item">Bank loan</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>


    <div class="contain">

        <div class="row">

            <div class="col-sm">

                <div class="trust">
                    <h5>Board trust: </h5>
                    <h5>Fun trust: </h5>
                </div>

                <div class = "player">
                    <h4>Perez (Age: 28)</h4>

                    <hr>

                    <h5>Attributes</h5>

                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                <h6>Finishing</h6>
                            </div>
                            <div class="col-sm">
                                <h6>Defending</h6>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <h6>Passing</h6>
                            </div>
                            <div class="col-sm">
                                <h6>Potential</h6>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <h6>Goalkeeping</h6>
                            </div>
                            <div class="col-sm">
                                <h6>Fitness</h6>
                            </div>
                        </div>
                    </div>

                    <hr>

                    <h5>Finance</h5>

                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                <h6>Salary</h6>
                            </div>
                            <div class="col-sm">
                                <h6>Value</h6>
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="btn-toolbar justify-content-between" role="toolbar">
                        <div class="btn-group mr-2" role="group">
                            <a href="<c:url value="/"/>"><button class="btn btn-light">Sell</button></a>
                        </div>
                        <div class="btn-group mr-2" role="group">
                                <a href="<c:url value="/"/>"><button class="btn btn-light">Contract</button></a>
                        </div>
                        <div class="btn-group mr-2" role="group">
                                <a href="<c:url value="/"/>"><button class="btn btn-light">Retire</button></a>
                        </div>
                    </div>

                </div>

                <a href="<c:url value="/"/>"><button class="btn btn-light">Formation</button></a>

            </div>

            <div class="col-sm">

                <table class="table table-hover ">

                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Salary</th>
                        <th scope="col">Value</th>
                        <th scope="col">Fitness</th>
                        <th scope="col">Age</th>
                    </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <th scope="row"><a href="<c:url value="/"/>">Perez</a></th>
                            <td>3,000</td> 
                            <td>400k</td> 
                            <td>93</td> 
                            <td>35</td>
                        </tr>
                        <tr>
                            <th scope="row">Perez</th>
                            <td>3,000</td> 
                            <td>400k</td> 
                            <td>93</td> 
                            <td>35</td>
                        </tr>
                        <tr>
                            <th scope="row">Perez</th>
                            <td>3,000</td> 
                            <td>400k</td> 
                            <td>93</td> 
                            <td>35</td>
                        </tr>
                    </tbody>
                    <%--<c:forEach items="${playerName}" var="player">--%>
                        <%--<tr>--%>
                            <%--<td>${player.name}</td>--%>
                            <%--<td>${player.salary}</td>--%>
                            <%--<td>${player.value}</td>--%>
                            <%--<td>${player.fitness}</td>--%>
                            <%--<td>${player.age}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>

                </table>

            </div>

        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
