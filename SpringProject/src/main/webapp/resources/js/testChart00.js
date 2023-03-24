

 		function drawChart( prev) {
 			// alert(prev);
			var data = google.visualization.arrayToDataTable([
	            ['Month', '매출액(원)'],
	           // ['이번달', 9999],			// ['이번달', ${TMBill}],
	           // ['지난달', {prev}]		// ['지난달', ${LMBill}]
        ['2010', 138],
        ['2020', 14],
        ['2030', 360],
        ['2040', 22],
        ['2050', 28]
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
 