<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.university.domain.faculty.Faculty"%>
<%@page import="com.university.domain.applicant.Applicant"%>
<%@page import="java.util.Map"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="viewAdminPage.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/main.css" />" />
</head>
<body>

	<jsp:include page="/WEB-INF/include/languageChooser.jsp" />
	<jsp:include page="/WEB-INF/include/header.jsp" />
	<c:choose>
	
		<c:when test="${applicants != null}">
		
			<form action="confirmRegistration" method="post" >
				<table style='margin: 0 auto;' class="adminTable">
					<thead>
						<tr>
						<th>Id</th>
						<th><fmt:message key="viewAdminPage.table.name" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.lastname" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.faculty" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.certificate" bundle="${lang}"/></th>
						<!-- replace with correct subjects -->
						<th><fmt:message key="viewAdminPage.table.subject1" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.subject2" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.subject3" bundle="${lang}"/></th>
						<th><fmt:message key="viewAdminPage.table.marked" bundle="${lang}"/></th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="applicant" items="${applicants}">
							<tr>
								<td><span>${applicant.key.applicantId}</span></td>
								<td><span>${applicant.key.applicantName}</span></td>
								<td><span>${applicant.key.applicantLastName}</span></td>
								<td><span>${applicant.value.facultyName}</span></td>
								<td><span>${applicant.key.applicantCertificate}</span></td>
			
								<c:forEach var="exam" items="${applicant.key.exams}">
									<td><span>${exam}</span></td>
								</c:forEach>
								<td><input type="checkbox" name="applicant" value="${applicant.key.applicantId}"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<p class="submit">
					<input type="submit" value="<fmt:message key="viewAdminPage.table.submit" bundle="${lang}"/>">
				</p>
			</form>
		
		</c:when>
		<c:otherwise>
			<h3 class="noApplicants"><fmt:message key="viewAdminPage.noContent" bundle="${lang}"/></h3>
		</c:otherwise>
	</c:choose>

</body>
</html>