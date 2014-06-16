<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.university.domain.subject.Subject"%>
<%@page import="com.university.repository.subject.SubjectRepository"%>
<%@page import="com.university.repository.jdbc.DaoFactory.ConnTypes"%>
<%@page import="com.university.repository.jdbc.DaoFactory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="viewDepartmentSubjects.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/main.css" />" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
		<h1 style="text-align: center;">${faculty.facultyName }</h1>
		<h2 style="text-align: center;"><fmt:message key="viewDepartmentSubjects.title" bundle="${lang}"/></h2>
		
		
		<form action="registerApplicant" method="post" id="ApplicantForm" class="ApplicantForm">
				<p class="commonInfo">
					<fmt:message key="viewDepartmentSubjects.form.registerApplicant.legend" bundle="${lang}"/>
				</p>
				<p class="name">
					<input type="text" name="aname" id="appname" maxlength="15">
					<label for="appname"><fmt:message key="viewDepartmentSubjects.form.registerApplicant.name" bundle="${lang}"/></label>
				</p>
				<p class="lastname">
					<input type="text" name="alastname" id="applastname" maxlength="15">
					<label for="applastname"><fmt:message key="viewDepartmentSubjects.form.registerApplicant.lastname" bundle="${lang}"/></label>
				</p>
				<p class="certificate">
					<input type="text" name="acertificate" id="appcertificate" maxlength="3">
					<label for="appcertificate"><fmt:message key="viewDepartmentSubjects.form.registerApplicant.certificate" bundle="${lang}"/></label>
				</p>
			
				<p class="subjectInfo">
					<fmt:message key="viewDepartmentSubjects.form.RequaredSubjectClass.legend" bundle="${lang}"/>
				</p>
				<p class="appsubjects">
					<c:forEach var="subject" items="${facultySubjects}">
					<p>
						<input type='text' name="id${subject.subjectId}" id="subname" maxlength="3">
						<label for="subname">${subject.subjectName }</label>
					</p>
					</c:forEach>
				</p>
				<input type="hidden" name="faculty" value="${faculty.facultyId}">
				<p class="submit">
					<input type="submit" value="<fmt:message key="viewDepartmentSubjects.form.submit" bundle="${lang}"/>">
				</p>
		</form>
</body>
</html>