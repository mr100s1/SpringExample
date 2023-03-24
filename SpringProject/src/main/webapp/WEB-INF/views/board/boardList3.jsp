<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록 보기</title>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<div align="center">
		<h2>게시글 목록 (페이징 + 검색)</h2>
		<button class="btn btn-primary" onclick="location.href='/board/boardRegisterForm'">글쓰기</button>
		<hr/>
	</div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr class="warning">
				<th class="col-sm-1 text-center">번호</th>
				<th class="col-sm-3 text-center">제  목</th>
				<th class="col-sm-3 text-center">내  용</th>
				<th class="col-sm-1 text-center">글쓴이</th>
				<th class="col-sm-3 text-center">작성일자</th>
				<th class="col-sm-1 text-center">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList}">
			<tr>
				<td align="right">${board.getBno()}</td>
				<td><a href="${contextPath}/board/boardDetail?bno=${board.bno}&flag=0">${board.subject}</a></td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td><fmt:formatDate value="${board.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초"/></td>
				<td align="right"><fmt:formatNumber value="${board.readCount}" pattern="#,###"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div align="center">
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-2">
				<select id="searchType" class="form-control">
					<option>검색종류</option>
					<option value="s" <c:if test="${searchType == 's'}">selected</c:if>>제목</option>
					<option value="c" <c:if test="${searchType == 'c'}">selected</c:if>>내용</option>
					<option value="w" <c:if test="${searchType == 'w'}">selected</c:if>>글쓴이</option>
				</select>
			</div>
			<div class="col-sm-2">
				<input type="text" id="searchKeyword" value="${keyword}"/>
			</div>
			<div class="col-sm-2">
				<button id="searchBtn" class="btn btn-danger">검색</button>
			</div>
		</div>
	</div>
	
	<br/>
	<div class="col-sm-12" align="center">
		<ul class="btn-group pagination">
			<c:if test="${pageMaker.prev}">
				<li>
					<a href='<c:url value="/board/boardList3?page=${pageMakerf.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
				</li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
				<li>
					<a href='<c:url value="/board/boardList3?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li>
					<a href='<c:url value="/board/boardList3?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
				</li>
			</c:if>
		</ul>
	</div>
	
	<form id="formList" action="/board/boardList3" method="get">
		<input type='hidden' name='searchType'	value='${searchType}'/>
		<input type="hidden" name="keyword"		value="${keyword}"/>
	</form>
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>


<script>
$(document).ready(function() {
	
	var formObj = $("#formList");
	
	// 검색 버튼을 눌렀을 경우
	$("#searchBtn").click(function() {
		var typeStr		= $("#searchType").find(":selected").val();
		var keywordStr	= $("#searchKeyword").val();
		alert(typeStr + ":" + keywordStr);
		
		// 서버로 전송하기 전에, name 속성에 값을 넣어준다.
		formObj.find("[name='searchType']").val(typeStr);
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val("1");
		formObj.submit();
	});
	
});
</script>

</body>
</html>









