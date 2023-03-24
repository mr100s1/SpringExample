<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="${contextPath}/resources/js/signup.js"></script>
</head>
<body>
	<!-- 상단 메뉴바 -->
	<jsp:include page="../../common/topMenu.jsp" flush="false"/>
	
	<h1>doA.jsp</h1>
	<h2>저는 doA.jsp 파일입니다.</h2>
	
	<hr/>
	<h2>스크립트 테스트</h2>
	<div id="idError"></div>
	
	<input type="text" id="userId" />
	<button type="button" class="btn btn-danger" onclick="signUpCheck()">
		<span class="glyphicon glyphicon-erase">스크립트 테스트</span>
	</button>
	
</body>
</html>