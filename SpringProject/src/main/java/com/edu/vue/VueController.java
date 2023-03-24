package com.edu.vue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;

import java.util.List;

import javax.inject.Inject;

//-----------------------------------------------------------------------------------------------------------
//Vue에서 사용하는 게시판관련 컨트롤러
//@RestController = @Controller + @RequestBody
//@RestController를 붙여 놓으면 컨트롤러의 하위 메서드에 @RequestBody 어노테이션을 붙이지 않아도
//문자열과 JSON 데이터 등을 전송할 수 있다.
//-----------------------------------------------------------------------------------------------------------
@CrossOrigin
// @RestController : 이것을 사용하면 페이지 이동이 되지 않는다.
@Controller
public class VueController {
 
	private static final Logger logger = LoggerFactory.getLogger(VueController.class);
	
	@Inject
	private BoardService boardService;
	

 	//-----------------------------------------------------------------------------------------------------------
	// Test
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/test/main")
	public void testMain() throws Exception {
		
		logger.info("테스트 Main 가져오기 ==> ");
		
	} // End - 게시판 목록 가져오기

	@GetMapping("/vueboard/vtest")
	public void vueTest() throws Exception {
		
		logger.info("VueController vueTest 가져오기 ==> ");
		
	} // End - 게시판 목록 가져오기

 	//-----------------------------------------------------------------------------------------------------------
	// Vue 메인화면 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/vueboard/vboardMain")
	public void vueMain() throws Exception {
		
		logger.info("VueController vboardMain 가져오기 ==> ");
		
	} // End - 게시판 목록 가져오기

 	//-----------------------------------------------------------------------------------------------------------
	// Jsp에서 Vue 사용하기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/vueexam/addUp")
	// @RequestMapping("/vueexam/addUp")
	public void addUpUpUpUpUpUpUp() throws Exception {
		
		logger.info("VueController Jsp에서 Vue 사용하기 ==> ");
		
		// return "/vueexam/addUp";
		
	} // End - 게시판 목록 가져오기

 	//-----------------------------------------------------------------------------------------------------------
	// Jsp에서 gChart 사용하기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/google/gChart00")
	public void gChart00() throws Exception {
		
		logger.info("VueController Jsp에서 Google Chart 사용하기 0 ==> ");
		
	} // End - Jsp에서 gChart 사용하기

	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/google/gChart01")
	public void gChart01() throws Exception {
		
		logger.info("VueController Jsp에서 Google Chart 사용하기 1 ==> ");
		
	} // End - Jsp에서 gChart 사용하기

	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/google/jsfiddleChart")
	public void jsfiddleChart() throws Exception {
		
		logger.info("VueController Jsp에서 jsfiddleChart 사용하기 1 ==> ");
		
	} // End - Jsp에서 gChart 사용하기

	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/google/testChart00")
	public void testChart00() throws Exception {
		
		logger.info("VueController Jsp에서 Google Chart 사용하기 1 ==> ");
		
	} // End - Jsp에서 gChart 사용하기

	//-----------------------------------------------------------------------------------------------------------
	// Google Analytics
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/google/helloAnalytics")
	public void helloAnalytics() throws Exception {
		
		logger.info("VueController Jsp에서 Google Analytics 사용하기 1 ==> ");
		
	} // End - Jsp에서 gChart 사용하기




	
}