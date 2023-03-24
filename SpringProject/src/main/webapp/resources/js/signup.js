

// 가입부분 체크
function signUpCheck(){

	alert("스크립트 테스트 시작.....");

  let id = document.getElementById("userId").value
  alert("아이디 => " + id);
  
  // 아이디확인
  if(id === ""){
    document.getElementById("idError").innerHTML="아이디는 필수입력 항목입니다"
    check = false
  }else{
    document.getElementById("idError").innerHTML=""
  }

  
  let name = document.getElementById("userName").value
  let pwd = document.getElementById("userPwd").value
  let rePwd = document.getElementById("rePwd").value
  let email = document.getElementById("userEmail").value
  let gender_man = document.getElementById("gender_man").checked
  let gender_woman = document.getElementById("gender_woman").checked
  let check = true;

  // 이메일확인
  if(email.includes('@')){
    let emailId = email.split('@')[0]
    let emailServer = email.split('@')[1]
    if(emailId === "" || emailServer === ""){
      document.getElementById("emailError").innerHTML="이메일은 필수입력 항목입니다"
      check = false
    }
    else{
      document.getElementById("emailError").innerHTML=""
    }
  }else{
    document.getElementById("emailError").innerHTML="이메일이 올바르지 않습니다."
    check = false
  }
  
  
  // 이름확인
  if(name===""){
    document.getElementById("nameError").innerHTML="이름은 필수입력 항목입니다"
    check = false
  }else{
    document.getElementById("nameError").innerHTML=""
  }
  
  
  // 비밀번호 확인
  if(pwd !== rePwd){
    document.getElementById("re-pwdError").innerHTML="비밀번호가 동일하지 않습니다."
    check = false
  }else if(pwd===""){
    document.getElementById("pwdError").innerHTML="비밀번호를 입력해주세요."
    check = false
  }else if(rePwd===""){
    document.getElementById("re-pwdError").innerHTML="비밀번호를 입력해주세요."
    check = false
  }else{
    document.getElementById("pwdError").innerHTML=""
    document.getElementById("re-pwdError").innerHTML=""
  }



  // 비밀번호 확인
  // if(pwd !== rePwd){
  //   document.getElementById("pwdError").innerHTML=""
  //   document.getElementById("re-pwdError").innerHTML="비밀번호가 동일하지 않습니다."
  //   return false;
  // }else{
  //   document.getElementById("pwdError").innerHTML=""
  //   document.getElementById("re-pwdError").innerHTML=""
  // }
  
  // if(pwd===""){
  //   document.getElementById("pwdError").innerHTML="비밀번호를 입력해주세요."
  //    return false;
  // }else{
  //   document.getElementById("pwdError").innerHTML=""
  // }
  // if(rePwd===""){
  //   document.getElementById("re-pwdError").innerHTML="비밀번호를 다시 입력해주세요."
  //    return false;
  // }else{
  //   document.getElementById("re-pwdError").innerHTML=""
  // }
  
  
  // 성별체크확인
  if(!gender_man && !gender_woman){
    document.getElementById("genderError").innerHTML="성별을 선택해주세요."
    check = false
  }else{
    document.getElementById("genderError").innerHTML=""
  }
  
  if(check){
    document.getElementById("idError").innerHTML=""
    document.getElementById("emailError").innerHTML=""
    document.getElementById("nameError").innerHTML=""
    document.getElementById("pwdError").innerHTML=""
    document.getElementById("re-pwdError").innerHTML=""
    document.getElementById("genderError").innerHTML=""
    
    //비동기 처리이벤트
    setTimeout(function() {
      alert("가입이 완료되었습니다.")
      },0);
    }
  }












// 약관동의 모두선택
function selectAll(selectAll)  {
  let checkboxes 
      = document.getElementsByName('agree');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}
 