<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화 좌석 예매</title>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">

	<div class="row" align="center">
		<div class="col-sm-offset-2 col-sm-8">
			<h2>좌석 예약</h2>
		</div>
		<div class="col-sm-offset-3 col-sm-8">
			<label class="control-label col-sm-2">남은 좌석수</label>
			<div id="reserveNO" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:#2F70A9; color:#FFFFFF; height:28px; border:1px solid; float:left;">
				${reserveNO}
			</div>
			<label class="control-label col-sm-2">예약 좌석수</label>
			<div id="reserveOK" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:#D77875; color:#FFFFFF; height:28px; border:1px solid; float:left;">
				${reserveOK}
			</div>
		</div>
	</div>
	
	<div class="row" align="center">
		<form action="" method="post" class="pt-3" style="max-width:1720px;">
			<c:forEach var="list" items="${SeatList}" begin="0" end="99" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNO" style="width:50px;" value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNO" style="width:50px;" value="${list.seatID}" disabled>${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 12 == 0}">
						<br/>
					</c:when>
				</c:choose>
			</c:forEach>
			
			<br/><br/><br/>
			
			<c:forEach var="list" items="${SeatList}" begin="100" end="199" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNO" style="width:50px;" value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNO" style="width:50px;" value="${list.seatID}" disabled>${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 16 == 0}">
						<br/>
					</c:when>
				</c:choose>
			</c:forEach>
		</form>
	</div>

</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

<script>
//-------------------------------------------------------------------------------------------------
// 빈 좌석을 클릭하면 예약 업무를 실행한다.
//-------------------------------------------------------------------------------------------------
$('.seatNO').on('click', function() {
	
	// 클릭한 좌석의 인덱스를 알아낸다.
	var idx = $('.seatNO').index(this);
	//alert(idx);
	
	// 예약 확정/취소
	if(!confirm("좌석 " + $('.seatNO').eq(idx).val() + "번을 예약하겠습니까?")) {
		// 취소(아니오) 버튼 클릭 시의 이벤트
	} else { 
		// 확인(예) 버튼 클릭 시 이벤트
		// 예약 시작이 진행된다.
		$.ajax({
			url:		"/movie/seatReservation",
			type:		"post",
			dataType:	"json",
			data:		{"seatID" : $('.seatNO').eq(idx).val()},	// 누른 좌석번호
			success:	function(data) {
				// alert("Return Value : " + data);
				if(data == 1) {	// 좌석 예약이 성공한 경우
					alert("좌석 예매가 되었습니다!");
				
					/* 예매좌석 수 변화를 해결하기 위한 방법 1
					location.href = "/movie/seatReservation?movieID=1";
					*/
					
					// 예매좌석 수 변화를 해결하기 위한 방법 2
					// 예약이 완료된 좌석의 버튼은 색상을 변경한다.
					$('.seatNO').eq(idx).addClass('btn-danger').removeClass('btn-primary');
					
					// 예약이 완료되었으므로 버튼을 누르지 못하도록 변경한다.
					$('.seatNO').eq(idx).attr('disabled', true);
					
					// 예매가 성공하였으므로 남은 좌석은 -1로 하여 화면에 보여준다.
					// 문자열을 숫자로 변경하여 계산한다.
					let remainCount 	= Number($("#reserveNO").text()) -1;	
					$("#reserveNO").text(remainCount);
					
					// 예매가 성공하였으므로 예약 좌석은 +1로 하여 화면에 보여준다.
					// 문자열을 숫자로 변경하여 계산한다.
					let reserveCount	= Number($("#reserveOK").text()) + 1;
					$("#reserveOK").text(reserveCount);

				} else {	// 좌석 예약 중 문제가 생긴 경우
					alert("좌석 예매 중 장애가 발생하였습니다. 잠시 후에 다시 해주십시오!");
				}
			}
			
		});
		
	}
	
	
});



</script>

</body>
</html>
