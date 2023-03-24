<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인 페이지</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!-- HOME CSS -->
		<link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
		<!-- HOME JS -->
		<script src="${contextPath}/resources/js/home.js"></script>
		
		<style>
		/*
		body {
			position:	relative;
		}
		nav {
			height:		700px;
			border:		1px solid #F00;
		}
		div.col-sm-9 div {
			height:		700px;
			border:		1px solid #00F;
		}
		*/
		</style>
		
	</head>
	<body data-spy="scroll" data-target="#mainNav">
		
	<div class="container">
		<div class="row">
			<!-- 세로 메뉴 바 -->
			
				<nav class="col-sm-2" id="mainNav">
					<ul class="nav nav-pills nav-stacked" >
						<li class="active">
							<a href="#mainSection1">mainSection1</a>
						</li>
						<li>
							<a href="#mainSection2">mainSection2</a>
						</li>
						<li>
							<a href="#mainSection3">mainSection3</a>
						</li>
						<li>
							<a href="#mainSection4">mainSection4</a>
						</li>
						<li>
							<a href="#mainSection5">mainSection5</a>
						</li>
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">mainSection6 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#mainSection61">mainSection6-1</a></li>
								<li><a href="#mainSection62">mainSection6-2</a></li>
							</ul>
						</li>
					</ul>
				</nav>	
		
				<div class="col-sm-9">
					<div class="container">
						ddfsf
					</div>
				</div>
		</div>
	</div>
	
	</body>
</html>