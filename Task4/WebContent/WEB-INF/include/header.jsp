<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<div id="header" class="header">

	<h1 class="headerLogo1"><fmt:message key="project.title1" bundle="${lang}"/> </h1> <h1 class="headerLogo2"><fmt:message key="project.title2" bundle="${lang}"/></h1>
	
	<div id="menu" class="menu">
		<a href="viewAllFaculties"><fmt:message key="menu.viewAllFaculties" bundle="${lang}"/></a>
		<a href="viewAllApplicants"><fmt:message key="menu.viewAllApplicants" bundle="${lang}"/></a>
		<a href="viewAllStudents"><fmt:message key="menu.viewAllStudents" bundle="${lang}"/></a>
		<c:if test="${logged != null && logged == true}">
			<a href="viewAdminPage"><fmt:message key="menu.viewAllAdminPage" bundle="${lang}"/></a>
		</c:if>
	</div>
</div>
	<hr>
	
	<c:if test="${sessionScope.admin != null}">
		<h4 class="adminLogout">
			<fmt:message key="faculties.page.admin.hello" bundle="${lang}"/>,  <c:out value=" ${sessionScope.admin.name}" />. 
			<a href="logOut"><fmt:message key="faculties.page.admin.logout" bundle="${lang}"/></a>
		</h4>
	</c:if>

