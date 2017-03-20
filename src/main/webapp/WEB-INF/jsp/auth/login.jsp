<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="logIn" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.error}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${param.error}" bundle="${bundle}" />
           	</div>
        </div>
    </c:if>

    <div class="content__section">
        <form method="POST">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="login" placeholder="<fmt:message key="login" bundle="${bundle}" />" autocomplete="off" minlength="5" maxlength="20" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="password" name="password" placeholder="<fmt:message key="password" bundle="${bundle}" />" autocomplete="off" minlength="5" maxlength="20" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input type="checkbox" name="remember-me-param" id="remember-me-param"/>
                    <label for="remember-me-param"><fmt:message key="rememberMe" bundle="${bundle}" /></label>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" required/>
                    <input class="button button--primary" type="submit" value="<fmt:message key="logIn" bundle="${bundle}" />" />
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>