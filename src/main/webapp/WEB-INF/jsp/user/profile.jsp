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

    <c:if test="${empty application}">
        <div class="content__section">
            <div class="alert alert--warning">
                <p><fmt:message key="noApplication" bundle="${bundle}" /></p>
                <p>
                    <a class="button button--primary" href="${context}/application/add">
                    	<fmt:message key="createApplication" bundle="${bundle}" />
                    </a>
                </p>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty application && status eq 'NEW'}">
        <div class="content__section">
            <div class="alert alert--success">
                <p><fmt:message key="haveApplication" bundle="${bundle}" /></p>
                <p>
                    <a class="button button--primary" href="${context}/application/edit">
                    	<fmt:message key="editApplication" bundle="${bundle}" />
                    </a>
                    <a class="button button--default" href="${context}/application/delete">
                    	<fmt:message key="deleteApplication" bundle="${bundle}" />
                    </a>
                </p>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty application && status eq 'ACCEPTED'}">
        <div class="content__section">
	        <div class="alert alert--success">
                <fmt:message key="facultyPassed" bundle="${bundle}" /> ${application.getFaculty().getName()}.
            </div>
        </div>
    </c:if>

    <c:if test="${not empty application && status eq 'DECLINED'}">
        <div class="content__section">
            <div class="alert alert--danger">
                <p><fmt:message key="facultyNotPassed" bundle="${bundle}" /> ${application.getFaculty().getName()}.
                <fmt:message key="applyAnother" bundle="${bundle}" /></p>
                <p>
                    <a class="button button--primary" href="${context}/application/add">
                        <fmt:message key="createApplication" bundle="${bundle}" />
                    </a>
                </p>
            </div>
        </div>
    </c:if>
    
    <div class="content__section">
        <jsp:include page="../custom/profileInfo.jsp"/>
        
        <c:if test="${empty application || status ne 'ACCEPTED'}">
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
        </c:if>
    </div>
    
    <div class="content__section">
    	
    	<h1 class="table__title"><fmt:message key="availableFaculties" bundle="${bundle}" /></h1>
    	
        <table class="table table--stripped">
            <thead>
                <tr>
                    <th><fmt:message key="faculty" bundle="${bundle}" /></th>
                    <th><fmt:message key="exams" bundle="${bundle}" /></th>
                    <th><fmt:message key="plan" bundle="${bundle}" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="f" items="${faculties}">
                    <tr>
                        <td>${f.getName()}</td>
                        <td>
                            <c:forEach var="s" items="${f.getSubjects()}">
                                ${s.getName()}
                                <br/>
                            </c:forEach>
                        </td>
                       	<td>${f.getRecruitmentPlan()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${faculties.isEmpty()}">
        <div class="content__section">
            <div class="alert alert--default"><fmt:message key="noFaculties" bundle="${bundle}" /></div>
        </div>
    </c:if>

</div>

<%@ include file="../custom/footer.jsp" %>