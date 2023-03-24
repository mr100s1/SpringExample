<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-7G0VHPCRPT"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-7G0VHPCRPT');
</script></head>

<%
/*
	sticky-top 은 상단 공간을 차지하면서 위에 고정하고,
	fixed-top  은 상단 공간을 차지하지 않고 위에 고정한다.
		일부 내용은 상단 메뉴바에 가려져서 보이지 않을 수가 있다.
*/
%>

<nav class="navbar navbar-inverse navbar-sticky-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextPath}/main.do">홈페이지</a>
		</div>
	
		<!-- 실질적인 메뉴를 나열한다. -->
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-left">
				<li>
					<form class="navbar-form navbar-left" method="post" action="${contextPath}/member/login.do">
						<c:choose>
							<c:when test="${isLogOn == true && member != null}">
								<p class="navbar-text"><b>${member.name}님, 즐거운 시간되십시요.</b></p>
								<a href="${contextPath}/member/logout.do" class="btn btn-danger">
									<span class="glyphicon glyphicon-log-out"></span> 로그아웃</a>
							</c:when>
							<c:otherwise>
								<div class="form-group">
									<input type="text"		class="form-control" name="id"  size="12" maxlength="20" placeholder="아이디"/>
									<input type="password"	class="form-control" name="pwd" size="12" maxlength="20" placeholder="비밀번호"/>
								</div>
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-log-in"></span> 로그인
								</button>
							</c:otherwise>
						</c:choose>
					</form>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">기본 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/exam/exam1/doA">doA</a></li>
						<li><a href="${contextPath}/exam/exam1/doB">doB</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/exam/exam2/doC">doC</a></li>
						<li><a href="${contextPath}/exam/exam2/gugudanForm.do">구구단</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/exam/exam3/doD">회원정보 (객체)</a></li>
						<li><a href="${contextPath}/exam/exam3/doE">회원정보 (Map)</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/util/zipcode/address">주소검색 (Daum API)</a></li>
						<li><a href="${contextPath}/util/datepicker/datepicker">날짜 선택</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/util/selectrow/selectRow1">Row와 Column 선택</a></li>
						<li><a href="${contextPath}/util/selectrow/selectRow2">Row와 Column 선택</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/test/locale.do">인터셉터</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/util/message/message01.do">JSTL 다국어 기능</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">회원 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
						<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
						<li><a href="${contextPath}/member/registerAjaxForm.do">회원가입 (Ajax)</a></li>
						<li><a href="${contextPath}/member/listMembers.do">회원목록</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">게시글 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/board/boardRegisterForm">게시글 쓰기</a></li>
						<li><a href="${contextPath}/board/boardList">게시글 전체목록</a></li>
						<li><a href="${contextPath}/board/boardList1">게시글 전체목록 (Paging 1)</a></li>
						<li><a href="${contextPath}/board/boardList2">게시글 전체목록 (Paging 2)</a></li>
						<li><a href="${contextPath}/board/boardList3">게시글 전체목록 (Paging 3 + 검색)</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">파일 올리기 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/util/upload/uploadForm">파일 올리기 (Form)</a></li>
						<li><a href="${contextPath}/util/upload/uploadAjax">파일 올리기 (Ajax)</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">영화관 좌석 예약 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/movie/seatReservation?movieID=1">영화관 좌석 예약</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Vue 데이터 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/vueboard/vboardMain">Vue 게시글 메인화면</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/board/boardListVue">게시글 목록 (Vue)</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/vueexam/addUp">Jsp에서 Vue 사용하기</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/vueboard/vtest">Vue 테스트</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Google API 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/google/gChart00">Google Chart 0</a></li>
						<li><a href="${contextPath}/google/gChart01">Google Chart 1</a></li>
						<li><a href="${contextPath}/google/jsfiddleChart">jsfiddleChart</a></li>
						<li><a href="${contextPath}/google/testChart00">Google Chart 1</a></li>
						<li role="presentation" class="divider"></li>
						<li><a href="${contextPath}/google/helloAnalytics">Google Analytics</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">프로그램 테스트 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/test/main">영화관 좌석 예약</a></li>
					</ul>
				</li>
				
			</ul>
		</div>
	
	</div>
</nav>










