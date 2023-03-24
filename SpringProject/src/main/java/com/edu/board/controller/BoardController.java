package com.edu.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;
import com.edu.common.util.Criteria;
import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//-----------------------------------------------------------------------------------------------------------
// 게시글 관리 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller		// Bean의 대상으로 인식시키기 위해서 servlet-context.xml에 등록한다.
@RequestMapping(value = "/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@Inject
	private	BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardRegisterForm", method = RequestMethod.GET)
	public String boardRegisterForm() throws Exception {
		
		logger.info("BoardController 게시글 화면 불러오기() 시작");
		return "/board/boardRegisterForm";
		
	} // End - 게시글 화면 불러오기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
	// request 객체를 통해 데이터를 가져온다.
	// 메서드의 파라미터에 객체를 전달하면 자동으로 데이터가 set가 된 상태로 전달하게 된다.
	public String boardRegister(BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 등록하기() 시작");
		logger.info("BoardDTO 값 : " + boardDTO);
		
		if(boardService.boardRegister(boardDTO) == 1) {	// 게시글 등록 완료
			return "Y";
		} else {	// 게시글 등록 실패
			return "N";
		}
		
	} // End - 게시글 등록하기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public void  boardList(Locale locale, Model model) throws Exception {
		
		logger.info("BoardController 게시글 목록 보기() 시작");
		
		List<BoardDTO> boardList = boardService.boardList();
		logger.info("BoardController 게시글 목록 ==> " + boardList);
		
		// 게시글 목록을 model에 담는다.
		model.addAttribute("boardList", boardList);
		
	} // End - 게시글 목록 보기
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세 조회 (수정)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("BoardController 게시글 상세 조회 " + Integer.parseInt((String) request.getParameter("bno")));
		
		int bno		= Integer.parseInt((String)request.getParameter("bno"));
		int flag	= Integer.parseInt((String)request.getParameter("flag"));	
		
		// 게시글 번호에 해당하는 게시글의 정보를 가져온다.
		BoardDTO boardDTO = boardService.boardDetail(bno, flag);
		model.addAttribute("boardDetail", boardDTO);
		return "/board/boardDetail";
		
	} // End - 게시글 상세 조회()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public String boardDelete(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("BoardController 게시글 번호에 해당하는 게시글 삭제하기 " + 
					request.getParameter("bno"));
		
		if(boardService.boardDelete(Integer.parseInt((String)request.getParameter("bno"))) == 1) {
			return "Y";
		} else {
			return "N";
		}
		
	} // End - 게시글 번호에 해당하는 게시글 삭제하기()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정화면 불러오기 - 조회수가 증가되면 않된다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.POST)
	public String boardUpdateForm(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("BoardController 게시글 수정화면 불러오기() 시작");
		
		int bno		= Integer.parseInt((String)request.getParameter("bno"));
		int flag	= Integer.parseInt((String)request.getParameter("flag"));	
		
		// 게시글 번호에 해당하는 정보를 수정하기 위한 데이터를 가져온다. - 조회수가 증가되면 않된다.
		// BoardDTO boardDTO = boardDAO.boardDetail(Integer.parseInt((String)request.getParameter("bno")));
		BoardDTO boardDTO = boardService.boardDetail(bno, flag);
		
		model.addAttribute("boardDetail", boardDTO);
		return "/board/boardUpdate";
		
	} // End - 게시글 수정화면 불러오기

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(Model model, BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기() 시작");
		
		if(boardService.boardUpdate(boardDTO) == 1) {
			return "Y";
		} else {
			return "N";
		}
		
	} // End - 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기 (Paging 1 처리)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList1", method = RequestMethod.GET)
	public ModelAndView boardList1(Model model, @RequestParam(defaultValue="1") int pageNum, @RequestParam(defaultValue="10") int pageSize) throws Exception {
		
		logger.info("BoardController 게시글 목록 보여주기 (Paging 1 처리) 시작");
		
		// Mapper에서는 파라미터를 2개이상 받아들이지 않아서, Map에다 넘겨줄 값들이 저장한다.
		HashMap<String, Integer> pageList = new HashMap<String, Integer>();
		pageList.put("pageNum", 	pageNum);
		pageList.put("pageSize",	pageSize);
		
		for(int key : pageList.values()) {
			System.out.println(key);
		}
		
		// 전체 게시글 수를 구한다.
		int totalCount = boardService.boardListTotalCount1();
		
		// 요청된 페이지에 해당하는 게시글을 가져온다.
		List<BoardDTO> boardList = boardService.boardListPaging1(pageList);
		System.out.println("찾은 목록 ==> " + boardList);
		
		ModelAndView mav = new ModelAndView("/board/boardList1");
		mav.addObject("pageNum",	pageNum);	// 현재 페이지 번호
		mav.addObject("boardList",	boardList);	// 현재 페이지 번호에 해당하는 게시글 목록
		mav.addObject("totalCount",	totalCount);// 전체 게시글 건수
		
		return mav;
		
	} // End - 게시글 목록 보여주기 (Paging 1 처리)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기 (Paging 2 처리)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList2", method = RequestMethod.GET)
	public ModelAndView boardList2(Criteria cri) throws Exception {
		
		ModelAndView mav = new ModelAndView("/board/boardList2");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		// 게시글의 전체 개수를 구해서 pageMaker에 저장한다.
		pageMaker.setTotalCount(boardService.boardListTotalCount2(cri));
		
		// cri에 해당하는 게시글을 가져와서 View에게 넘겨준다.
		List<BoardDTO> boardList = boardService.boardListPaging2(cri);
		
		mav.addObject("boardList", boardList);	// 찾아온 게시글 목록
		mav.addObject("pageMaker", pageMaker);	// 페이지에 관계되어 계산한 값들도 보내준다.
		return mav;
		
	} // End - public ModelAndView boardList2(Criteria cri)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기 (Paging 3 처리 + 검색 기능)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList3", method = RequestMethod.GET)
	public ModelAndView boardList3(SearchCriteria sCri) throws Exception {
		
		logger.info("BoardController 게시글 목록 보여주기 (Paging 3 처리 + 검색 기능) 시작");
		
		ModelAndView mav = new ModelAndView("/board/boardList3");
		
		mav.addObject("searchType",	sCri.getSearchType());
		mav.addObject("keyword",	sCri.getKeyword());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(sCri);
		
		// 조건에 해당하는 데이터 건수를 알아낸다.
		pageMaker.setTotalCount(boardService.boardListTotalCount3(sCri));
		// System.out.println(boardService.boardListTotalCount3(sCri));
		
		// 조건에 해당하는 데이터를 가져온다.
		List<BoardDTO> boardList = boardService.boardListPaging3(sCri);
		
		mav.addObject("boardList",	boardList);
		mav.addObject("pageMaker",	pageMaker);
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// Vue.js에서 사용할 게시글 목록
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardListVue", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:8081") // 해당 출처를 허용한다.
	public String boardListVue(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		logger.info("BoardController Vue.js에서 사용할 게시글 목록가져오기");
		
		/*
		List<BoardDTO> boardList = boardService.boardList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("boardList", boardList);
		*/
		
		List<BoardDTO> boardList = boardService.boardList();
		Gson gson = new GsonBuilder().create();
		
		System.out.println("GSon Data Binding......");
		System.out.println(gson.toJson(boardList));
		return gson.toJson(boardList);
		
	} // End - public String boardListVue(HttpServletRequest request, HttpServletResponse response)

	//-----------------------------------------------------------------------------------------------------------
	// (Vue) 게시글 번호에 해당하는 게시글 정보 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardDetailVue", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:8081/") // 해당 출처를 허용한다.
	public String boardDetailVue(@RequestParam int bno) throws Exception {
		
		BoardDTO boardDetail = boardService.boardDetail(bno, 1);
		
		Gson gson = new GsonBuilder().create();
		return gson.toJson(boardDetail);
		
	} // End - public String boardDetailVue(@RequestParam int bno)
	
	
} // End - public class BoardController










