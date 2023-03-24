
export default{
	template : `
	<header>
	
	
    <div class="d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom">
		<!--
		<a href="#" class="d-flex align-items-center text-dark text-decoration-none">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" class="me-2" viewBox="0 0 118 94" role="img"><title>Bootstrap</title><path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z" fill="currentColor"></path></svg>
			<span class="fs-4" @click="alertMain()">Home</span>
		</a>

	      <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
	        <a class="me-3 py-2 text-dark text-decoration-none" href="#">Features</a>
	        <a class="me-3 py-2 text-dark text-decoration-none" href="#">Enterprise</a>
	        <a class="me-3 py-2 text-dark text-decoration-none" href="#">Support</a>
	        <a class="py-2 text-dark text-decoration-none" href="#">Pricing</a>
	      </nav>
		-->
		
		<nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto navbar navbar-expand-lg navbar-dark bg-primary">
			<div class="container-fluid">
			    <a class="navbar-brand" href="#">Navbar</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="#">Home</a>
				 		</li>
				        <li class="nav-item">
				          <a class="nav-link" href="#">Link</a>
				        </li>
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	기본프로그램
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/exam/exam1/doA">doA</a></li>
								<li><a class="dropdown-item" href="/exam/exam1/doB">doB</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/exam/exam2/doC">doC</a></li>
								<li><a class="dropdown-item" href="/exam/exam2/gugudanForm.do">구구단</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/exam/exam3/doD">회원정보 (객체)</a></li>
								<li><a class="dropdown-item" href="/exam/exam3/doE">회원정보 (Map)</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/util/zipcode/address">주소검색 (Daum API)</a></li>
								<li><a class="dropdown-item" href="/util/datepicker/datepicker">날짜 선택</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/util/selectrow/selectRow1">Row와 Column 선택</a></li>
								<li><a class="dropdown-item" href="/util/selectrow/selectRow2">Row와 Column 선택</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/test/locale.do">인터셉터</a></li>
					            <li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="/util/message/message01.do">JSTL 다국어 기능</a></li>
							</ul>
				        </li>
	
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	회원 관리
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/member/loginForm.do">로그인</a></li>
								<li><a class="dropdown-item" href="/member/memberForm.do">회원가입</a></li>
								<li><a class="dropdown-item" href="/member/registerAjaxForm.do">회원가입 (Ajax)</a></li>
								<li><a class="dropdown-item" href="/member/listMembers.do">회원목록</a></li>
							</ul>
				        </li>
	
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	게시글 관리
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/board/boardRegisterForm">게시글 쓰기</a></li>
								<li><a class="dropdown-item" href="/board/boardList">게시글 전체목록</a></li>
								<li><a class="dropdown-item" href="/board/boardList1">게시글 전체목록 (Paging 1)</a></li>
								<li><a class="dropdown-item" href="/board/boardList2">게시글 전체목록 (Paging 2)</a></li>
								<li><a class="dropdown-item" href="/board/boardList3">게시글 전체목록 (Paging 3 + 검색)</a></li>
							</ul>
				        </li>
	
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	파일 올리기
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/util/upload/uploadForm">파일 올리기 (Form)</a></li>
								<li><a class="dropdown-item" href="/util/upload/uploadAjax">파일 올리기 (Ajax)</a></li>
							</ul>
				        </li>
	
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	영화관 좌석 예약
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/movie/seatReservation?movieID=1">영화관 좌석 예약</a></li>
							</ul>
				        </li>
				        
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				            	Vue 데이터 관리
							</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/board/boardListVue">게시글 목록 (Vue)</a></li>
								<li role="presentation" class="divider"></li>
								<li><a class="dropdown-item" href="/vueexam/addUp">Jsp에서 Vue 사용하기</a></li>
								<li role="presentation" class="divider"></li>
								<li><a class="dropdown-item" href="/googleChart/gChart00">Google Chart 0</a></li>
								<li><a class="dropdown-item" href="/googleChart/gChart01">Google Chart 1</a></li>
								<li role="presentation" class="divider"></li>
								<li><a class="dropdown-item" href="/vboard/vboardList">게시글 목록(Vue)</a></li>
							</ul>
				        </li>
				        
						<li class="nav-item">
							<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
						</li>
			    	</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>



    </div>

    <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
      <h1 class="display-4 fw-normal">JSP에서 Vue 생성하여 상단 메뉴 불러오기</h1>
      <p class="fs-5 text-muted">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It’s built with default Bootstrap components and utilities with little customization.</p>
      <p><h3>상단 메뉴 (topMenuComponent.js) 끝</h3></p>
    </div>
    
	<hr/>
	

    
  </header>
	`,
	methods : {
		alertMain : function(){
			alert("메인로고 입니다.");
		}
	}
}
