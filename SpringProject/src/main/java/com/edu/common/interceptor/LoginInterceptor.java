package com.edu.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//-----------------------------------------------------------------------------------------------------------
// 브라우저의 요청을 해당 컨트롤러의 메서드가 처리하기 전후에 인터셉터를 두어 특정한 작업을 실행한다.
// 인터셉터는 필터와 비슷한 기능을 하지만,
// 필터는 웹 애플리케이션의 특정한 위치에서만 동작하는데 반해서
// 인터셉터는 좀 더 자유롭게 위치를 변경해서 기능을 수행할 수 있다.
// 애플리케이션에서 필터와 인터셉터가 동시에 적용되면 필터 기능이 먼저 수행된 후에 인터셉터 기능이 수행된다.
//-----------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------
// redirect : 특정 조건에 만족하지 않으면 특정 페이지로 이동하는 것.
// ==> 회원 관련 페이지에서 로그인이 되어 있지 않다면 메인 페이지 또는 로그인 페이지로 이동시키는 것.
// *Controller에서 매 요청마다 로그인을 체크하고 redirect를 하는 것은 효율적이지 못하다.
// 이렇게 redirect를 사용해야 하는 경우가 많을 때 인터셉터, 명확하게는 HandlerInterceptor를 이용한다.
//
// HandlerInterceptor는 interface로 3가지 method를 포함하고 있다.
// preHandle()		: Controller가 작동 되기 전
// postHandle()		: Controller가 작업을 마친 후
// afterCompletion(): Controller, View가 모두 작업을 마친 후
//
// * 기존에 클라이언트의 요청이 작동되던 순서
// ==> Request -> DispatcherServlet -> Handler(Controller) -> View -> Response
//
// * 인터셉터를 적용했을 때 요청이 작동되는 순서
//   (해당 interface에 있는 3가지 메서드를 모두 적용시킨다는 가정하에서) 
// ==> Request -> DispatcherServlet
//     -> preHandle -> Handler(Controller) -> postHandle -> View -> afterCompletion() -> Response
//-----------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------
// public class LoginInterceptor
//-----------------------------------------------------------------------------------------------------------
@SuppressWarnings("deprecation")
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	//-----------------------------------------------------------------------------------------------------------
	// preHandle
	// 전처리기는 클라이언트에서 컨트롤러로 요청할 때 가로채는 것이다.
	// 즉, 컨트롤러가 호출되기 전에 실행되는 메서드이다.
	//-----------------------------------------------------------------------------------------------------------
	// 컨트롤러가 작동되기 전에 세션이 있는지 검사하는 메서드
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("==========================================================================");
		logger.info("LoginInterceptor preHandle 시작");
		logger.info("==========================================================================");
		
		// 세션 객체를 가져온다.
		HttpSession session = request.getSession();
		
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져온다.
		Object obj = session.getAttribute("member");

		if(obj == null) {
			logger.info("LoginInterceptor preHandle 세션이 없습니다.");
			
			// [해결1]의 alert창에서 한글이 깨지지 않게 하기 위해서 설정한다.
			response.setContentType("text/html;charset=UTF-8");
			
			// java.lang.IllegalStateException: 응답이 이미 커밋된 후에는, sendRedirect()를 호출할 수가 없다.
			// [해결1] location.replace('/member/loginForm.do'); 를 사용하면 된다.
			PrintWriter printWriter = response.getWriter();
			printWriter.print("<script>alert('LoginIntercepter 메시지 - 먼저 로그인을 해주십시오');location.replace('/member/loginForm.do');</script>");
			printWriter.flush();
			printWriter.close();
			
			// 더 이상 컨트롤러의 요청으로 가지 않도록 false로 반환한다. 		
			return false;
		}

		// preHanle의 return은 컨트롤러의 요청 uri로 가도 되냐? 않되냐?를 허가하는 의미이다.
		// 따라서 return 값을 true로 하면 컨트롤러의 uri로 가게 된다.
		return true;
	}

	//-----------------------------------------------------------------------------------------------------------
	// postHandle : 컨트롤러가 실행이 되고 화면이 보여지기 직전에 수행되는 메서드
	// 후처리기는 컨트롤러에서 클라이언트로 요청할 때 가로채는 것이다.
	// 즉, 컨트롤러가 호출되고 난 후에 실행되는 메서드이다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info("==========================================================================");
		logger.info("LoginInterceptor postHandle 시작");
		logger.info("==========================================================================");
		
		super.postHandle(request, response, handler, modelAndView);
	}

	//-----------------------------------------------------------------------------------------------------------
	// afterCompletion : 컨트롤러와 화면이 모두 실행된 후에 실행되는 메서드
	// 컨트롤러의 처리가 끝나고 화면처리까지 모두 끝나면 실행되는 메서드이다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		logger.info("==========================================================================");
		logger.info("LoginInterceptor afterCompletion 시작");
		logger.info("==========================================================================");
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	

} // End - public class LoginInterceptor




