<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 올리기</title>
	<style>
	iframe {
		width:			800px;
		height:			200px;
		border:			1px;
		border-style:	solid;
	}
	</style>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../../common/topMenu.jsp" flush="false"/>

<div class="container">
	<div align="center">
		<h2>파일 올리기</h2>
	</div>
	
	<!-- target="iframe1" -->
	<!-- 파일을 업로드하면 원래는 action="${contextPath}/util/upload/uploadForm"으로 이동해야 하는데
		 페이지를 넘어가지 않기 위해서 제출된 결과를 iframe으로 보낸다. -->
	<form class="form-horizontal" action="${contextPath}/util/upload/uploadForm" method="post"
			enctype="multipart/form-data" target="iframe1">
			<!-- name="file"의 변수명과 컨트롤로의 MultipartFile file과 일치해야 한다. -->
			<div class="form-group">
				<div class="col-sm-3">
					<input type="file"   class="btn btn-warning" name="file"/>
				</div>
				<div class="col-sm-2">
					<input type="submit" class="bnt btn-primary" value="파일 올리기"/>
				</div>
			</div>
			<!-- iframe에 업로드한 결과를 출력한다. -->
			<div class="form-group">
				<div class="col-sm-12">
					<iframe name="iframe1"></iframe>
				</div>
			</div>
	</form>
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../../common/footer.jsp" flush="false"/>
</body>
</html>





