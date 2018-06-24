<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/match.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <div class="contain">
      <div class="translucent"></div>
      <div class="content">
        <div class="row">
          <div class="col">

            <h5 class="center"><spring:message code="upcomingMatches"/></h5>

            <table class="table bg-white small center header-fixed">

              <thead>
              <tr>
                <th scope="col" class="width40"><spring:message code="stadium"/></th>
                <th scope="col" class="width60"><spring:message code="teams"/></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach items="${upcomingMatches}" var="match">
                <tr>
                  <td class="width40"><c:out value="${match.home.stadium.name}"/></td>
                  <td class="width60"><spring:message code="versus"
                                                      arguments="${match.home.name}, ${match.away.name}"/></td>
                   
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
          <div class="col">

            <h5 class="center"><spring:message code="positionTable"/></h5>

            <table class="table bg-white small center header-fixed">

              <thead>
              <tr>
                <th scope="col" class="width60"><spring:message code="team"/></th>
                <th scope="col" class="width40"><spring:message code="points"/></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach items="${teams}" var="team">
                <tr>
                  <td class="width60"><c:out value="${team.key}"/></td>
                  <td class="width40"><c:out value="${team.value}"/></td>
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
