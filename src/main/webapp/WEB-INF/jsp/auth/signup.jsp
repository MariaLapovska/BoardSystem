<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="signUp" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger"><fmt:message key="${param.message}" bundle="${bundle}" /></div>
        </div>
    </c:if>

    <div class="content__section">
        <form:form method="POST" modelAttribute="userForm">
            <div class="row">
                <div class="row__item row__item--12">
                    <spring:bind path="login">
                        <fmt:message key="login" bundle="${bundle}" var="login"/>
                        <form:input class="input" type="text" path="login" placeholder="${login}" autocomplete="off"></form:input>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <spring:bind path="name">
                        <fmt:message key="name" bundle="${bundle}" var="name"/>
                        <form:input class="input" type="text" path="name" placeholder="${name}"></form:input>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <spring:bind path="surname">
                        <fmt:message key="surname" bundle="${bundle}" var="surname"/>
                        <form:input class="input" type="text" path="surname" placeholder="${surname}"></form:input>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <spring:bind path="password">
                        <fmt:message key="password" bundle="${bundle}" var="password"/>
                        <form:input class="input" type="password" path="password" placeholder="${password}"></form:input>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <spring:bind path="confirmPassword">
                        <fmt:message key="repPassword" bundle="${bundle}" var="repPassword"/>
                        <form:input class="input" type="password" path="confirmPassword" placeholder="${repPassword}"></form:input>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="signUp" bundle="${bundle}" />" />
                </div>
            </div>
        </form:form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>