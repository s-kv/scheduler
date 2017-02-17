<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="cp1251">
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.js"/>"></script>    
    <script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <link rel="stylesheet" type="text/css"
          href="<spring:url value="/resources/css/bootstrap.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<spring:url value="/resources/css/bootstrap-responsive.css"/>" />
    <link type="text/css" rel="stylesheet"
          href="<spring:url value="/resources/css/classic.css"/>" />
    <link type="text/css" rel="stylesheet"
          href="<spring:url value="/resources/css/jquery.datetimepicker.css"/>" />
    
    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<spring:url value="/resources/ico/apple-touch-icon-144-precomposed.png"/>" />
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<spring:url value="/resources/ico/apple-touch-icon-114-precomposed.png"/>" />
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<spring:url value="/resources/ico/apple-touch-icon-72-precomposed.png"/>" />
    <link rel="apple-touch-icon-precomposed" href="<spring:url value="/resources/ico/apple-touch-icon-57-precomposed.png"/>" />
    <link rel="shortcut icon" href="<spring:url value="/resources/ico/favicon.png"/>" />
    
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
    <table id="mainTable">
        <tr>
            <td colspan="2"><tiles:insertAttribute name="header" /></td>
        </tr>
        <tr>
            <td valign="middle"><tiles:insertAttribute name="menu" /></td>
            <td valign="middle"><tiles:insertAttribute name="body" /></td>
        </tr>
        <tr>
            <td height="30" colspan="2"><tiles:insertAttribute name="footer" />
            </td>
        </tr>
    </table>
</body>
</html>