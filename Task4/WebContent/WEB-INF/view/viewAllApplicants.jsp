<%@page import="com.university.domain.faculty.Faculty"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.university.domain.applicant.Applicant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.university.repository.jdbc.DaoFactory.ConnTypes"%>
<%@page import="com.university.repository.jdbc.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="viewAllApplicants.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main.css" />" />
</head>
<body>

<jsp:include page="/WEB-INF/include/languageChooser.jsp" />
<jsp:include page="/WEB-INF/include/header.jsp" />

<h1 style="text-align: center;"><fmt:message key="viewAllApplicants.title" bundle="${lang}"/></h1>
	
<c:choose>
	<c:when test="${applicants != null}">
		<table class="applicantTable">
			<thead>
					<tr>
						<th>№</th>
						<th><fmt:message key="viewAllApplicants.table.name" bundle="${lang}"/></th>
						<th><fmt:message key="viewAllApplicants.table.lastname" bundle="${lang}"/></th>
						<th><fmt:message key="viewAllApplicants.table.faculty" bundle="${lang}"/></th>
						<th><fmt:message key="viewAllApplicants.table.certificate" bundle="${lang}"/></th>
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
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	
	<c:otherwise>
		<h3 class="noApplicants"><fmt:message key="viewAllApplicants.noContent" bundle="${lang}"/></h3>
	</c:otherwise>
</c:choose>	
	
</body>
</html>