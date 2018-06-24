<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/css/formation.css"/>"/>
  </jsp:attribute>
  <jsp:body>
    <div class="contain">
      <div class="translucent"></div>
      <div class="content">
        <div class="row">
          <div class="col-sm">
            <div class="row">

              <div class="col">
                <h5><spring:message code="pressure"/></h5>
              </div>
              <div class="col">
                <select class="form-control">
                  <option><spring:message code="low"/></option>
                  <option><spring:message code="medium"/></option>
                  <option><spring:message code="high"/></option>
                </select>

              </div>

              <div class="col">
                <h5><spring:message code="penaltyTaker"/></h5>
              </div>
              <div class="col">
                <select class="form-control">
                  <option>Perez</option>
                </select>
                  <%--<c:forEach items="${formation.starters.keySet()}" var="player">
                      <option><c:out value="${player.name}"/></option>
                  </c:forEach>--%>
              </div>

            </div>

            <div class="row">

              <div class="col">
                <h5><spring:message code="captain"/></h5>
              </div>
              <div class="col">
                <select class="form-control">
                  <option>Perez</option>
                </select>
                  <%--<c:forEach items="${formation.starters.keySet()}" var="player">
                      <option><c:out value="${player.name}"/></option>
                  </c:forEach>--%>
              </div>

              <div class="col">
                <h5><spring:message code="freekickTaker"/></h5>
              </div>
              <div class="col">
                <select class="form-control">
                  <option>Perez</option>
                </select>
                  <%--<c:forEach items="${formation.starters.keySet()}" var="player">
                      <option><c:out value="${player.name}"/></option>
                  </c:forEach>--%>
              </div>

            </div>

            <div class="row">
              <div class="col">
                <div class="row">
                  <div class="col">
                    <h5><spring:message code="attitude"/></h5>
                  </div>
                  <div class="col">
                    <select class="form-control">
                      <option><spring:message code="balanced"/></option>
                      <option><spring:message code="ofensive"/></option>
                      <option><spring:message code="defensive"/></option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="col">
                <a href="<c:url value="/home"/>">
                  <button class="button"><spring:message code="save"/></button>
                </a>
              </div>

            </div>

            <div class="formation">
            </div>
          </div>

          <div class="col">

            <table class="table table-hover bg-white small table-sm">

              <thead>
              <tr>
                <th scope="col"><spring:message code="position"/></th>
                <th scope="col"><spring:message code="name"/></th>
                <th scope="col"><spring:message code="fitness"/></th>
                <th scope="col"><spring:message code="skillLevel"/></th>
                <th scope="col"><spring:message code="goalKeeping"/></th>
                <th scope="col"><spring:message code="finishing"/></th>
                <th scope="col"><spring:message code="defending"/></th>
                <th scope="col"><spring:message code="passing"/></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="i" begin="1" end="23">
                <tr>
                  <td>Forward</td>
                  <td>Perez</td>
                  <td>100</td>
                  <td>80</td>
                  <td>0</td>
                  <td>80</td>
                  <td>40</td>
                  <td>70</td>
                </tr>
              </c:forEach>
                <%--<c:forEach items="${players}" var="player">
                    <tr>
                        <td><c:out value="${formation.getPlayerPosition(player.id)}"/></td>
                        <td><c:out value="${player.name}"/></td>
                        <td><c:out value="${player.fitness}"/></td>
                        <td><c:out value="${player.skillLevel}"/></td>
                        <td><c:out value="${player.goalKeeping}"/></td>
                        <td><c:out value="${player.finish}"/></td>
                        <td><c:out value="${player.defending}"/></td>
                        <td><c:out value="${player.passing}"/></td>
                    </tr>
                </c:forEach>--%>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</t:masterpage>
