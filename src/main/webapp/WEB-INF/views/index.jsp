<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>카네스블랙 카페</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style.css">
</head>
<body>
	
	<%@include file="/WEB-INF/views/common/header.jsp" %> 
	
	<div id="container">
		<div id="menuAdmin">
			<h2 id="menuAdminH2">공지사항</h2>
			
			<!-- 특별한 기능(JSTL 라이브러리를 이용해서 세션에 있는 변수를 세팅 조건을 설정)
			세션 공간에 저장되어 있는 'MANAGER'의 값이 true일 때, 작성이라는 버특이 보이게금 할 것임
			즉, 운영진만 가질 수 있는 기능을 추가할 거임
			 -->
			<c:if test="${MANAGER == true}">
				<button type="button" id="" onclick="location.href=`${pageContext.request.contextPath}/noticeAdd`">작성</button>
				<!-- location.href=localhost:XXXX/noticeAdd -->
			</c:if>
			<div id="menuList">
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/script.js"></script>

</body>
</html>