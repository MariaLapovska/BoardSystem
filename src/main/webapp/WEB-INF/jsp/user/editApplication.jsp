<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title">
        <fmt:message
        	key="${action eq 'add' ? 'createApplication' : 'editApplication'}"
        	bundle="${bundle}"/>
    </h1>

    <c:if test="${not empty error}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${error}" bundle="${bundle}" />
           	</div>
        </div>
    </c:if>

    <div class="content__section">
        <form:form method="POST" modelAttribute="applicationForm">
            <div class="row">
                <div class="row__item row__item--12">
                    <select class="input input--select" name="facultyId">
                        <option value="" ${empty application ? 'selected' : ''}>
                            <fmt:message key="chooseFaculty" bundle="${bundle}" />
                        </option>
                        <c:forEach var="f" items="${faculties}">
                            <option value="${f.getId()}" ${application.getFaculty().getId() eq f.getId() ? 'selected' : ''}>
                            	${f.getName()}
                           	</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <i><fmt:message key="certificatePattern" bundle="${bundle}"/></i>
                </div>
                <div class="row__item row__item--6">
                    <i><fmt:message key="gradePattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="certificateNumber" value="${application.getCertificateNumber()}"
                    placeholder="<fmt:message key="certificateNumber" bundle="${bundle}" />"  autocomplete="off" required minLength="10" maxlength="10" />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="certificateGrade" value="${application.getCertificateGrade()}"
                    placeholder="<fmt:message key="certificateGrade" bundle="${bundle}" />" autocomplete="off" required minLength="3" maxlength="3" />
                </div>
            </div>

            <div class="row">
                <div class="row__item row__item--12">
                    <i><fmt:message key="examsPattern" bundle="${bundle}"/></i>
                </div>
            </div>
            <c:choose>
                <c:when test="${not empty application}">
                    <c:forEach var="exam" items="${application.getExams().entrySet()}" varStatus="loop" >
                        <div class="row">
                            <div class="row__item row__item--6">
                                <select class="input input--select" name="exams[${loop.index}].subjectId">
                                    <option value="">
                                        <fmt:message key="chooseSubject" bundle="${bundle}" />
                                    </option>
                                    <c:forEach var="s" items="${subjects}">
                                        <option value="${s.getId()}" ${exam.getKey().getId() eq s.getId() ? 'selected' : ''}>${s.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="row__item row__item--6">
                                <input class="input" type="text" name="exams[${loop.index}].grade" value="${exam.getValue()}"
                                            placeholder="<fmt:message key="grade" bundle="${bundle}" />" autocomplete="off" required minLength="3" maxlength="3" />
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="1" end="3" varStatus="loop" >
                        <div class="row">
                            <div class="row__item row__item--6">
                                <select class="input input--select" name="exams[${loop.index}].subjectId">
                                    <option value="" selected>
                                        <fmt:message key="chooseSubject" bundle="${bundle}" />
                                    </option>
                                    <c:forEach var="s" items="${subjects}">
                                        <option value="${s.getId()}">${s.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="row__item row__item--6">
                                <input class="input" type="text" name="exams[${loop.index}].grade"
                                            placeholder="<fmt:message key="grade" bundle="${bundle}" />" autocomplete="off" required minLength="3" maxlength="3" />
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
           	
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" 
                    value="<fmt:message key="${action eq 'add' ? 'createApplication' : 'editApplication'}"
                   					 bundle="${bundle}"/>" 
    				/>
                    <a class="button button--default" href="${context}/profile"><fmt:message key="cancel" bundle="${bundle}" /></a>
                </div>
            </div>
        </form:form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>