package com.edu.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
public class VueBoardController {
 
	private static final Logger logger = LoggerFactory.getLogger(VueBoardController.class);
	
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
	@GetMapping("/vboard/{bno}")
	public BoardDTO boardDetail(@PathVariable int bno) throws Exception {

		logger.info("VueBoardController boardDetail() bno ==> " + bno);

		return boardService.boardDetail(bno, 1);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/vboard")
	// request 객체를 통해 데이터를 가져온다.
	// 메서드의 파라미터에 객체를 전달하면 자동으로 데이터가 set가 된 상태로 전달하게 된다.
	public String create(@RequestBody BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 등록하기.....");
		logger.info("BoardDTO 값 : " + boardDTO);
		// BoardDTO 값 : BoardDTO(bno=0, subject=길동이의 일기, content=소풍가는 날, writer=희동이, reg_date=null, readCount=0)
		
		if(boardService.boardRegister(boardDTO) == 1) { // 게시글 등록 완료
			return "Y";
		} else {	// 게시글 등록 실패
			return "N";
		}
		
	} // End - public String boardRegister(BoardDTO boardDTO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	//// @PostMapping(value = "/vboard")
	// request 객체를 통해 데이터를 가져온다.
	// 메서드의 파라미터에 객체를 전달하면 자동으로 데이터가 set가 된 상태로 전달하게 된다.
	public String boardRegister(@RequestBody BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 등록하기.....");
		logger.info("BoardDTO 값 : " + boardDTO);
		// BoardDTO 값 : BoardDTO(bno=0, subject=길동이의 일기, content=소풍가는 날, writer=희동이, reg_date=null, readCount=0)
		
		if(boardService.boardRegister(boardDTO) == 1) { // 게시글 등록 완료
			return "Y";
		} else {	// 게시글 등록 실패
			return "N";
		}
		
	} // End - public String boardRegister(BoardDTO boardDTO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정
	// 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@PatchMapping(value = "/vboard")
	public String boardUpdate(@RequestBody BoardDTO boardDTO) throws Exception {
		System.out.println("BoardController boardUpdate() BoardDTO : " + boardDTO);
		if(boardService.boardUpdate(boardDTO) == 1) {
			return "Y";
		}else {
			return "N";
		}
	}
	
 	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/vboard/{bno}")
	public void delete(@PathVariable int bno) throws Exception {
		System.out.println("BoardController boardDelete() bno : " + bno);

		boardService.boardDelete(bno);
	}

	
}