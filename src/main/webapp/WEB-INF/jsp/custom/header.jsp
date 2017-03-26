<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<fmt:setLocale value="${pageContext.request.locale eq 'ru_RU' ? 'ru_RU' : 'en_US'}" />
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

    <div class="header-wrapper">
        <div class="header">

            <div class="header__logo">
                <a class="header__logo__link" href="${contextPath}/">
                    <img class="header__logo__image" src="${contextPath}/assets/images/logo.svg" alt="BoardSystem" />
                </a>
            </div>

            <ul class="header__menu">
                <li class="header__menu__item">
                    <a class="header__menu__link" href="${contextPath}/">
                        <fmt:message key="home" bundle="${bundle}" />
                    </a>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name eq null and empty user}">
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${contextPath}/login">
                                <fmt:message key="logIn" bundle="${bundle}" />
                            </a>
                        </li>
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${contextPath}/signup">
                                <fmt:message key="signUp" bundle="${bundle}" />
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${contextPath}/profile">
                                <fmt:message key="profile" bundle="${bundle}" />
                            </a>
                        </li>
                        <li class="header__menu__item">
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <a class="header__menu__link" onclick="document.forms['logoutForm'].submit()">
                                <fmt:message key="logOut" bundle="${bundle}" />
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <%--<ul class="header__languages">--%>
                <%--<li class="header__languages__item">--%>
                    <%--<a class="header__languages__link ${lang eq 'en_US' ? 'header__languages__link--current' : ''}"--%>
                    <%--href="${lang eq 'en_US'--%>
                    <%--? '#'--%>
                    <%--: '?lang=en_US'}">--%>
                        <%--ENG--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header__languages__item">--%>
                    <%--<a class="header__languages__link ${lang eq 'ru_RU' ? 'header__languages__link--current' : ''}"--%>
                    <%--href="${lang eq 'ru_RU'--%>
                    <%--? '#'--%>
                    <%--: '?lang=ru_RU'}">--%>
                        <%--РУС--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</ul>--%>

        </div>
    </div>