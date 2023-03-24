<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주문 통계 페이지</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
		<link href="${contextPath}/resources/css/product.css" rel="stylesheet" type="text/css">
		<script src="${contextPath}/resources/js/product.js"></script>	
		<script src="${contextPath}/resources/js/review.js"></script>
		<script src="${contextPath}/resources/js/googleAPI.js"></script>
		<script src="${contextPath}/resources/js/testChart00.js"></script>
		<!-- 구글 차트 api -->	
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	     <script type="text/javascript">
			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(drawChart);
			
			 drawChart(12340000);
			 
			/***
			function drawChart() {
			var data = google.visualization.arrayToDataTable([
	            ['Month', '매출액(원)'],
	            ['이번달', 12345],			// ['이번달', ${TMBill}],
	            ['지난달', 100]		// ['지난달', ${LMBill}]
	          ]);
	        
	          
	          var options = {
	            chart: {
	              title: '전월 대비 매출',
	              subtitle: '지난달과 이번달 매출 비교입니다.',
	            },
	            bars: 'horizontal' // Required for Material Bar Charts.
	          };

	          var chart = new google.charts.Bar(document.getElementById('barchart_values'));

	          chart.draw(data, google.charts.Bar.convertOptions(options));
	        }
			***/
			
			
		</script>
	</head>
	<body>
		<!-- 상단 메뉴 -->
		<jsp:include page="../common/topMenu.jsp" flush="false"/>
		
		<div id="mainTitle">
			<hr/>
			<h1>주문 통계</h1>
		</div>
		
		<div class="container">
			<div class="row">
				<nav class="col-sm-2" id="myScrollspy">
					<ul class="nav nav-pills nav-stacked">
						<li><a href="/order/orderManagement">최근 등록 상품</a></li>
						<li><a href="/order/orderManagement">최근 등록 Q&A</a></li>
						<li><a href="/order/orderManagement">최근 등록 리뷰</a></li>
						<li><a href="/order/orderManagement">상품 주문 현황</a></li>
						<li class="active"><a href="#">주문 통계</a></li>
					</ul>
				</nav>
				<div class="col-sm-offset-1 col-sm-9">
					<div id="barchart_values" style="width: 100%; height: 200px;"></div>
					<div>
						<input type="hidden" id="TMBill" value="${TMBill}"/>
						<input type="hidden" id="LMBill" value="${LMBill}"/>
					</div>
				</div>
				
				
			</div>
		</div>
	</body>
</html>
	