<%@tag description="Nav Bar Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="active"%>

<%--<nav class="navbar navbar-expand-lg navbar-light bg-light" id="nav">
  <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="team"/> </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav mr-auto">
      &lt;%&ndash;<li class="nav-item ">&ndash;%&gt;
      &lt;%&ndash;<a href = "<c:url value="/youthAcademy"/>" class="nav-link"><spring:message code="youthAcademy"/> </a>&ndash;%&gt;
      &lt;%&ndash;</li>&ndash;%&gt;
      <li class="nav-item">
        <a href="<c:url value="/stadium"/>" class="nav-link"><spring:message code="stadium"/></a>
      </li>
      <li class="nav-item">
        <a href="<c:url value="/league"/>" class="nav-link"><spring:message code="league"/></a>
      </li>
      <li class="nav-item">
        <a href="<c:url value="/finance"/>" class="nav-link"><spring:message code="finance"/></a>
      </li>
    </ul>
    <span class="nav-item navbar-nav">
      <a href="<c:url value="/logout"/>" class="nav-link"><spring:message code="logout"/></a>
    </span>
  </div>
</nav>--%>
<aside id="left-panel" class="left-panel">
  <nav class="navbar navbar-expand-sm navbar-default">

    <div class="navbar-header">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
      </button>
      <a class="navbar-brand" href="#">Football Manager<%--<img src="images/logo.png" alt="Logo">--%></a>
      <a class="navbar-brand hidden" href="#">Football Manager<%--<img src="images/logo2.png" alt="Logo">--%></a>
    </div>

    <div id="main-menu" class="main-menu collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="<c:if test="${active == '' || active == 'home'}">active</c:if>">
          <a href="<c:url value="/"/>"> <i class="menu-icon ti-home"></i>Home </a>
        </li>
        <li class="<c:if test="${active == 'league'}">active</c:if>">
          <a href="<c:url value="/league"/>"> <i class="menu-icon ti-cup"></i>League </a>
        </li>
        <li class="<c:if test="${active == 'tactics'}">active</c:if>">
          <a href="<c:url value="/"/>"> <i class="menu-icon ti-blackboard"></i>Tactics </a>
        </li>
        <li class="<c:if test="${active == 'finance'}">active</c:if>">
          <a href="<c:url value="/finance"/>"> <i class="menu-icon ti-wallet"></i>Economy </a>
        </li>
        <li class="<c:if test="${active == 'passes'}">active</c:if>">
          <a href="<c:url value="/"/>"> <i class="menu-icon ti-exchange-vertical"></i>Passes </a>
        </li>
        <li>
          <a href="<c:url value="/logout"/>"> <i class="menu-icon ti-power-off"></i>Logout </a>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </nav>
</aside><!-- /#left-panel -->

