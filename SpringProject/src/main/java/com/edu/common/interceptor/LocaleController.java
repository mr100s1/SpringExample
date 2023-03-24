package com.edu.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//-----------------------------------------------------------------------------------------------------------
// 컨트롤러는 요청에 대해서 JSP 이름만 뷰리졸버로 반환한다.
//-----------------------------------------------------------------------------------------------------------
@Controller("localeController")
public class LocaleController {

	//-----------------------------------------------------------------------------------------------------------
	// public String locale(HttpServletRequest request, HttpServletResponse response)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/test/locale.do", method = {RequestMethod.GET})
	public String locale(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("localeController locale() 시작");
		
		return "locale";	// 컨트롤러는 뷰 이름만 반환한다.
		
	} // End - public String locale(HttpServletRequest request, HttpServletResponse response)

} // End - public class LocaleController






