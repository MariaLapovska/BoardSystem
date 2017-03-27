<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="editProfile" bundle="${bundle}" /></h1>

    <c:if test="${not empty error}">
        <div class="content__section">
            <div class="alert alert--danger"><fmt:message key="${error}" bundle="${bundle}" /></div>
        </div>
    </c:if>

    <div class="content__section">
        <form:form method="POST" modelAttribute="editUserForm">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" placeholder="<fmt:message key="login" bundle="${bundle}" />" value="${user.getLogin()}" autocomplete="off" readonly="true" />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="namePattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="name" placeholder="<fmt:message key="name" bundle="${bundle}" />" value="${user.getName()}" minLength="3" maxlength="25" required />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="surname" placeholder="<fmt:message key="surname" bundle="${bundle}" />" value="${user.getSurname()}" minLength="3" maxlength="25" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="password" name="oldPassword" placeholder="<fmt:message key="oldPassword" bundle="${bundle}" />" autocomplete="off" minLength="5" maxlength="20" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="passwordPattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="newPassword" placeholder="<fmt:message key="newPassword" bundle="${bundle}" />" autocomplete="off" minLength="5" maxlength="20" required />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="confirmPassword" placeholder="<fmt:message key="repNewPassword" bundle="${bundle}" />" autocomplete="off" minLength="5" maxlength="20" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="saveChanges" bundle="${bundle}" />" />
                    <a class="button button--default" href="${context}/profile"><fmt:message key="cancel" bundle="${bundle}" /></a>
                </div>
            </div>
        </form:form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>