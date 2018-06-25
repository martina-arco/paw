<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
    <jsp:attribute name="styles">
        <link rel="stylesheet" href="<c:url value="/css/formation.css"/>"/>
    </jsp:attribute>
    <jsp:body>
        <c:url value="/formation" var="formationUrl" />
        <c:url value="/processFormationForm" var="postPath" />

        <div class="contain">
            <div class = "translucent"></div>
            <div class = "content">
                <div class="row">
                    <div class="col">
                        <form:form modelAttribute="formationForm" action="${postPath}" method="post">
                                    <%-- Delanteros --%>
                                    <div class="row">

                                            <form:select path="leftForward">
                                                <form:option value="${lf.id}"><c:out value="${lf.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != lf && player.getPosition() == 3}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>


                                            <form:select path="rightForward">
                                                <form:option value="${rf.id}"><c:out value="${rf.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != rf && player.getPosition() == 3}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                    </div>

                                    <%-- Volantes --%>
                                    <div class="row">

                                            <form:select path="leftMid">
                                                <form:option value="${lm.id}"><c:out value="${lm.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != lm && player.getPosition() == 2}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                            <form:select path="leftCenterMid">
                                                <form:option value="${lcm.id}"><c:out value="${lcm.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != lcm && player.getPosition() == 2}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                            <form:select path="rightCenterMid">
                                                <form:option value="${rcm.id}"><c:out value="${rcm.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != rcm && player.getPosition() == 2}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                            <form:select path="rightMid">
                                                <form:option value="${rm.id}"><c:out value="${rm.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != rm && player.getPosition() == 2}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                    </div>

                                    <%-- Defensores --%>
                                    <div class="row">

                                            <form:select path="leftBack">
                                                <form:option value="${lb.id}"><c:out value="${lb.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != lb && player.getPosition() == 1}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                            <form:select path="leftCenterBack">
                                                <form:option value="${lcb.id}"><c:out value="${lcb.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != lcb && player.getPosition() == 1}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                            <form:select path="rightCenterBack">
                                                <form:option value="${rcb.id}"><c:out value="${rcb.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != rcb && player.getPosition() == 1}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>


                                            <form:select path="rightBack">
                                                <form:option value="${rb.id}"><c:out value="${rb.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != rb && player.getPosition() == 1}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>

                                    </div>

                                    <%-- Arquero --%>
                                    <div class="row">
                                        <form:select path="goalkeeper">
                                            <form:option value="${gk.id}"><c:out value="${gk.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != lf && player.getPosition() == 0}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                    <%-- Suplentes --%>
                                    <div class="row">
                                        <form:select path="substitute1">
                                            <form:option value="${sub1.id}"><c:out value="${sub1.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub1}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute2">
                                            <form:option value="${sub2.id}"><c:out value="${sub2.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub2}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute3">
                                            <form:option value="${sub3.id}"><c:out value="${sub3.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub3}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute4">
                                            <form:option value="${sub4.id}"><c:out value="${sub4.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub4}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute5">
                                            <form:option value="${sub5.id}"><c:out value="${sub5.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub5}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute6">
                                            <form:option value="${sub6.id}"><c:out value="${sub6.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub6}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                        <form:select path="substitute7">
                                            <form:option value="${sub7.id}"><c:out value="${sub7.name}"/></form:option>
                                            <c:forEach items="${players}" var="player">
                                                <c:if test="${player != sub7}">
                                                    <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>

                                    </div>

                                    <div class="row">
                                        <div class="col">
                                            <h6>Captain</h6>
                                            <form:select path="captain">
                                                <form:option value="${captain.id}"><c:out value="${captain.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != captain}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>
                                        </div>

                                        <div class="col">
                                            <h6>Freekick Taker</h6>
                                            <form:select path="freeKickTaker">
                                                <form:option value="${fk.id}"><c:out value="${fk.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != fk}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>
                                        </div>

                                        <div class="col">
                                            <h6>Penalty Taker</h6>
                                            <form:select path="penaltyTaker">
                                                <form:option value="${pen.id}"><c:out value="${pen.name}"/></form:option>
                                                <c:forEach items="${players}" var="player">
                                                    <c:if test="${player != pen}">
                                                        <form:option value="${player.id}"><c:out value="${player.name}"/></form:option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col">
                                            <h6>Pressure</h6>
                                            <form:select path="pressure">
                                                <form:option value="1">Low</form:option>
                                                <form:option value="2">Medium</form:option>
                                                <form:option value="3">High</form:option>
                                            </form:select>
                                        </div>
                                        <div class="col">
                                            <h6>Attitude</h6>
                                            <form:select path="attitude">
                                                <form:option value="1">Defensive</form:option>
                                                <form:option value="2">Balanced</form:option>
                                                <form:option value="3">Offensive</form:option>
                                            </form:select>
                                        </div>
                                        <div class="col">
                                            <h6>Formation</h6>
                                            <form:select path="formation">
                                                <form:option value="442">442</form:option>
                                            </form:select>
                                        </div>
                                    </div>
                            <input type="submit" value="Save" class="btn btn-light"/>
                        </form:form>
                    </div>

                    <div class="col">
                        <table class="table table-hover bg-white small table-sm">
                            <thead>
                            <tr>
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
                                <c:forEach items="${players}" var="player">
                                    <tr>
                                        <td><c:out value="${player.name}"/></td>
                                        <td><c:out value="${player.fitness}"/></td>
                                        <td><c:out value="${player.skillLevel}"/></td>
                                        <td><c:out value="${player.goalKeeping}"/></td>
                                        <td><c:out value="${player.finishing}"/></td>
                                        <td><c:out value="${player.defending}"/></td>
                                        <td><c:out value="${player.passing}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:masterpage>
