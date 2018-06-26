<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
    <jsp:attribute name="scripts">
        <script src="<c:url value="/assets/js/transfer.js"/>"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="modal fade" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-backdrop="static">
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
                            This is a static modal, backdrop click will not close it.
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button id="returnButton" type="button" data-dismiss="modal" class="btn btn-primary">Confirm</button>
                    </div>
                </div>
            </div>
        </div>
        <div>
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
        <div id="players">
        </div>
    </jsp:body>
</t:masterpage>
