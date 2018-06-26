<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage active="home">
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/home.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <div class="contain">
      <div class="translucent"></div>
      <div class="content">
        <div class="row">
          <div class="col-sm">
            <div class="row">
              <div class="col">
                <h4><c:out value="${team.name}"/></h4>
              </div>
              <div class="col right">
                <h4><c:out value="${date}"/></h4>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h5><spring:message code="boardTrust"/></h5>
              </div>
              <div class="col">
                <p><c:out value="${team.boardTrust}"/></p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h5><spring:message code="fanTrust"/></h5>
              </div>
              <div class="col">
                <p><c:out value="${team.fanTrust}"/></p>
              </div>
            </div>

            <div class="row">
              <div class="col">
                <p><spring:message code="nextMatch" arguments="${team1},${team2},${stadium}"/></p>
              </div>
            </div>

            <div class="player green">
              <jsp:include page="playerInfo.jsp"/>
            </div>

              <%--<div class="row">--%>
              <%--<div class="col">--%>
              <%--<a href="<c:url value="/formation"/>"><button class="button"><spring:message code="formation"/> </button></a>--%>
              <%--</div>--%>
              <%--<div class="col">--%>

            <a href="<c:url value="/match"/>">
              <button class="button"><spring:message code="playMatch"/></button>
            </a>

              <%--</div>--%>
              <%--</div>--%>
          </div>

          <div class="col">
            <table class="table table-hover bg-white small table-sm">
              <thead>
              <tr>
                <th scope="col"><spring:message code="name"/></th>
                <th scope="col"><spring:message code="salary"/></th>
                <th scope="col"><spring:message code="value"/></th>
                <th scope="col"><spring:message code="fitness"/></th>
                <th scope="col"><spring:message code="age"/></th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${players}" var="player">
                <tr>
                  <th scope="row"><a href="<c:url value="/${player.id}" />"><c:out
                      value="${player.name}"/></a></th>
                  <td><c:out value="${player.salary}"/></td>
                  <td><c:out value="${player.value}"/></td>
                  <td><c:out value="${player.fitness}"/></td>
                  <td><c:out value="${player.age}"/></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:masterpage>