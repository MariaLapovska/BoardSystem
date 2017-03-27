<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="signUp" bundle="${bundle}" /></h1>

    <c:if test="${not empty error}">
        <div class="content__section">
            <div class="alert alert--danger"><fmt:message key="${error}" bundle="${bundle}" /></div>
        </div>
    </c:if>

    <div class="content__section">
        <form:form method="POST" modelAttribute="signUpForm">
            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="loginPattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="login" placeholder="<fmt:message key="login" bundle="${bundle}"/>" autocomplete="off" required maxlength="20" minLength="5" />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="namePattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="name" placeholder="<fmt:message key="name" bundle="${bundle}"/>" required maxlength="25" minLength="3" />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="surname" placeholder="<fmt:message key="surname" bundle="${bundle}"/>" required maxlength="25" minLength="3" />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="passwordPattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="password" placeholder="<fmt:message key="password" bundle="${bundle}"/>" required maxlength="20" minLength="5" />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                     <input class="input" type="password" name="confirmPassword" placeholder="<fmt:message key="repPassword" bundle="${bundle}"/>" required maxlength="20" minLength="5" />
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