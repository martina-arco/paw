<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage active="league">
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/home.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/match.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <%--<div class="contain">
      <div class="translucent"></div>
      <div class="content">--%>
    <div id="right-panel" class="right-panel">
      <div class="animated fadeIn p-5">
        <div class="row">
          <div class="col-lg-6">
            <div class="card">
              <div class="card-header">
                <strong class="card-title"><spring:message code="upcomingMatches"/></strong>
              </div>
              <div class="card-body">
                <table class="table bg-white small center header-fixed">
                  <thead>
                  <tr>
                    <th scope="col" class="match-stadium"><spring:message code="stadium"/></th>
                    <th scope="col" class="match-home"><spring:message code="home"/></th>
                    <th scope="col" class="match-away"><spring:message code="away"/></th>
                  </tr>
                  </thead>

                  <tbody>
                  <c:forEach items="${upcomingMatches}" var="match">
                    <tr>
                      <td class="match-stadium"><c:out value="${match.home.stadium.name}"/></td>
                      <td class="match-home"><c:out value="${match.home.name}"/></td>
                      <td class="match-away"><c:out value="${match.away.name}"/></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="card">
              <div class="card-header">
                <strong class="card-title"><spring:message code="positionTable"/></strong>
              </div>
              <div class="card-body">
                <table class="table bg-white small center header-fixed">
                  <thead>
                  <tr>
                    <th scope="col" class="match-position"><spring:message code="position"/></th>
                    <th scope="col" class="match-team"><spring:message code="team"/></th>
                    <th scope="col" class="match-points"><spring:message code="points"/></th>
                  </tr>
                  </thead>

                  <tbody>
                  <c:forEach items="${teams}" var="team" varStatus="loop">
                    <tr>
                      <th scope="row" class="match-position"><c:out value="${loop.index}"/></th>
                      <td class="match-team"><c:out value="${team.key}"/></td>
                      <td class="match-points"><c:out value="${team.value}"/></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="col">

            <%--<h5 class="center"></h5>--%>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:masterpage>
