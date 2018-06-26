<%@tag description="Nav Bar Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="active"%>

<aside id="left-panel" class="left-panel">
  <nav class="navbar navbar-expand-sm navbar-default">

    <div class="navbar-header">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
      </button>
      <a class="navbar-brand" href="<c:url value="/"/>">Football Manager<%--<img src="images/logo.png" alt="Logo">--%></a>
      <a class="navbar-brand hidden" href="<c:url value="/"/>">Football Manager<%--<img src="images/logo2.png" alt="Logo">--%></a>
    </div>

    <div id="main-menu" class="main-menu collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="<c:if test="${active == 'home'}">active</c:if>">
          <a href="<c:url value="/"/>"> <i class="menu-icon ti-home"></i>Home </a>
        </li>
        <li class="<c:if test="${active == 'league'}">active</c:if>">
          <a href="<c:url value="/league"/>"> <i class="menu-icon ti-cup"></i>League </a>
        </li>
        <li class="<c:if test="${active == 'tactics'}">active</c:if>">
          <a href="<c:url value="/formation"/>"> <i class="menu-icon ti-blackboard"></i>Tactics </a>
        </li>
        <li class="<c:if test="${active == 'finance'}">active</c:if>">
          <a href="<c:url value="/finance"/>"> <i class="menu-icon ti-wallet"></i>Economy </a>
        </li>
        <li class="<c:if test="${active == 'passes'}">active</c:if>">
          <a href="<c:url value="/transfers"/>"> <i class="menu-icon ti-exchange-vertical"></i>Passes </a>
        </li>
        <li>
          <a href="<c:url value="/logout"/>"> <i class="menu-icon ti-power-off"></i>Logout </a>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </nav>
</aside><!-- /#left-panel -->

