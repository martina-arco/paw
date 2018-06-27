<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage active="passes">
  <jsp:attribute name="styles">
    <link rel="stylesheet" href="<c:url value="/assets/css/lib/datatable/dataTables.bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/transfer.css"/>"/>
  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script src="<c:url value="/assets/js/lib/data-table/datatables.min.js"/>"></script>
    <script src="<c:url value="/assets/js/plugins.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/dataTables.buttons.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/buttons.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/jszip.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/pdfmake.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/vfs_fonts.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/buttons.html5.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/buttons.print.min.js"/>"></script>
    <script src="<c:url value="/assets/js/lib/data-table/buttons.colVis.min.js"/>"></script>
    <script src="<c:url value="/assets/js/transfer.js"/>"></script>
  </jsp:attribute>
  <jsp:body>
    <div class="modal fade" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel"
         aria-hidden="true" data-backdrop="static">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticModalLabel">Transfer status</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p id="modalContent">
            </p>
          </div>
          <div class="modal-footer">
            <button id="returnButton" type="button" data-dismiss="modal" class="btn btn-primary">Confirm</button>
          </div>
        </div>
      </div>
    </div>

    <div class="animated fadeIn p-5">
      <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header">
            <form id="filterForm" method="post">
              <c:forEach items="${criterias}" var="criteria">
                ${criteria}
                <select name="${criteria}">
                  <option selected>Any</option>
                  <option value="${criteria};LESSTHAN">Less than</option>
                  <option value="${criteria};MORETHAN">Greater than</option>
                  <option value="${criteria};EQUALS">Equals</option>
                </select>
                <input name="${criteria}" type="number">
              </c:forEach>
              <input type="submit" value="Search">
            </form>
          </div>
          <div class="card-body">
            <table id="players-data-table" class="table table-striped table-bordered">
              <thead>
              <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
                <th>Salary</th>
              </tr>
              </thead>
              <tbody id="players" data-lang="<spring:message code="lang"/>">
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </div>
  </jsp:body>
</t:masterpage>
