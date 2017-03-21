<%@ include file="../custom/header.jsp" %>

<div class="content">

    <h1 class="content__title">
    	<fmt:message key="facultyApplications" bundle="${bundle}" /> ${faculty.getName()}
   	</h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--${param.message eq 'changesSuccess' ? 'success' : 'danger'}">
                <p><fmt:message key="${param.message}" bundle="${bundle}" /></p>
            </div>
        </div>
    </c:if>

    <div class="content__section">
        <div class="alert alert--default">
            <fmt:message key="${totalNumber}" bundle="${bundle}" /> ${total}.
            <fmt:message key="${acceptedNumber}" bundle="${bundle}" /> ${accepted}.
        </div>
    </div>

    <div class="content__section">
        <table class="table table--stripped">
            <thead>
                <tr>
                    <th><fmt:message key="id" bundle="${bundle}" /></th>
                    <th><fmt:message key="name" bundle="${bundle}" /></th>
                    <th><fmt:message key="surname" bundle="${bundle}" /></th>
                    <th><fmt:message key="certificateNumber" bundle="${bundle}" /></th>
                    <th><fmt:message key="certificateGrade" bundle="${bundle}" /></th>
                    <th><fmt:message key="examGrades" bundle="${bundle}" /></th>
                    <th><fmt:message key="total" bundle="${bundle}" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${applications}">
                    <tr>
                        <td>${a.getId()}</td>
                        <td>${a.getUser().getName()}</td>
                        <td>${a.getUser().getSurname()}</td>
                        <td>${a.getCertificateNumber()}</td>
                        <td>${a.getCertificateGrade()}</td>
                        <td>
                            <c:forEach var="exam" items="${a.getExams()}">
                                ${exam.getKey().getName()} - ${exam.getValue()}
                                <br/>
                            </c:forEach>
                        </td>
                        <td>${a.getTotalGrade()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${applications.isEmpty()}">
        <div class="content__section">
            <div class="alert alert--default"><fmt:message key="noApplications" bundle="${bundle}" /></div>
        </div>
    </c:if>
    
    <div class="row">
            <div class="row__item">
                <a class="button button--primary" href="${context}/faculty/download?faculty=${faculty.getId()}">
                	<fmt:message key="downloadRegister" bundle="${bundle}" />
                </a>
                <a class="button button--default" href="${context}/profile">
                	<fmt:message key="backProfile" bundle="${bundle}" />
                </a>
            </div>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>