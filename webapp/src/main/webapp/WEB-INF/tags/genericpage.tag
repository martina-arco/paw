<%@tag description="Wrapper Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Football Manager</title>
    <meta name="description" content="Football Manager">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
    <link rel="apple-touch-icon" href="<c:url value="/assets/favicon.ico"/>">

    <link rel="stylesheet" href="<c:url value="/assets/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/themify-icons.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/flag-icon.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/cs-skin-elastic.css"/>">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <jsp:invoke fragment="styles"/>

</head>
<body>

<jsp:doBody/>

<script src="<c:url value="/assets/js/vendor/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/assets/js/popper.min.js"/>"></script>
<script src="<c:url value="/assets/js/plugins.js"/>"></script>
<script src="<c:url value="/assets/js/main.js"/>"></script>
<jsp:invoke fragment="scripts"/>

</body>
</html>