<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%--<c:choose>--%>
	<%--<c:when test="${empty lang}">--%>
		<%--<c:set var="lang" value="${empty param.lang ? pageContext.request.locale : param.lang}" />--%>
	<%--</c:when>--%>
	<%--<c:otherwise>--%>
		<%--<c:set var="lang" value="${empty param.lang ? lang : param.lang}" />--%>
	<%--</c:otherwise>--%>
<%--</c:choose>--%>

<fmt:setLocale value="${pageContext.request.locale}" />
<fmt:setBundle basename="i18n/messages" var="bundle" />

<!doctype html>
<html>
<head>
	<title>
		<fmt:message key="pageTitle" bundle="${bundle}" />
	</title>

	<meta charset="utf-8" />
	<meta name="x-ua-compatible" content="ie=edge" />
	<meta name="robots" content="index, follow" />

	<link rel="icon" href="${contextPath}/assets/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${contextPath}/assets/images/favicon.ico" type="image/x-icon" />

	<link rel="stylesheet" href="${contextPath}/assets/styles/custom.css" />

</head>
<body>

<div class="content content--form">
	<h1 class="content__title">
		<fmt:message key="${errorStatus}" bundle="${bundle}" var="customMessage" />
		<fmt:message key="error" bundle="${bundle}" var="defaultMessage" />
		${empty customMessage ? defaultMessage : customMessage}
	</h1>
	
	<div class="content__section">
		<a class="button button--primary" href="../">
			<fmt:message key="backHome" bundle="${bundle}" />
		</a>
	</div>
</div>

</body>
</html>