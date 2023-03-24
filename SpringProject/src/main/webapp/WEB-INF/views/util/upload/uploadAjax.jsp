<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 올리기 (AJAX)</title>
	<style>
	.fileDrop	{
		width:		100%;
		height:		200px;
		border:		1px dotted blue;
	}
	</style>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../../common/topMenu.jsp" flush="false"/>

<div class="container">
	<h2 align="center">Ajax File Upload</h2>
	<div class="fileDrop"></div>		<!-- 파일을 떨어뜨리는 영역 -->
	<div class="uploadedList"></div>	<!-- 업로드된 파일목록을 출력하는 영역 -->
</div>


<!-- 하단 메뉴 -->
<jsp:include page="../../common/footer.jsp" flush="false"/>

<script>
//---------------------------------------------------------------------------------------
// 스크립트는 jQuery를 불러들인 다음에 기술한다.
// 순서가 바뀌면 jQuery로 만든 스크립트가 작동되지 않는다.
//---------------------------------------------------------------------------------------
$(function() {
	
	//---------------------------------------------------------------------------------------
	// 드래그의 기본 효과를 막는다.
	// 드래그의 기본 효과를 막지 않으면, 드래그된 곳에 이미지가 보이게 된다.
	//---------------------------------------------------------------------------------------
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});
	
	//---------------------------------------------------------------------------------------
	// 그림 올리기 영역에 그림을 떨어뜨릴 때 처리하는 함수
	//---------------------------------------------------------------------------------------
	$(".fileDrop").on("drop", function(event) {
		
		alert("그림을 떨어뜨렸습니다.");
		// 그림이 Drop될 때의 기본효과를 막는다.
		event.preventDefault();

		// 첨부파일 배열 : 드래그된 파일의 정보
		// Ctrl + 클릭으로 여러개의 파일을 동시에 올릴 수 있다.
		let files 	= event.originalEvent.dataTransfer.files;
		
		let file	= files[0];	// 첫번째 파일
		
		// 파일정보가 콘솔에 올라간다.
		// 웹 브라우저에서 F12키를 누르면 어떤 파일인지 알 수가 있다.
		console.log(file);
		
		// AJAX로 서버에 전달할 때에는 폼이 별도로 존재하지 않기 때문에 폼 객체를 만들어 주어야 한다.
		let formData = new FormData();	// 폼 객체
		
		// 폼에 file 변수를 추가한다.
		formData.append("file", file);
		
		// 서버에 파일을 업로드한다.(백그라운드에서 실행이 된다.)
		// contentType : false => multipart/form-data로 처리가 된다.
		// processData : false => 일반적으로 서버에 전달하는 데이터는 query string형태로 전달되기 때문에
		// 			data 파라미터로 전달된 데이터를 jquery 내부적으로 query string으로 만든다.
		//			파일전송의 경우 이렇게 하지 않아야 하고, 설정하는 것이 processData : false 이다.
		$.ajax({
			type:			"post",
			url:			"${contextPath}/util/upload/uploadAjax",
			data:			formData,
			dataType:		"text",
			contentType:	false,
			processData:	false,
			success:		function(data, status, req) {
				console.log("data   : "	+ data);	// 업로드된 파일이름
				console.log("status : "	+ status);	// 성공, 실패여부
				console.log("req    : "	+ req);		// 요청 코드값
				
				let	str	= "";
				str = "<div>";
				
				// 컨트롤러에 displayFile() 메서드가 없으면 View에서 엑박으로 나타난다.
				if(checkImageType(data)) {	// 이미지 파일인 경우
					str += "<a href='${contextPath}/util/upload/displayFile?fileName="
							+ getImageLink(data) + "'>";
					str += "<img src='${contextPath}/util/upload/displayFile?fileName="
							+ data + "'></a>";
				} else {	// 이미지 파일이 아닌 경우
					str += "<a href='${contextPath}/util/upload/displayFile?fileName="
							+ data + "'>" + getOriginalName(data) + "</a>";
				}
				
				str += "<span data-src='" + data + "'>[삭제]</span>";
				str += "</div>";
				$(".uploadedList").append(str);
				
			}
		}); // End - $.ajax()
		
	}); // End - $(".fileDrop").on("drop", function(event)
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일인지 아닌지 검사하는 함수
	// i : ignore case()
	//-----------------------------------------------------------------------------------------------------------
	function checkImageType(fileName) {
		var pattern = /jpg|png|jpeg|gif/i;	// 정규표현식(대소문자 무시)
		return fileName.match(pattern);		// 규칙에 맞으면 true, 아니면 false
	} // End - function checkImageType(fileName)
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일의 원래 이름 알아내기
	// 98fb3e60-e2d8-4147-8abe-9362b5db529e_12월 판매계획서.txt
	//-----------------------------------------------------------------------------------------------------------
	function getOriginalName(fileName) {
		if(checkImageType(fileName)) {	// 이미지 파일이면 Skip
			return;
		}
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	} // End - function getOriginalName(fileName)
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일의 원래 이름 알아내기
	// /2023/01/20/s_98fb3e60-e2d8-4147-8abe-9362b5db529e_귀여운 고양이 01.jpg
	//-----------------------------------------------------------------------------------------------------------
	function getImageLink(fileName) {
		if(!checkImageType(fileName)) {	// 이미지 파일이 아니면 Skip
			return;
		}
		// 이미지 파일이라면
		// /2023/01/20/s_98fb3e60-e2d8-4147-8abe-9362b5db529e_귀여운 고양이 01.jpg
		var front	= fileName.substr(0, 12);	// 연월일 경로 (0 부터 12 앞까지)
		var	end		= fileName.substr(14);		// s_ 제거
		
		console.log(front);
		console.log(end);
		
		alert("front:" + front + " == end:"+ end);
		return front+end;	// 경로 + s_를 뺀 나머지(UUID를 붙여만든 파일이름)
		
	} // End - function getImageLink(fileName)

	//-----------------------------------------------------------------------------------------------------------
	// [삭제]버튼을 눌렀을 경우 : 첨부파일 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	$(".uploadedList").on("click", "span", function(event) {
		
		// 현재 클릭한 태그는 무엇인가?
		var that = $(this);
		alert($(this).attr("data-src"));
		
		// [삭제]버튼을 클릭한 이미지를 서버에게 지우도록 한다.
		$.ajax({
			url:		"${contextPath}/util/upload/deleteFile",
			type:		"post",
			data:		"fileName=" + $(this).attr("data-src"),
			dataType:	"text",
			success:	function(result) {
				if(result == "deleted") {
					that.parent("div").remove();
				}
			}, 
			error:		function(error) {
				alert("에러 발생!!!");
			},
			complete:	function() {
				alert("Complete...");
			}
		});
				
		
	}); // End - $(".uploadedList").on("click", "span", function(event)
	
	
	
}); // End - $(function()

</script>


</body>
</html>







