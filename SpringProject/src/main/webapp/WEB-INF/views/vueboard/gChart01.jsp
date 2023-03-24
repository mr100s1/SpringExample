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

	<!--  
    <script src="https://unpkg.com/vue@v3.1.1"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>	
	-->
<!-- vue 3 -->
<script src="https://unpkg.com/vue@3"></script>

	<!-- 
	<script src="${contextPath}/resources/js/board.js"></script>
	<script src="../../js/vueAddUp.js" type="module"></script>
	 -->

	<!-- 
	<script src="${contextPath}/resources/js/vueAddUp.js" type="module"></script>
	 
	<script type="module" src="<c:url value='/resources/js/vueAddUp.js' />"></script> 
	-->
	<script type="module" src="<c:url value='/resources/js/gChart.js' />"></script> 

</head>
<body>

	<h1>Jsp에서 Vue 사용하기</h1>

  <!-- el태그의 app -->
  <div id="app" class="container py-3">
  	<!-- 
  	-->
  	<topmenu-component></topmenu-component> 
  	
	<!-- Google Chart 컴포넌트 붙여줌 -->
  	 <gchart1-component></gchart1-component>
   </div>
   
   
           <script>
           // 챠트에 보여줄 데이터 - 나중에 DB에서 가져오도록 변경할 것.
            var orgdata = [
                ['종류','갯수'],
                ['도시락',3],
                ['갈비탕',4],
                ['오므라이스',5],
                ['짬뽕',1],
                ['비빔밥',3],
                ['짜장면',2]
            ]
            google.charts.load('current',{packages:['corechart']})
            google.charts.setOnLoadCallback(drawBasic)

            // 챠트 그리기
            function drawBasic(){
                var data = google.visualization.arrayToDataTable(orgdata)
                var options = {title: '선호하는 점심',"is3D" : true}
                
                // Pie 챠트 그리기
                var chart = new google.visualization.PieChart(
                    document.getElementById('chart_div')
                )
                chart.draw(data, options)
            }
        </script>
        <script>
            new Vue({
                el : '#app',
                data:{
                    dataArray:orgdata
                },
                methods:{
                    addOne:function(val){
                        var obj = this.dataArray[val]
                        obj[1]++
                        this.dataArray.splice(val, 1,obj)
                        drawBasic()
                    }
                }
            })
        </script>
   
   
   
</body>

  <!-- 부트스트랩 스타일 -->
  <style>
  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }
	</style>


</html>