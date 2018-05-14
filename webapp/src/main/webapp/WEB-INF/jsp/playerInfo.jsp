<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h5><spring:message code="playerNameAndAge" arguments="${player.name},${player.age}"/></h5>

<hr>

<h6><spring:message code="attributes"/></h6>

<div class="container">
    <div class="row">
        <div class="col-sm">
            <p><spring:message code="player.finishing" arguments="${player.finish}"/></p>

        </div>
        <div class="col-sm">
            <p><spring:message code="player.defending" arguments="${player.defending}"/></p>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <p><spring:message code="player.passing" arguments="${player.passing}"/></p>
        </div>
        <div class="col-sm">
            <p><spring:message code = "player.goalKeeping" arguments="${player.goalKeeping}"/></p>
        </div>
    </div>
</div>

<hr>

<h6><spring:message code="finance"/> </h6>

<div class="container">
    <div class="row">
        <div class="col-sm">
            <p><spring:message code="player.salary" arguments="${contract.salary}"/></p>
        </div>
        <div class="col-sm">
            <p><spring:message code="player.value" arguments="${player.value}"/></p>
        </div>
    </div>
</div>

<hr>