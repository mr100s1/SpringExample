<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>Input 값 범위 지정</h1>
	<input type="number" id="starValue" name="starValue" min="1" max="5"/>
	
	
	<script>
	$(document).ready(function() {
		$('#starValue').keyup(function() {
			var inputValue = $('#starValue').val();
			if(inputValue < 1 || inputValue > 5) {
				alert('값의 범위를 넘어섰습니다.\n\n1 ~ 5사이의 값을 입력하셔야 합니다.');
				$('#starValue').focus();
			}
		});
	});
	</script>
</body>
</html>

