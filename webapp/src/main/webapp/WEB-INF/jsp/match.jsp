<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%--<c:set var="yellowCard"><spring:message code="yellowCard"/></c:set>
<input id="yellowCard" type="hidden" value="${yellowCard}"/>

<c:set var="redCard"><spring:message code="redCard"/></c:set>
<input id="redCard" type="hidden" value="${redCard}"/>

<c:set var="substitute"><spring:message code="substitute"/></c:set>
<input id="substitute" type="hidden" value="${substitute}"/>

<c:set var="goal"><spring:message code="goalScored"/></c:set>
<input id="goalScored" type="hidden" value="${goal}"/>--%>

<t:masterpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/css/match.css"/>"/>
  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script src="<c:url value="/js/match.js"/>"></script>
  </jsp:attribute>
  <jsp:body>

    <div class="contain">
      <div class="translucent"></div>
      <div class="content">

        <div class="row">
          <div class="col text-md-right" id="time">
          </div>
          <div class="col-3">
            <div class="progress">
              <div class="progress-bar green" role="progressbar" aria-valuenow="0" aria-valuemin="0"
                   aria-valuemax="90"></div>
            </div>
          </div>
        </div>

        <table class="table bg-white table-striped header-fixed">
          <thead class="green">
          <tr>
            <th scope="col" class="width15"><spring:message code="stadium"/></th>
            <th scope="col" class="width15"><spring:message code="home"/></th>
            <th scope="col" colspan="2" class="width20"><spring:message code="score"/></th>
            <th scope="col" class="width15"><spring:message code="away"/></th>
            <th scope="col" class="width35"><spring:message code="events"/></th>
          </tr>
          </thead>

          <tbody>
          <c:forEach items="${matches}" var="match">
            <tr id="${match.id}">
              <td class="width15">${match.home.stadium.name}</td>
              <td class="width15">${match.home.name}</td>
               
              <td class="width10" id="${match.id}homeScore">0</td>
               
              <td class="width10" id="${match.id}awayScore">0</td>
              <td class="width15">${match.away.name}</td>
               
              <td class="width35" id="${match.id}event"></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </jsp:body>
</t:masterpage>

