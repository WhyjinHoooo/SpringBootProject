<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>공지사항 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/noticeModify/style.css">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %> 
	<!-- Javascript 코드로 form태그의 action기능을 대신하는 기능을 생성 예정
	일종의 REST API개발방식의 일부분이다. -->
	<form id="menuForm">
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		
		<div id="container">
			<div id="menuAdmin">
				<h2 id="menuAdminH2">공지사항 수정</h2>
				<br>
				<label for="memID">회원아이디</label>
				<input type="hidden" id="idx" name="idx" value="${menu.idx}" />
				
				<input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${menu.memID}" readonly style="background-color: #e0e0e0;"/> 
				<br>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" placeholder="제목" maxlength="10" value="${menu.title}" /> 
				<br>
				<label for="content">내용</label>
				<input type="text" id="content" name="content" placeholder="내용" maxlength="30" value="${menu.content}" /> 
				<br>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${menu.writer}" readonly style="background-color: #e0e0e0;"/><!-- 작성자 -->
				<br>
				
				<div id="buttonContainer">
					<c:if test="${MANAGER == true}">
						<button type="button" id="buttonUpdate">수정</button>
					</c:if>
				</div>
				
			</div>
		</div>
	</form>

	<%@include file="/WEB-INF/views/common/footer.jsp" %> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/noticeModify/script.js"></script>
</body>
</html>