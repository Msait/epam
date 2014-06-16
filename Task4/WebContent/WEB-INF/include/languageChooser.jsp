<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="language">
	<select id="lang" name="language" onchange="submit()">
		<option value="en" ${language == 'en' ? 'selected' : ''}>Eng</option>
		<option value="ru" ${language == 'ru' ? 'selected' : ''}>Рус</option>
		<option value="ua" ${language == 'ua' ? 'selected' : ''}>Укр</option>
	</select>
</form>