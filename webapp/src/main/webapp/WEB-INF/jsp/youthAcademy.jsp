<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/home.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <div class="contain">
      <div class="translucent"></div>
      <div class="content">
        <div class="row">
          <div class="col-sm">
            <div class="player green">

              <jsp:include page="playerInfo.jsp"/>

              <div class="btn-toolbar justify-content-between" role="toolbar">
                <div class="btn-group mr-2" role="group">
                  <a href="<c:url value="/"/>">
                    <button class="btn btn-light"><spring:message code="hire"/></button>
                  </a>
                </div>
                <div class="btn-group mr-2" role="group">
                  <a href="<c:url value="/"/>">
                    <button class="btn btn-light"><spring:message code="retire"/></button>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <div class="col">

            <table class="table table-hover bg-white">
              <thead>
              <tr>
                <th scope="col"><spring:message code="name"/></th>
                <th scope="col"><spring:message code="player.salary"/></th>
                <th scope="col"><spring:message code="player.value"/></th>
                <th scope="col"><spring:message code="fitness"/></th>
                <th scope="col"><spring:message code="age"/></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="i" begin="1" end="10">
                <tr>
                  <th scope="row"><a href="<c:url value="/"/>">Perez</a></th>
                  <td>3,000</td>
                   
                  <td>400k</td>
                   
                  <td>93</td>
                   
                  <td>18</td>
                </tr>
              </c:forEach>
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
    </div>
  </jsp:body>
</t:masterpage>
