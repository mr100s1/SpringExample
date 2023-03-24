<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current');   // Don't need to specify chart libraries!
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        var wrapper = new google.visualization.ChartWrapper({
          chartType: 'ColumnChart',
          dataTable: [['', 'Germany', 'USA', 'Brazil', 'Canada', 'France', 'RU'],
                      ['', 700, 300, 400, 500, 600, 800]],
          options: {'title': 'Countries'},
          containerId: 'vis_div'
        });
        wrapper.draw();
      }
    </script>

</head>
<body style="font-family: Arial;border: 0 none;">

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

	<!-- 구글 챠트 종류 선택 -->
	<select id="chartType">
		<option>검색종류</option>
		<option value="s" <c:if test="${chartType == 's'}">selected</c:if>>제목</option>
		<option value="c" <c:if test="${chartType == 'c'}">selected</c:if>>내용</option>
		<option value="w" <c:if test="${chartType == 'w'}">selected</c:if>>글쓴이</option>
	</select>
	<!-- 구글 챠트를 그리는 영역 -->
	<div id="vis_div" style="width: 600px; height: 400px;"></div>


<script>
var myParent = document.body; 

//Create array of options to be added 
var array = ["Volvo","Saab","Mercades","Audi"];

//Create and append select list 
var selectList = document.createElement("select"); 

selectList.id = "mySelect"; 
myParent.appendChild(selectList); 

//Create and append the options 
for (var i = 0; i < array.length; i++) { 
	var option = document.createElement("option");
	option.value = array[i];
	option.text = array[i];
	selectList.appendChild(option); 
}
</script>

<script>
loadSelect();

var basicData = new Array(
	      ['http://naver.com','네이버','1'],
	      ['http://daum.net','다음','2'],
	      ['http://nate.com','네이트','3'],
	      ['http://yahoo.com','야후','4']
);
	                         
function loadSelect() {
	var htm = "";

	for (var x=0;x < basicData.length; x++)	
	{
		htm += "<option value='" + basicData[x][2] + "' korname='" + basicData[x][1] + "' url='" + basicData[x][0] + "'>" + basicData[x][1] + "</option>";
	}
	$("#selsite").html(htm);
	
	dispView();
}

function dispView() {
	alert("dispView() 시작.....");
	var korname=$("#selsite option:selected").attr('korname');
	var url=$("#selsite option:selected").attr('url');
	var htm = "";

	htm = "선택 정보 <div>이름 : " + korname + "</div>";
	htm += "<div>URL : " + url + "</div>";

	$("#disp_msg").html(htm);
}

function editAttr() {
	var old = $("#selsite option:selected").attr('korname');
	var htm = "";

	$("#selsite option:selected").attr({'korname':'변경완료'});

	htm = "값 변경 [" + old + "] -> [변경완료]";

	$("#edit_msg").html(htm);
}

</script>


<select name="selsite" id="selsite" onchange="dispView();">
</select>

<input type="button" value="선택박스 설정" onclick="loadSelect()">

<input type="button" value="선택 속성값 변경" onclick="editAttr()">

<div id="disp_msg"></div>
<div id="edit_msg"></div>


</body>
</html>


