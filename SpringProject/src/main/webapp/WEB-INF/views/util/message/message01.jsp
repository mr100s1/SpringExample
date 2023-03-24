<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
  <%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri	=	"http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 다국어 기능</title>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../../common/topMenu.jsp" flush="false"/>

<div class="container">
	<h1 align	=	"center">다국어 기능</h1>
	
	<fmt:setLocale value	="ko_KR"/>
	<h1>회원 정보</h1>
	<fmt:bundle	 	basename="member.member">
		<h2>이름: <fmt:message	key="mem.name"/></h2>	
		<h2>주소: <fmt:message key="mem.address"/></h2>	
		<h2>직업: <fmt:message key="mem.job"/></h2>	
	</fmt:bundle>
	
	<hr/>
	
	<fmt:setLocale value	="en_US" />
	<h1>회원 정보</h1>
	<fmt:bundle	 	basename="member.member">
		<h2>이름: <fmt:message	key="mem.name"/></h2>	
		<h2>주소: <fmt:message key="mem.address"/></h2>	
		<h2>직업: <fmt:message key="mem.job"/></h2>	
	</fmt:bundle>
</div>





<!-- 하단 메뉴 -->
<jsp:include page="../../common/footer.jsp" flush="false"/>
</body>
</html>