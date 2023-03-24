<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	
	request.setCharacterEncoding("UTF-8"); 
	// 현재 년도를 구한다.
	Timestamp 	nowTime		= new Timestamp(System.currentTimeMillis());
	System.out.println(nowTime);
	int			lastYear	= Integer.parseInt(nowTime.toString().substring(0, 4));
%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>날자 선택</title>
	<!-- datepicker에서 사용 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../../common/topMenu.jsp" flush="false"/>
<!-- datepicker에서 사용 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	

<div class="container">
	<form class="form-horizontal">
		<div class="form-group">
			<div>
				<h2 align="center">도서 정보</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">출판일자</label>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_year" style="width:100%"
						onkeydown="nextFocus(publishing_month)">
						<%for(int year = lastYear; year >= 2001; year--) { %>
							<option value="<%=year%>"><%=year%></option>
						<% } %>
					</select>
					<span class="input-group-addon">년</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_month" style="width:100%"
						onkeydown="nextFocus(publishing_day)">
						<c:forEach var="month" begin="1" end="12" step="1">
							<option value="${month}" <c:if test="${month == 5}">selected</c:if> >${month}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">월</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_day" style="width:100%"
						onkeydown="nextFocus(publishing_com)">
						<c:forEach var="day" begin="1" end="31" step="1">
							<option value="${day}">${day}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">일</span>
				</div>
			</div>
		</div>
	</form>
</div>

<hr/>

<div class="container">
	<form class="form-horizontal">
		<div class="form-group">
			<div>
				<h2 align="center">제품 생산 계획</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">일자</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="datepicker1" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">일자</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="datepicker2" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">조회기간을 선택하십시요.</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="datepicker3" placeholder="날짜를 선택하십시오."/>
			</div>
			<div class="col-sm-1">
				&nbsp;~&nbsp;
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="datepicker4" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
	</form>
</div>


<!-- 하단 메뉴 -->
<jsp:include page="../../common/footer.jsp" flush="false"/>

<script>
function nextFocus(where) {
	if(event.keyCode == 13) {	// Enter 키를 눌렀을 경우, 다음 입력 필드로 이동시킨다.
		where.focus();
	}
}
</script>

<script>
$(function() {
	$("#datepicker1").datepicker();
	
	$("#datepicker2").datepicker({
		// 선택할 수 있는 최대 날짜 +1m +1w은 1달 1주일 뒤까지 선택이 가능하다.
		// [+, -][숫자][y, m, w, d]
		maxDate:	"+1m +1w",
		minDate:	"-100y"
	});
	
	$("#datepicker3, #datepicker4").datepicker({
		// 옵션들 생략
	});
});

$.datepicker.setDefaults({
	// minDate:   "-100y", // 100년전 날짜까지 보여준다
	// yearRange: 'c-100:c+10', // 리스트박스에 보여줄 범위
	// 오늘날짜 기준으로 100년전 부터 향후 10후까지 보여준다.
	// 2가지를 같이 쓰셔야 됩니다.

	minDate:	"-100y",
	yearRange: 'c-100:c+10',
	showOn:			"both",			// 버튼과 텍스트 필드 모두 캘린더를 보여준다.
	changeYear:		true,			// 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	changeMonth:	true,			// 월을 바꿀 수 있는 셀렉트 박스를 표시한다.
	showAnim:		"slide",		// 애니메이션을 적용한다.
	dateFormat:		"yy년 mm월 dd일",		// 날짜 포맷
	prevText:		'이전 달',		// 마우스 오버시 이전달이라는 텍스트 풍선도움말을 보여준다.
	nextText:		'다음 달',		// 마우스 오버시 다음달이라는 텍스트 풍선도움말을 보여준다.
	closeText:		'닫기',			// 닫기 버튼 텍스트 변경
	currentText:	'오늘',			// 오늘 버튼 텍스트 변경
									// 월을 한글로 표시한다.
	monthNames:		['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	monthNamesShort:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
									// 주를 한글로 표시한다.
	dayNames:		['일', '월', '화', '수', '목', '금', '토'],	
	dayNamesShort:	['일', '월', '화', '수', '목', '금', '토'],	
	dayNamesMin:	['일', '월', '화', '수', '목', '금', '토'],	
	showMonthAfterYear:	true,	// true : 년 월,  false : 월 년
	yearSuffix:		'년',		// 년도 셀렉트 박스 다음에 '년'이란 글자를 보여준다.
	showButtonPanel:	true	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션
});
</script>


</body>
</html>






