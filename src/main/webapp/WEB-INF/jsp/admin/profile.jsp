<%@ include file="../custom/header.jsp" %>

<div class="content">

    <h1 class="content__title"><fmt:message key="welcome" bundle="${bundle}" /> ${user.getName()}!</h1>

	<c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--${param.message eq 'changesSuccess' ? 'success' : 'danger'}">
                <p><fmt:message key="${param.message}" bundle="${bundle}" /></p>
            </div>
        </div>
    </c:if>
    
    <div class="content__section">
        <jsp:include page="../custom/profileInfo.jsp"/>
        
        <div class="row">
            <div class="row__item">
                <a class="button button--primary" href="${context}/profile/edit">
                	<fmt:message key="editProfile" bundle="${bundle}" />
                </a>
                <a class="button button--default" href="${context}/profile/delete">
                	<fmt:message key="deleteProfile" bundle="${bundle}" />
                </a>
            </div>
        </div>
    </div>

</div>

<div class="content">

    <h1 class="content__title"><fmt:message key="administrationPanel" bundle="${bundle}" /></h1>

    <div class="content__section">
        <form action="${context}/faculty/close" method="POST">
            <div class="row">
                <div class="row__item">
                    <select class="input input--select" name="facultyId">
                        <option value=""><fmt:message key="chooseFaculty" bundle="${bundle}" /></option>
                        <c:forEach var="f" items="${faculties}">
                            <option value="${f.getId()}">${f.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="row__item">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="button button--primary" type="submit" value="<fmt:message key="closeFaculty" bundle="${bundle}" />" />
                    <a class="button button--default" href="${context}/faculty/add">
                        <fmt:message key="addFaculty" bundle="${bundle}" />
                    </a>
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>