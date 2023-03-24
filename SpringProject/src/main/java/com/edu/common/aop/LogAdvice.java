package com.edu.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//-----------------------------------------------------------------------------------------------------------
// Aspect		: 흩어진 관심사를 모듈화 한 것.
// Target		: Aspect를 적용하는 곳. 클래스, 메서드.....
// Advice		: 실질적으로 어떤 일을 해야 할 지에 대한 것. 실질적인 부가기능을 담은 구현체.
// Join Point	: Advice가 적용될 위치 또는 끼어들 수 있는 시점.
//				  메서드 진입 시점, 생성자 호출 시점, 필드에서 꺼내올 시점 등 끼어들 시점을 의미.
//				  스프링에서 Join Point는 언제나 메서드의 실행 시점을 의미한다.
// Point Cut	: Join Point의 상세한 스펙을 정의한 것.
//				  "Login 이란 메서드의 진입 시점에 호출할 것"처럼 구체적으로 Advice가 실행될 시점을 정한다.
//-----------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------
// public class LogAdvice
// @Ascpect		: 해당 클래스 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용한다.
// @Component	: AOP와 관계는 없지만 스프링에서 Bean으로 인식하기 위해서 사용한다.
//-----------------------------------------------------------------------------------------------------------
@Aspect
@Component
public class LogAdvice {

	private static Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// @Before			: 타겟 메서드가 실행하기 이전에 어드바이스 기능을 수행한다.
	// @After			: 타겟 메서드의 결과에 상관없이 실행 후에 어드바이스 기능을 수행한다.
	// @AfterReturning	: 타겟 메서드가 정상적으로 결과를 반환한 후에 어드바이스 기능을 수행한다.
	// @AfterThrowing	: 타겟 메서드가 수행 중 예외가 발생하면 어드바이스 기능을 수행한다.
	// @Around			: 어드바이스가 타겟 메서드를 감싸서 타겟 메서드 호출 전, 후에 어드바이스를 실행한다.
	//-----------------------------------------------------------------------------------------------------------
	// execution(int minus(int, int))	: int  타입의 리턴 값, minus 메서드, 두개의 int파라미터를 가지고 있는 메서드
	// execution(*	 minus(int, int))   : 모든 타입의 리턴 값, minus 메서드, 두개의 int파라미터를 가지고 있는 메서드
	// execution(*   minus(..))	: 리턴 타입, 파라미터 종류, 개수에 상관없이 minus라는 메서드의 이름을 가진 모든 메서드
	// execution(*   minus())	: 리턴 타입은 상관없이 파라미터가 존재하지 않는 minus메서드.
	// execution(* *(..))		: 리턴 타입, 메서드 이름, 파라미터 이름에 상관없이 모든 조건을 허용하는 포인트 컷 표현식.
	// execution(* com.edu.aop.Target.*(..))	: com.edu.aop 패키지의 Target 클래스에 존재하는 메서드. 
	// execution(* com.edu.aop.*.*(..))			: com.edu.aop 패키지내에 존재하는 모든 클래스.
	//-----------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------
	// @Around("execution(* com.edu.board.service.BoardService*.*(..))")
	// execution..	: AspectJ의 표현식
	// execution	: 접근 제한자와 특정 클래스의 메서드를 지정할 수 있다.
	// 맨 앞의 *	: 리턴 타입을 의미한다.
	// 맨 뒤의 *	: 클래스의 이름과 메서드의 이름을 의미한다.
	//-----------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------
	// @Before
	//-----------------------------------------------------------------------------------------------------------
	// @Before("execution(* com.edu.board.service.*.*(..))")
	public void beforeComponentMethod(JoinPoint jp) {
		
		logger.info("================================================================");
		logger.info("AOP Before 입니다.");
		logger.info("beforeComponentMethod ==> " + jp.getSignature());
		logger.info("================================================================");
	}

	//-----------------------------------------------------------------------------------------------------------
	// @After
	//-----------------------------------------------------------------------------------------------------------
	// @After("execution(* com.edu.board.service.*.*(..))")
	public void afterComponentMethod(JoinPoint jp) {
		
		logger.info("================================================================");
		logger.info("AOP After 입니다.");
		logger.info("afterComponentMethod ==> " + jp.getSignature());
		logger.info("================================================================");
	}

	//-----------------------------------------------------------------------------------------------------------
	// @Around : 핵심업무에 걸린 시간을 조사하자!
	// 모든 패키지 내의 controller, service, dao package에 존재하는 모든 클래스
	//-----------------------------------------------------------------------------------------------------------
	@Around("execution(* *..controller.*.*(..)) or execution(* *..service.*.*(..)) or execution(* *..dao.*.*(..))")
	public Object calculateExecutionTIme(ProceedingJoinPoint pjp) throws Throwable {

		logger.info("================================================================");
		logger.info("AOP Around Before 입니다.");
		logger.info("================================================================");

		// 해당 클래스의 처리 전 시간을 알아낸다.
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 핵심업무
		Object result = pjp.proceed();
		
		// 해당 클래스의 처리가 모두 끝나면, StopWatch를 멈춘다.
		sw.stop();
		long executionTime = sw.getTotalTimeMillis();
		
		String	className	= pjp.getTarget().getClass().getName();
		String	methodName	= pjp.getSignature().getName();
		String	task		= className + "." + methodName;
		
		logger.info("================================================================");
		logger.info("[업무처리소요시간] " + task + " ==> " + executionTime + "(ms)");
		logger.info("================================================================");
		
		return result;
		
	}
	
	
	
} // End - public class LogAdvice














