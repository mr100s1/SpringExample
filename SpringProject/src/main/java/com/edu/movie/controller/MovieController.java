package com.edu.movie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.movie.dto.SeatDTO;
import com.edu.movie.dto.SeatStatusDTO;
import com.edu.movie.service.MovieService;

//-------------------------------------------------------------------------------------------------
// 영화 좌석 예매
// MovieController에 @Controller 어노테이션이 설정되어 있더라도 스프링에서 해당 패키지를 스캔하지 않으면
// 스프링 빈으로 등록되지 않는다. 그래서 root-context.xml 또는 servlet-context.xml에 Bean으로 등록해야 한다.
//-------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/movie")
public class MovieController {

	//-------------------------------------------------------------------------------------------------
	// @Inject		: Java에서 지원하는 어노테이션. 특정 Framework에 종속적이지 않는다.
	// @Autowired	: Spring에서 지원하는 어노테이션.
	//-------------------------------------------------------------------------------------------------
	@Inject
	MovieService movieService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	//-------------------------------------------------------------------------------------------------
	// 영화관 좌석 화면 가져오기
	// @ModelAttribute("movieID") 는 주소창에서 msg라는 파라미터 값을 가져온다.
	// http://localhost:8080/movie/seatReservation?movieID=1
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/seatReservation", method = RequestMethod.GET)
	public String seatReservation(@ModelAttribute("movieID") String mID, Model model) throws Exception {
		
		logger.info("MovieController 영화관 좌석 화면 가져오기() 시작");
		
		int reserveOK	= 0;	// 예약된  좌석 수
		int	reserveNO	= 0;	// 남은 좌석 수
		int	movieID		= 0;	// 어떤 영화?
		
		if(mID != null) {
			movieID = Integer.parseInt(mID);
		}
		if(movieID < 0) {
			logger.info("MovieController seatReservation() ==> 유효하지 않은 값입니다.");
		} else {
			List<SeatDTO> list = movieService.getSeatList(movieID);
			// 좌석의 예매상태 자료를 만든다.
			ArrayList<SeatStatusDTO> result = new ArrayList<SeatStatusDTO>();
			
			// 영화관의 좌석 수가 200개라고 상정한다.
			// 좌석 수가 변동이 되면 변수를 사용해야 한다.
			// 좌석예약 테이블(RESERVATION_SEAT)에 값이 있으면 true, 아니면 false로 만든다.
			for(int i = 1; i <= 200; i++) {
				boolean findSeatID = false;
				for(int j = 0; j < list.size(); j++) {	// 예약된 개수만큼 반복하여 비교를 한다.
					if(i == list.get(j).getSeatID()) {
						findSeatID = true;
						reserveOK++;	// 예약개수를 증가시킨다.
					}
				}
				if(findSeatID)	result.add(new SeatStatusDTO(i, true));
				else			result.add(new SeatStatusDTO(i, false));
			} // End - 예약된 좌석 검사
			reserveNO = 200 - reserveOK;	// 예약 않된 좌석 수
			
			model.addAttribute("SeatList",		result);
			model.addAttribute("reserveOK",		reserveOK);
			model.addAttribute("reserveNO",		reserveNO);
			logger.info("좌석 상태 => " + result);
		}
		
		return "/movie/seatReservation";
		
	} // End - ublic String seatReservation(@ModelAttribute("movieID") String mID, Model model)

	//-------------------------------------------------------------------------------------------------
	// 좌석 예약 처리하기
	// 좌석 번호를 받아서 데이터베이스에 저장한다.
	// 성공한 데이터 건수를 리턴한다.
	//-------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/seatReservation", method = RequestMethod.POST)
	public int seatReservationOK(@RequestParam int seatID, Model model) throws Exception {
		
		logger.info("MovieController seatReservationOK() 좌석번호 : " + seatID);
		
		int returnCnt = movieService.insertSeatID(seatID);
		// if(movieService.insertSeatID(seatID) == 1) {
		if(returnCnt == 1) {
			// 현재 예약된 좌석수를 알아낸다.
			int reserveCount = movieService.seatReserveCount();
			int reserveOK 	= reserveCount;
			int reserveNO	= 200 - reserveCount;
			model.addAttribute("reserveOK",		reserveOK);
			model.addAttribute("reserveNO",		reserveNO);
			return returnCnt;
		} else {
			return returnCnt;
		}
		
	} // End - public int seatReservationOK(@RequestParam int seatID, Model model)
	
	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	   @RequestMapping(value = "/seatReservationAA", method = RequestMethod.POST)
	   @ResponseBody
	   public HashMap<String, Integer> seatReservationOK(@RequestParam int seatID, @RequestParam String userID, Model model) throws Exception {
	      logger.info("### MovieController seatReservationOK() 좌석 번호: " + seatID);
	      
	      /*
	      int seatNum = 200;
	      
	      HashMap<String, String> seatInfo = new HashMap<String, String>();
	      seatInfo.put("userID", userID);
	      seatInfo.put("seatID", String.valueOf(seatID));
	      
	      HashMap<String, Integer> reservedInfo = new HashMap<String, Integer>();
	      int reserveSeat = movieService.insertSeatInfo(seatInfo);
	      
	      reservedInfo.put("reserveSeat", reserveSeat);
	      if (reserveSeat == 1) {
	         int reservedCnt = movieService.seatReserveCount();
	         logger.info("### MovieController seatReservationOK() 좌석 수: " + reservedCnt);
	         model.addAttribute("reservedOK", reservedCnt);
	         model.addAttribute("reservedNO", seatNum - reservedCnt);
	         
	         reservedInfo.put("reserveOK", reservedCnt);         
	         reservedInfo.put("reserveNO", seatNum - reservedCnt);
	      }
	      
	      return reservedInfo;
	      */
	      return null;
	   }
} // End - public class MovieController





