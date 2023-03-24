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
         <link href="/resources/css/menu.css" rel="stylesheet" type="text/css">   
         <link href="/resources/css/product.css" rel="stylesheet" type="text/css">
         <script src="/resources/js/product.js"></script>   
         <script src="/resources/js/review.js"></script>
         <!-- 구글 차트 api -->   
         <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
         <script src="/resources/js/googleAPI.js"></script>
         <script>
         drawChart();
         drawChart2();
         </script>
      </head>
      <body>
         <!-- 상단 메뉴 -->
         


<!-- 로그인 확인용 -->
<script src="/resources/js/isLogOn.js"></script>
<div>
   <input type="hidden" id="isLogOnT" value="true"/>
   <input type="hidden" id="userIDT" value="hong"/>
</div>

<div class="navbar" id="myNav">
   <!-- 로고 네브바  -->
   <div class="container-fluid" id="navbar1">
      <a href="/main.do"><strong>A N D E W</strong></a>
   </div>   

   <!-- 로그인 네브바 -->
   <div class="container-fluid">
      <div class="collapse navbar-collapse navbar-right" id="navbar2">
         
            
               <ul class="nav navbar-nav"  id="navbar2Ul">
                  <li>
                     <p class="navbar-text"><b>홍길동님, 환영합니다.&nbsp;&nbsp;</b></p>
                  </li>
                  <li>
                     <a href="/member/logout.do" class="btn btn-light">
                        <span class="glyphicon glyphicon-log-out"></span> 로그아웃
                     </a>
                  </li>
                  <li class="dropdown">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user" ></span> 마이페이지 <span class="caret"></span>
                     </a>
                     <ul class="dropdown-menu">
                        <li>
                           <a href="/member/mypageForm.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 회&nbsp; 원&nbsp; 정&nbsp; 보 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        </li>
                        <li>
                           <a href="/member/myOrderPage?userID=hong" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 활&nbsp; 동&nbsp; 내&nbsp; 역 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        </li>
                        <!-- 관리자 로그인시 보이는 부분 -->
                        
                           
                              <li>
                                 <a href="/member/listMembers.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 회&nbsp; 원&nbsp; 목&nbsp; 록 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                              </li>
                              <li>
                                 <a href="/order/orderManagement">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 운&nbsp; 영&nbsp; 관&nbsp; 리 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                              </li>
                           
                        
                     </ul>
                  </li>
               </ul>
            
            
         
      </div>
   </div>
   
   <!-- 창이 작아졌을 때 생기는 버튼 부분 -->
   <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
      </button>
   </div>
   
   <!-- 메뉴 부분 -->
   <nav class="navbar" id="navbar3">
         <!-- 실질적인 메뉴를 나열한다. -->
         <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
               <li class="dropdown menu2Li">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#" >&nbsp;&nbsp;&nbsp;&nbsp; ANDEW &nbsp;&nbsp;&nbsp;&nbsp;</a>
                  <ul class="dropdown-menu">
                     <li><a href="/other/conceptForm.do">CONCEPT</a></li>
                  </ul>
               </li>
               <li class="dropdown menu2Li">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; Product &nbsp;&nbsp;&nbsp;&nbsp; </a>
                  <ul class="dropdown-menu">
                     <li><a href="/product/productTypeList?product_type=top&page=1&array_type=r">Top</a></li>
                     <li><a href="/product/productTypeList?product_type=bottom&page=1&array_type=r">Bottom</a></li>
                     <li><a href="/product/productTypeList?product_type=outer&page=1&array_type=r">Outer</a></li>
                     <li><a href="/product/productTypeList?product_type=acc&page=1&array_type=r">Acc</a></li>
                  </ul>
               </li>
               <li class="dropdown menu2Li">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; StyleLife &nbsp;&nbsp;&nbsp;&nbsp;</a>
                  <ul class="dropdown-menu">
                     <li><a href="/other/styleLifeForm.do">News & Event</a></li>
                  </ul>
               </li>
               <li class="dropdown menu2Li">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; Customer Service &nbsp;&nbsp;&nbsp;&nbsp;</a>
                  <ul class="dropdown-menu">
                     <li><a href="/board/boardList?page=1">&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp; & &nbsp;&nbsp;&nbsp; A&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                     <li><a href="/other/useForm.do">&nbsp;&nbsp;&nbsp;&nbsp;이 용 약 관&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                     <li><a href="/other/informationForm.do">&nbsp;&nbsp;&nbsp;&nbsp;개 인 정 보 취 급 방 침&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                  </ul>
               </li>
               <li>
                  <a href = "/product/searchForm?product_type=outer&page=1&array_type=r"><span class = "glyphicon glyphicon-search" >&nbsp;&nbsp;</span></a>
               </li>
               <li>
                  <a href = "#" id="isLogOnTA" onclick="fn_isLogOnT();"><span class = "glyphicon glyphicon-shopping-cart">&nbsp;&nbsp;</span></a>
               </li>
               
            </ul>
         </div>   
      
   </nav>
</div>
         
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
                  <div id="barchart_values2" style="width: 100%; height: 300px;"></div>
                  <div>
                     <input type="hidden" id="TMBill" value="459889"/>
                     <input type="hidden" id="LMBill" value="265000"/>
                     <input type="hidden" id="LMBill_10" value="160000"/>
                     <input type="hidden" id="LMBill_20" value="105000"/>
                     <input type="hidden" id="LMBill_30" value="0"/>
                     <input type="hidden" id="LMBill_40" value="0"/>
                     <input type="hidden" id="TMBill_10" value="254889"/>
                     <input type="hidden" id="TMBill_20" value="205000"/>
                     <input type="hidden" id="TMBill_30" value="0"/>
                     <input type="hidden" id="TMBill_40" value="0"/>
                  </div>
               </div>
               
               
            </div>
         </div>
      </body>
   </html>
	