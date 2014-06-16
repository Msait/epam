<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.university.domain.faculty.Faculty"%>
<%@page import="java.util.List"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ex" uri="/WEB-INF/TLD/showFaculties.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="faculties.page.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/styles/main.css" />" />
<style>
</style>
</head>
<body>
	
	<jsp:include page="/WEB-INF/include/languageChooser.jsp" />
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<c:if test="${logged == null || logged == false}">
		<form id="adminId" class="adminClass" action="viewAdminPage"
			method="post">
				<p class="adminFormName">
					<input type="text" name="uid" id="adminName">
					<label for="adminName"><fmt:message key="faculties.page.login.login" bundle="${lang}"/></label>
				</p>
				<p class="adminFormPass">
					<input type="password" name="upass" id="adminPass">
					<label for="adminPass"><fmt:message key="faculties.page.login.password" bundle="${lang}"/></label>
				</p>
				<p class="adminSubmit">
					<input type="submit" value="<fmt:message key="faculties.page.login.submit" bundle="${lang}"/>">
					<input type="checkbox" name="saveCookie" id="cb">
					<label for="cb"><fmt:message key="faculties.page.login.savecookie" bundle="${lang}"/></label>
				</p>
		</form>
		<div id="contentWrapper">
	</c:if>
	
		<h3 class="pageTitle"><fmt:message key="faculties.page.title" bundle="${lang}"/></h3>
		
		<table class="facultyTable">
			<thead>
				<tr>
					<th style="text-align: center;"><fmt:message key="faculties.table.header" bundle="${lang}"/></th>
					<th style="text-align: center;"><fmt:message key="faculties.table.places" bundle="${lang}"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="faculty" items="${faculties}">
					<tr>
						<td><ex:showFaculties val="${faculty}"/></td>
						<%-- <td><a href='viewDepartmentSubjects?id=${faculty.facultyId} '>
								${faculty.facultyName }</a></td> --%>
						<td>${faculty.facultyLimit }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
	<c:if test="${logged == null || logged == false}">
		</div>
	</c:if>
	
</body>
</html>