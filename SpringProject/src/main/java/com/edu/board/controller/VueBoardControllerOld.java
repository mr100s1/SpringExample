package com.edu.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;

//-----------------------------------------------------------------------------------------------------------
// Vue에서 사용하는 게시판관련 컨트롤러
// @RestController = @Controller + @RequestBody
// @RestController를 붙여 놓으면 컨트롤러의 하위 메서드에 @RequestBody 어노테이션을 붙이지 않아도
//   문자열과 JSON 데이터 등을 전송할 수 있다.
//-----------------------------------------------------------------------------------------------------------
//@CrossOrigin
//@RestController
public class VueBoardControllerOld {
	
	private static final Logger logger = LoggerFactory.getLogger(VueBoardControllerOld.class);
	
	@Inject
	private BoardService boardService;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시판 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/vboard/list")
	public List<BoardDTO> boardList() throws Exception {
		
		List<BoardDTO> boardList = boardService.boardList();
		return boardList;
		
	} // End - 게시판 목록 가져오기

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 정보를 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@GetMapping("/vboard/detail/{bno}")
	public BoardDTO boardDetail(@PathVariable int bno) throws Exception {
		return boardService.boardDetail(bno, 1);
	}
	

} // End - public class VueBoardController







