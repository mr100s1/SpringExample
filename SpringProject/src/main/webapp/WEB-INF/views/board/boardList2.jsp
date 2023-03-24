<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록 (Paging 2)</title>
	
<body>

<!-- Google Analytics -->

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<div align="center">
		<h2>게시글 목록 (Paging 2) ${pageMaker.cri.getPage()}페이지</h2>
		<button class="btn btn-primary" onclick="location.href='/board/boardRegisterForm'">글쓰기</button>
	</div>
	<hr/>
	<table class="table table-striped table-bordered table-hove">
		<thead>
			<tr class="info">
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
				<td align="right">${board.bno}</td>
				<td><a href="${contextPath}/board/boardDetail?bno=${board.bno}&flag=0">${board.subject}</a></td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td><fmt:formatDate value="${board.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초"/></td>
				<td align="right">${board.readCount}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div align="center">
		<ul class="btn-group pagination">
			<c:if test="${pageMaker.prev}">
				<li>
					<a href='<c:url value="/board/boardList2?page=${pageMaker.startPage-1}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
				</li>
			</c:if>
		
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
				<li>
					<a href='<c:url value="/board/boardList2?page=${pageNum}"/>'><i>${pageNum}</i></a>
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<li>
					<a href='<c:url value="/board/boardList2?page=${pageMaker.endPage+1}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
				</li>
			</c:if>
		</ul>
	</div>
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>






