<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%--<c:set var="yellowCard"><spring:message code="yellowCard"/></c:set>
<input id="yellowCard" type="hidden" value="${yellowCard}"/>

<c:set var="redCard"><spring:message code="redCard"/></c:set>
<input id="redCard" type="hidden" value="${redCard}"/>

<c:set var="substitute"><spring:message code="substitute"/></c:set>
<input id="substitute" type="hidden" value="${substitute}"/>

<c:set var="goal"><spring:message code="goalScored"/></c:set>
<input id="goalScored" type="hidden" value="${goal}"/>--%>

<t:genericpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/background.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/match.css"/>"/>
  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script src="<c:url value="/assets/js/match.js"/>"></script>
  </jsp:attribute>
  <jsp:body>
    <span hidden id="dataURL"><c:url value="/matchData"/></span>
    <span hidden id="matchEndURL"><c:url value="/matchEnd"/></span>
    <div class="p-5">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-body">
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

              <p hidden id="goalScored"><spring:message code="goalScored"/></p>
              <p hidden id="yellowCard"><spring:message code="yellowCard"/></p>
              <p hidden id="redCard"><spring:message code="redCard"/></p>

              <table class="table bg-white table-striped header-fixed">
                <thead class="green">
                <tr>
                  <th scope="col" class="width15"><spring:message code="stadium"/></th>
                  <th scope="col" class="width20"><spring:message code="home"/></th>
                  <th scope="col" colspan="2" class="width10"><spring:message code="score"/></th>
                  <th scope="col" class="width20"><spring:message code="away"/></th>
                  <th scope="col" class="width35"><spring:message code="events"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${matches}" var="match">
                  <tr id="${match.id}">
                    <td class="width15">${match.home.stadium.name}</td>
                    <td class="width20">${match.home.name}</td>
                     
                    <td class="width5" id="${match.id}homeScore">0</td>
                     
                    <td class="width5" id="${match.id}awayScore">0</td>
                    <td class="width20">${match.away.name}</td>
                     
                    <td class="width35" id="${match.id}event"></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:genericpage>

