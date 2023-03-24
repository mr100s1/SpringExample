package com.edu.vue.student;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;


@CrossOrigin
@RestController
public class BoardVueController {
private static final Logger logger = LoggerFactory.getLogger(BoardVueController.class);
	
	@Inject
	private BoardService boardService;
	
 	//-----------------------------------------------------------------------------------------------------------
	// 게시판 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/Ffaqlist")
	public List<BoardDTO> FaqList() throws Exception {
		
		logger.info("***** ====================================== *****");
		logger.info("BoardVueController FaqList()를 호출하였습니다.....");
		logger.info("***** ====================================== *****");
		
		
		// List<BoardDTO> FaqList = boardService.FaqList(0);
		List<BoardDTO> FaqList = null;
		return FaqList;
		
	} // End - 게시판 목록 가져오기


	
	
}
