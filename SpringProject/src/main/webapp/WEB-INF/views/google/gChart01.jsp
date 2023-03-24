<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Google Chart</title>
	<!-- 구글 챠트 라이브러리 불러오기 -->
    <script src="https://www.gstatic.com/charts/loader.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <script type="text/javascript">

	// Load the Visualization API and the corechart package.
	google.charts.load('current', {'packages':['corechart']});
	
	// Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(drawChart);
	
	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChart() {
	
		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Topping');
		data.addColumn('number', 'Slices');
		data.addRows([
			['Mushrooms', 3],
			['Onions', 1],
			['Olives', 1],
			['Zucchini', 1],
			['Pepperoni', 2]
		]);

		// Set chart options
		var options = {	'title':'How Much Pizza I Ate Last Night',
						'width':400,
						'height':300};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document.getElementById('chart_div_piechart'));
		chart.draw(data, options);
		
		var chart = new google.visualization.LineChart(document.getElementById('chart_div_linechart'));
		chart.draw(data, options);
		
		var chart = new google.visualization.BarChart(document.getElementById('chart_div_barchart'));
		chart.draw(data, options);
		
		var chart = new google.visualization.ComboChart(document.getElementById('chart_div_combochart'));
		chart.draw(data, options);

	}
	</script>
    
</head>
<body>

	<h1>Jsp에서 Google Chart API 사용하기</h1>

	<!-- 구글 챠트를 그리는 영역 -->
	<table class="columns">
		<tr>
			<td><div id="chart_div_piechart" 	class="chart_div"></div></td>
			<td><div id="chart_div_linechart" 	class="chart_div"></div></td>
			<td><div id="chart_div_barchart" 	class="chart_div"></div></td>
			<td><div id="chart_div_combochart" 	class="chart_div"></div></td>
		</tr>
	</table>
   
    
   
</body>

	<!-- 부트스트랩 스타일 -->
	<style>
	.chart_div" {
		border:		1px solid #CCC;
	}
	</style>


</html>