<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
  <jsp:body>
    <div class="contain">
      <div class="translucent"></div>
      <div class="content">

        <div class="row big-height">
          <div class="col">
            <h3 class="indent"><spring:message code="currentSeats"/></h3>
          </div>
        </div>

        <div class="row">
          <table class="table width35 table-borderless table-center">
            <tbody>

            <tr>
              <td class="width60"><spring:message code="lowSeats"/></td>
               
              <td><c:out value="${stadium.lowClass}"/></td>
               
            </tr>
            <tr>
              <td class="width60"><spring:message code="mediumSeats"/></td>
              <td><c:out value="${stadium.mediumClass}"/></td>
               
            </tr>
            <tr>
              <td class="width60"><spring:message code="highSeats"/></td>
              <td><c:out value="${stadium.highClass}"/></td>
               
            </tr>

            </tbody>
          </table>
        </div>

          <%--<div class="row big-height">--%>
          <%--<div class="col">--%>
          <%--<h3 class="indent"><spring:message code="stadiumExpansion"/> </h3>--%>
          <%--</div>--%>
          <%--</div>--%>

          <%--<form:form method="post">--%>
          <%--<div class="row">--%>
          <%--<table class="table width35 table-borderless table-center">--%>


          <%--<tbody>--%>

          <%--<tr>--%>
          <%--<td class="width60"><spring:message code="buyLowSeats"/></td> --%>
          <%--<td>--%>
          <%--<select class="form-control">--%>
          <%--<c:forEach var="i" begin = "1" end = "50">--%>
          <%--<option>${i}</option>--%>
          <%--</c:forEach>--%>
          <%--</select>--%>
          <%--</td> --%>
          <%--</tr>--%>
          <%--<tr>--%>
          <%--<td class="width60"><spring:message code="buyMediumSeats"/></td>--%>
          <%--<td>--%>
          <%--<select class="form-control">--%>
          <%--<c:forEach var="i" begin = "1" end = "50">--%>
          <%--<option>${i}</option>--%>
          <%--</c:forEach>--%>
          <%--</select>--%>
          <%--</td>--%>
          <%--</tr>--%>
          <%--<tr>--%>
          <%--<td class="width60"><spring:message code="buyHighSeats"/></td>--%>
          <%--<td>--%>
          <%--<select class="form-control">--%>
          <%--<c:forEach var="i" begin = "1" end = "50">--%>
          <%--<option>${i}</option>--%>
          <%--</c:forEach>--%>
          <%--</select>--%>
          <%--</td>--%>
          <%--</tr>--%>

          <%--</tbody>--%>


          <%--</table>--%>
          <%--</div>--%>

          <%--<div class="container right">--%>
          <%--<input type="submit" value="Save" class="btn btn-light">--%>
          <%--</div>--%>

          <%--</form:form>--%>
      </div>
    </div>
  </jsp:body>
</t:masterpage>
