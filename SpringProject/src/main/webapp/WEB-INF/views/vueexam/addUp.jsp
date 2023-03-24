<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Jsp에서 Vue 사용하기</title>
	
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

	<!--  
    <script src="https://unpkg.com/vue@v3.1.1"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>	
	-->
<!-- vue 3 -->
<script src="https://unpkg.com/vue@3"></script>

	<!-- 
	<script src="${contextPath}/resources/js/board.js"></script>
	<script src="../../js/vueAddUp.js" type="module"></script>
	 -->

	<!-- 
	<script src="${contextPath}/resources/js/vueAddUp.js" type="module"></script>
	 
	-->
	<script type="module" src="<c:url value='/resources/js/vueAddUp.js' />"></script> 

</head>
<body>

  <!-- el태그의 app -->
  <div id="app" class="container py-3">

	<h1>JSP에서 Vue 사용하기</h1>

  	<!-- 각 컴포넌트 붙여줌 -->
  	<topmenu-component></topmenu-component> 
	<two-component></two-component>
	<three-component></three-component>
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