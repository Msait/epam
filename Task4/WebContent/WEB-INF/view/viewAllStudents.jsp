<%@page import="java.util.List"%>
<%@page import="com.university.domain.applicant.Applicant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.university.repository.jdbc.DaoFactory.ConnTypes"%>
<%@page import="com.university.repository.jdbc.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="viewAllStudents.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/main.css" />" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/languageChooser.jsp" />
	<jsp:include page="/WEB-INF/include/header.jsp" />	
	
	<h1 style="text-align: center;"><fmt:message key="viewAllStudents.title" bundle="${lang}"/></h1>

	<table class="studentsTable">
		<thead>
			<tr>
				<th><fmt:message key="viewAllStudents.table.name" bundle="${lang}"/></th>
				<th><fmt:message key="viewAllStudents.table.lastname" bundle="${lang}"/></th>
				<th><fmt:message key="viewAllStudents.table.faculty" bundle="${lang}"/></th>
				<th><fmt:message key="viewAllStudents.table.certificate" bundle="${lang}"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${students}">
				<tr>
					<td><span>${student.key.applicantName }</span></td>
					<td><span>${student.key.applicantLastName }</span></td>
					<td><span>${student.value.facultyName }</span></td>
					<td><span>${student.key.applicantCertificate }</span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>