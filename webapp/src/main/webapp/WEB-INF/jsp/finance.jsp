<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
  <jsp:body>
    <div class="contain">
      <div class = "translucent"></div>
      <div class = "content">

        <div class="row big-height">
          <div class="col">
            <h3 class="indent"><spring:message code="money"/></h3>
          </div>
          <div class="col">
            <h5><spring:message code="currency" arguments="${money}"/></h5>
          </div>
        </div>

        <div class="row big-height">
          <div class="col">
            <h3 class="indent"><spring:message code="income"/> </h3>
          </div>
        </div>

        <div class="row">
          <table class="table width35 table-borderless table-center">
            <tbody>

              <%--<tr>--%>
              <%--<td class="width60"><spring:message code="playersSold"/></td> --%>
              <%--<td><spring:message code="currency" arguments="${playersSold}"/></td> --%>
              <%--</tr>--%>
              <%--<tr>--%>
              <%--<td class="width60"><spring:message code="bankLoan"/></td>--%>
              <%--<td><spring:message code="currency" arguments="${bankLoanIncome}"/></td> --%>
              <%--</tr>--%>
            <tr>
              <td class="width60"><spring:message code="ticketsSold"/></td>
              <td><spring:message code="currency" arguments="${ticketsSold}"/></td> 
            </tr>

            </tbody>
          </table>
        </div>

        <div class="row big-height">
          <div class="col">
            <h3 class="indent"><spring:message code="expenses"/> </h3>
          </div>
        </div>

        <div class="row">
          <table class="table width35 table-borderless table-center">
            <tbody>
              <%--<tr>--%>
              <%--<td class="width60"><spring:message code="playersBought"/></td> --%>
              <%--<td><spring:message code="currency" arguments="${playersBought}"/></td> --%>
              <%--</tr>--%>
              <%--<tr>--%>
              <%--<td class="width60"><spring:message code="bankLoan"/></td>--%>
              <%--<td><spring:message code="currency" arguments="${bankLoanExpense}"/></td> --%>
              <%--</tr>--%>
              <%--<tr>--%>
              <%--<td class="width60"><spring:message code="stadiumExpansion"/></td>--%>
              <%--<td><spring:message code="currency" arguments="${stadiumExpansion}"/></td> --%>
              <%--</tr>--%>
            <tr>
              <td class="width60"><spring:message code="salaries"/></td>
              <td><spring:message code="currency" arguments="${salaries}"/></td> 
            </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>
  </jsp:body>
</t:masterpage>
