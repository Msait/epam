<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.university.i18n.lang" var="lang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="error.page.title" bundle="${lang}"/></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/main.css" />" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/languageChooser.jsp" />
	<jsp:include page="/WEB-INF/include/header.jsp" />
	<h1 style="text-align: center;"><fmt:message key="error.page.message" bundle="${lang}"/></h1>
</body>
</html>