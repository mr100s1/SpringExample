<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Table에서 선택한 Row와 Column 위치 알아내기</title>
	<style>
	table	{
		border:			1px solid #DDD;
	}
	td	{
		border:			1px solid #BBB;
		text-align:		center;
		font-size:		2em;
	}
	</style>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../../common/topMenu.jsp" flush="false"/>

<div class="container">
	<h1 align="center">Selected Row &amp; Selected Column</h1>
	<hr/>
	<table class="table table-striped" id="myTable">
		<tr>
			<td>0-0</td><td>0-1</td><td>0-2</td><td>0-3</td>
		</tr>
		<tr>
			<td>1-0</td><td>1-1</td><td>1-2</td><td>1-3</td>
		</tr>
		<tr>
			<td>2-0</td><td>2-1</td><td>2-2</td><td>2-3</td>
		</tr>
		<tr>
			<td>3-0</td><td>3-1</td><td>3-2</td><td>3-3</td>
		</tr>
		<tr>
			<td>4-0</td><td>4-1</td><td>4-2</td><td>4-3</td>
		</tr>
		<tr>
			<td>5-0</td><td>5-1</td><td>5-2</td><td>5-3</td>
		</tr>
	</table>
	
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../../common/footer.jsp" flush="false"/>

<script>
$(document).ready(function() {
	
	$('#myTable td').bind('click', function() {
		var row = $(this).closest('tr').index();
		var col = $(this).closest('td').index();
		console.log(row + ":" + col);
		alert(row + " : " + col);
	});
	
});
</script>


</body>
</html>














