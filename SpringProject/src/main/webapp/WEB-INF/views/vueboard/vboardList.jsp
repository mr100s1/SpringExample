<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

	<!-- vue 3 -->
	<script src="https://unpkg.com/vue@3"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

	<!--  
	<script type="module" src="/resources/js/vboardMain.js"></script> 
	-->
	<script type="module" src="<c:url value='/resources/js/vboardMain.js' />"></script> 

</head>
<body>

	<!-- el태그의 app -->
	<div id="app" class="container py-3">

	<h1>Vue Board Main 화면</h1>
	<h2>{{ message }}</h2>

  	<!-- 각 컴포넌트 붙여줌 -->
  	<!-- 태그 네임은 소문자로 해주어야 함 : 대문자를 섞어서 만들어도 Browser에서 소문자로 인식해 사용함. -->
	<topmenu-component></topmenu-component>
  	

	<button v-on:click="getData">게시글 보기</button>
	
	<hr/>
	  <div class="board-list">
    <div class="common-bottons">
      <button type="button" class="w3-button w3-round w3-blue" v-on:click="fnWrite">글쓰기</button>
    </div>
    <h3>게시판 목록</h3>
    <table class="w3-table-all">
      <thead>
        <tr>
          <th>번호</th>
          <th>제  목</th>
          <th>작성자</th>
          <th>등록일자</th>
          <th>조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, idx) in boardList" :key="idx">
          <td>{{ row.bno }}</td>
          <td><a v-on:click="fnDetailView(`${row.bno}`)">{{ row.subject }}</a></td>
          <td>{{ row.writer }}</td>
          <td>{{ row.reg_date }}</td>
          <td>{{ row.readCount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
	

  </div>

</body>

<!-- 부트스트랩 스타일 -->
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media (min-width: 768px) {
	.bd-placeholder-img-lg {
	  font-size: 3.5rem;
	}
}
</style>

</html>
