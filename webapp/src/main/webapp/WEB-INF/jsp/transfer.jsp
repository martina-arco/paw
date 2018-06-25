<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
    <jsp:attribute name="scripts">
        <script src="<c:url value="/js/transfer.js"/>"></script>
    </jsp:attribute>
    <jsp:body>
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
