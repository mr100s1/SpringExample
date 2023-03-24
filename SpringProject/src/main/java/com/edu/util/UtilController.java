package com.edu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//-----------------------------------------------------------------------------------------------------------
// public class UtilController
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/util")
public class UtilController {

	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// 주소 검색 (Daum API)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/zipcode/address", method = RequestMethod.GET)
	public String address() throws Exception {
		logger.info("UtilController 주소 검색 화면 불러오기");
		return "/util/zipcode/address";
	}

	//-----------------------------------------------------------------------------------------------------------
	// 날짜 선택
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/datepicker/datepicker", method = RequestMethod.GET)
	public String datepicker() throws Exception {
		logger.info("UtilController 날짜 선택하기 화면 불러오기");
		return "/util/datepicker/datepicker";
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// Table에서 선택한 Row와 Column값 알아내기 1
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value ="/selectrow/selectRow1", method = RequestMethod.GET)
	public void selectRow1() {
		logger.info("UtilController Table에서 선택한 Row와 Column값 알아내기 1() 시작");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// Table에서 선택한 Row와 Column값 알아내기 2
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value ="/selectrow/selectRow2", method = RequestMethod.GET)
	public void selectRow2() {
		logger.info("UtilController Table에서 선택한 Row와 Column값 알아내기 2() 시작");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// public String message01 - JSTL 다국어 기능
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/message/message01.do", method = RequestMethod.GET)
	public String message01() {
		logger.info("UtilController JSTL 다국어 기능() 시작");
		return "/util/message/message01";
	}
	

} // End - public class UtilController







