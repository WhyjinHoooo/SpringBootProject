<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카네스블랙카페</title>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<!-- 데이터를 전달하는 입력창에 해당하는 페이즈의 경우 csrf가 필요한 것 같음 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/noticeAdd/style.css">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %> 
	<!-- Javascript 코드로 form태그의 action기능을 대신하는 기능을 생성 예정
	일종의 REST API개발방식의 일부분이다. -->
	<form id="menuForm">
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		
		<div id="container">
			<div id="menuAdmin">
				<h2 id="menuAdminH2">공지사항 작성</h2>
				<br>
				<label for="memID">회원아이디</label>
				<input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${username}" disabled /> 
				<br>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" placeholder="제목" maxlength="10" /> 
				<br>
				<label for="content">내용</label>
				<input type="text" id="content" name="content" placeholder="내용" maxlength="30" /> 
				<br>
				<input type="hidden" id="indate" name="indate"> <!-- 입력날짜 -->
				<input type="hidden" id="count" name="count"> <!-- 조회수 -->
				<input type="hidden" id="writer" name="writer"><!-- 작성자 -->
				
				
				<button type="button" id="buttonSubmit">확인</button>
			</div>
		</div>
	</form>

	<%@include file="/WEB-INF/views/common/footer.jsp" %> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/noticeAdd/script.js"></script>
</body>
</html>