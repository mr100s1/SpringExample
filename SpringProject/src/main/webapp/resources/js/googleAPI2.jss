/**
 *  구글 차트 API 스크립트
 */
 
// google.charts.load('current', {'packages':['bar']});
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
				
function drawChart() {

	var TMBill = parseInt(document.getElementById("TMBill").value);
	var LMBill = parseInt(document.getElementById("LMBill").value);
	
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Topping');
		data.addColumn('number', 'Slices');
		data.addRows([
        ['Current', TMBill],	// ['Current', TMBill],
        ['Privous', LMBill],	// ['Privous', LMBill],
		]);

		// Set chart options
		var options = {	'title':'How Much Pizza I Ate Last Night',
						'width':400,
						'height':300};

	var chart = new google.visualization.BarChart(document.getElementById('barchart_values'));
	chart.draw(data, options);

}

google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart2);
	
	function drawChart2() {
	  
		var LMBill_10 = document.getElementById("LMBill_10").value;
		var TMBill_10 = document.getElementById("TMBill_10").value;
	  	var LMBill_20 = document.getElementById("LMBill_20").value;
		var TMBill_20 = document.getElementById("TMBill_20").value;
	  	var LMBill_30 = document.getElementById("LMBill_30").value;
		var TMBill_30 = document.getElementById("TMBill_30").value;
		var LMBill_40 = document.getElementById("LMBill_40").value;
		var TMBill_40 = document.getElementById("TMBill_40").value;
		
	    var data = google.visualization.arrayToDataTable([
	      ['연령대', '지난달', '이번달'],
	      ['10대', LMBill_10, TMBill_10],
	      ['20대', LMBill_20, TMBill_20],
	      ['30대', LMBill_30, TMBill_30],
	      ['40대', LMBill_40, TMBill_40]
	    ]);
	
	    var options = {
	      chart: {
	        title: '연령별 매출 추이',
	        subtitle: '지난달과 이번달 연령별 매출 비교입니다.',
	      },
	      bars: 'horizontal' // Required for Material Bar Charts.
	    };
	
	    var chart = new google.charts.Bar(document.getElementById('barchart_values2'));
	
	    chart.draw(data, google.charts.Bar.convertOptions(options));
	  }
