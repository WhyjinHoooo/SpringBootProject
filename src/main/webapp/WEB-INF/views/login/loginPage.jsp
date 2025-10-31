<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %> 

<div id="login-container-wrapper">
	<div id="login-container">
		<h2>로그인</h2>
		<!-- localhost:port번호/login으로 username과 password가 넘어감 -->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="input-group">
				<label for="username">아이디</label>
				<input type="text" id="username" name="username" required/>
				<!-- input태그의 name속성은 다음과 같다
				서버로 데이터를 전송할 때, http URL형태로 데이터를 백으로 전송한다.
				이때, name속성을 키값으로 하고, 작성한 데이터를 value로 사용한다. -->
			</div>
			<div class="input-group">
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" required/>
			</div>
			<input type="submit" id="login-button" />
		</form>
		<div id="register-link">
			<a href="${pageContext.request.contextPath}/register">회원가입</a>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/common/footer.jsp" %> 
</body>
</html>