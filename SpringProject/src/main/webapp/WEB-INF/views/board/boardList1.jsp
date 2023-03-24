<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록 (Paging 1)</title>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<form class="form-horizontal" id="frm">
		<div class="form-group">
			<div>
				<h2 align="center">게시글 목록 (Paging 1)</h2>
				<h4 align="center"><font color="FF5050">총 건수 : ${totalCount}&emsp;현재 ${pageNum}페이지</font></h4>
			</div>
		</div>
		<table class="table table-bordered table-striped table-hover">
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
				<c:forEach items="${boardList}" var="list">
				<tr>
					<td align="center">${list.bno}</td>
					<td><a href="${contextPath}/board/boardDetail?bno=${list.bno}&flag=0">${list.subject}</a></td>
					<td>${list.content}</td>
					<td align="center">${list.writer}</td>
					<td><fmt:formatDate value="${list.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초" /></td>
					<td align="right">${list.readCount}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<p align="center">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/boardRegisterForm'">게시글 쓰기</button>
		</p>
	</form>
	
	<div align="center">
		<!-- 페이지를 보여주는 영역 -->
		<!-- totalCount(전체 데이터 건수)  pageCount(보여줄 총 페이지 수) -->
		<c:if test="${totalCount > 0}">
			<c:set var="totalCount" value="${totalCount}"/>
			<c:set var="pageSize"	value="10"/>
			<c:set var="pageBlock" 	value="10"/>

			<!-- 전체 데이터 건수를 pageSize 로 나누어, 나머지가 있으면 한 페이지를 더 만든다. -->
			<c:set var="pageCount" value="${totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1) }"/>
			<fmt:parseNumber var="pageCount" integerOnly="true" value="${pageCount}"/>

			<!-- startPage를 구하기 위해서 먼저 (pageNum/10)의 계산 값을 소수가 없는 정수 값으로 만들어야 한다. -->
			<fmt:parseNumber var="startPageInt" integerOnly="true" value="${pageNum / pageSize}"/>
			<!-- <c:set var="startPage" value="${(pageNum/10)*10+1}"/> -->
			
			<!--  
			<c:set var="startPage" 	value="${startPageInt * pageSize + 1}"/>
			${pageNum / pageSize}에서 endPage는 +1이 되서 나오므로 아래의 내용으로 대치한다.
			-->
			<c:choose>
				<c:when test="${pageNum % pageSize == 0 }">
					<c:set var="startPage" value="${(startPageInt - 1) * 10 + 1}" />
				</c:when>
				<c:otherwise>
					<c:set var="startPage" value="${startPageInt * 10 + 1}" />
				</c:otherwise>
            </c:choose>
			<c:set var="endPage"	value="${startPage + pageBlock-1 }"/>
			
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount }"/>
			</c:if>
			
			<!-- prev -->
			<c:if test="${startPage > pageBlock}">
				<a href="/board/boardList1?pageNum=${startPage-pageBlock}">[이전]</a>
			</c:if>
			
			<c:forEach var="page" begin="${startPage}" end="${endPage}">
				<a href="/board/boardList1?pageNum=${page}">[${page}]</a>&nbsp;&nbsp;
			</c:forEach>
			
			<!-- next -->
			<c:if test="${endPage < pageCount}">
				<a href="/board/boardList1?pageNum=${startPage+pageBlock}">[다음]</a>
			</c:if>
			
		</c:if>
	</div>
	
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>








