<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags" %>
<%	request.setCharacterEncoding("UTF-8"); %>

<%	// isELIgnored 속성을 true로 하게되면 
	// 표현언어 구문을 해석할 때 표현언어가 아닌 단순한 text로 해석하게 된다.
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><spring:message code="site.title" text="Member Info"/></title>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="./common/topMenu.jsp" flush="false"/>

<div class="container" align="center">

	<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/test/locale.do?locale=ko">한국어</a>
	<a class="btn btn-sm btn-danger"  href="${pageContext.request.contextPath}/test/locale.do?locale=en">ENGLISH</a>
	<h1><spring:message code="site.title" text="Member Info"/></h1>
	<p>
		<h3>
			<spring:message code="site.name" text="no name"/> :
			<spring:message code="name"		 text="no name"/>
		</h3>
	</p>
	<p>
		<h3>
			<spring:message code="site.job"  text="no job"/> :
			<spring:message code="job"		 text="no job"/>
		</h3>
	</p>
	<br/>

	<input type="button" class="btn btn-primary" value="<spring:message code='btn.send'/>"  />
	<input type="button" class="btn btn-warning" value="<spring:message code='btn.cancel'/>"/>
	<input type="button" class="btn btn-danger" value="<spring:message code='btn.finish'/>"/>

</div>

<hr/>

<!-- 하단 메뉴 -->
<jsp:include page="./common/footer.jsp" flush="false"/>
</body>
</html>





