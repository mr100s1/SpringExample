package com.edu.common.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//-----------------------------------------------------------------------------------------------------------
// 사용자 정의 인터셉터는 반드시 HandlerInterceptorAdapter를 상속 받아야 한다.
//-----------------------------------------------------------------------------------------------------------
public class LocaleInterceptor extends HandlerInterceptorAdapter {

	//-----------------------------------------------------------------------------------------------------------
	// 컨트롤러가 실행되기 전에 호출된다.
	// 요청에 따라 페이지를 한글 또는 영어로 보여준다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		// 브라우저에서 전달한 locale 정보를 가져온다.
		String		locale	= request.getParameter("locale");
		
		if(locale == null) {	// 최초 요청시
			System.out.println("LocaleInterceptor locale 값 없음.....");
			locale = "ko";		// locale을 한국어로 설정한다.
		}
		
		// locale 속성값을 세션에 저장하여 SessionLocaleResolver가 사용할 수 있게 한다.
		session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE",new Locale(locale));

		return true;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 컨트롤러가 실행이 된 후에 호출된다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	//-----------------------------------------------------------------------------------------------------------
	// 뷰(JSP)를 수행한 후에 호출된다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}
	
	

} // End - public class LocaleInterceptor extends HandlerInterceptorAdapter
