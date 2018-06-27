<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card">
  <div class="card-header">
    <strong class="card-title"><c:out value="${player.name}"/></strong>
  </div>
  <div class="card-body">
    <div class="col-6 p-0 pr-2">
      <ul class="list-group list-group-flush">
        <li class="list-group-item">
          <spring:message code="age"/>
          <span class="pull-right"><c:out value="${player.age}"/></span>
        </li>
        <li class="list-group-item">
          <spring:message code="player.salary"/>
          <span class="pull-right"><spring:message code="currency" arguments="${player.salary}"/></span>
        </li>
        <li class="list-group-item">
          <spring:message code="player.value"/>
          <span class="pull-right"><spring:message code="currency" arguments="${player.value}"/></span>
        </li>
      </ul>
    </div>
    <div class="col-6 p-0">
      <%--<h6><spring:message code="attributes"/></h6>--%>
      <canvas id="radarChart"
              data-labels='[ "<spring:message code="player.finishing" />", "<spring:message code="player.defending" />",
              "<spring:message code="player.passing" />", "<spring:message code="player.goalKeeping" />" ]'
              data-values='[${player.finishing}, ${player.defending}, ${player.passing}, ${player.goalKeeping}]'></canvas>
    </div>
  </div>
</div>