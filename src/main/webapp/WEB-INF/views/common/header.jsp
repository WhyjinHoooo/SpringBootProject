<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/common/header.css">
</head>
<body>
	<div id="header">
		<div style="
		color: white;
		text-align: left;
		float: left;
		cursor: pointer;" onclick="location.href=`${pageContext.request.contextPath}/`">
		CanesBlack Cafe
		</div>
		
		<c:choose>
			<c:when test="${isAuthenticated != null && isAuthenticated == true}">
				<div style="float: right;"> <!-- float: right 오른쪽으로 정렬 -->
					<a href="${pageContext.request.contextPath}/logout" style="color: white; margin-right: 15px; text-decoration: none; font-size: 15px">로그아웃</a>
				</div>
			</c:when>
			<c:otherwise>
				<div style="float: right;"> <!-- float: right 오른쪽으로 정렬 -->
					<a href="${pageContext.request.contextPath}/loginPage" style="color: white; margin-right: 15px; text-decoration: none; font-size: 15px">로그인</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>