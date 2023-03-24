package com.edu.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.dto.MemberDTO;
import com.edu.member.service.MemberService;

//-----------------------------------------------------------------------------------------------------------
// 회원 정보 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller("memberController")
@RequestMapping("/member")	// url에서 /member로 시작하는 요청들을 처리하는 컨트롤러.
public class MemberControllerImpl implements MemberController {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(MemberControllerImpl.class);

	//-----------------------------------------------------------------------------------------------------------
	//	@Inject		: Java에서 지원하는 어노테이션. 특정 Framework에 종속정이기 않다.
	//	@Autowired	: Spring에서 지원하는 어노테이션.
	//-----------------------------------------------------------------------------------------------------------
	@Autowired
	private	MemberDTO	memberDTO;
	
	// MemberService memberService = new MemberService();
	@Autowired
	private	MemberService	memberService;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 화면 띄우기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl 로그인 처리 시작.....");
		System.out.println("로그인 정보 => " + member.getId() + " : " + member.getPwd());
		
		ModelAndView mav = new ModelAndView();
		
		// 아이디 값이 없이 넘어온 경우에는 돌려보낸다.
		if(member.getId().equals("") || member.getId() == null) {
			rAttr.addAttribute("result", "loginIdEmpty");
			mav.setViewName("redirect:/member/loginForm.do");
			return mav;
		}
		
		// 로그인한 정보를 가지고 데이터베이스에 존재하는지 처리를 하고, 그 결과를 가져온다.
		// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
		memberDTO = memberService.login(member);
		System.out.println("로그인 처리 결과 ==> " + memberDTO);
		
		// 로그인한 정보가 데이터베이스에 존재하는지에 따라 처리를 다르게 한다.
		if(memberDTO != null) {	// 로그인 정보에 해당하는 자료가 있으면
			
			if(member.getPwd().equals(memberDTO.getPwd())) {
				
				// 아이디와 비밀번호가 일치하면 세션을 발급한다.
				HttpSession session = request.getSession();
				session.setAttribute("member", 	memberDTO);
				session.setAttribute("isLogOn", true);
				mav.setViewName("redirect:/main.do");	// 메인화면으로 이동한다.
				
			} else { 	// 아이디는 있는데 비밀번호가 틀린 경우
						// 메시지를 가지고 로그인 화면으로 이동한다.
				rAttr.addAttribute("result", "PasswordFailed");
				mav.setViewName("redirect:/member/loginForm.do");
			}
			
		} else {	// 로그인한 아이디가 존재하지 않으면 
					// 로그인 실패 메시지를 가지고 로그인 화면으로 이동한다.
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		
		return mav;
		
	} // End - public ModelAndView login(...)

	
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그아웃 버튼을 누르면 세션정보를 없애고, 메인페이지로 이동하게 한다.
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");	// 메인페이지로 이동
		
		return mav;
		
	} // End - 로그아웃 처리
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberForm.do", method=RequestMethod.GET)
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl 회원가입 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberForm");	// 회원가입화면
		return mav;
	} // End - 회원가입 화면 불러오기()

	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("MemberControllerImpl 회원가입 처리하기() 시작");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int result	= 0;	// 데이터 처리 완료 건수를 저장할 변수
		
		// 사용자가 입력한 회원정보를 서비스에게 넘겨주어서 처리하게 한다.
		result = memberService.addMember(memberDTO);
		
		// 회원가입 처리후 회원전체목록 페이지로 이동한다.
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");

		return mav;
	} // End - 회원가입 처리하기

	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl 회원 전체 목록 조회() 시작");
		
		// 회원 전체 목록을 가져온다.
		List<MemberDTO> memberLists = memberService.listMembers();
		
		ModelAndView mav = new ModelAndView("/member/listMembers");
		// mav.addObject("뷰에서 넘겨받을 이름", 뷰에 넘겨줄 값);
		mav.addObject("memberLists", memberLists);
		return mav;
		
	} // End - 회원 전체 목록 조회하기

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 + 폼출력
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/updateMemberForm.do", method = RequestMethod.GET)
	public ModelAndView updateMemberForm(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 조회 + 폼출력() 시작");

		// 회원 전체 목록 화면에서 수정을 요청한 id에 해당하는 정보를 찾는 일을 서비스에게 부탁한다.
		memberDTO = memberService.selectMember(id);
		logger.info("회원정보조회결과 : " + memberDTO);
		
		// 찾아온 회원 정보를 가지고 회원 정보 수정화면으로 넘어간다.
		ModelAndView mav = new ModelAndView("/member/updateMemberForm");
		mav.addObject("member", memberDTO);
		return mav;

	} // End - 아이디에 해당하는 회원 정보 조회 + 폼출력()

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/modifyMember.do", method = RequestMethod.POST)
	public ModelAndView modifyMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 수정하기() 시작");
		logger.info("JSP에서 넘겨준 회원정보 : " + memberDTO);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int result = memberService.modifyMember(memberDTO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
		
	} // End - 아이디에 해당하는 회원 정보 수정하기()

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 삭제하기기() 시작");
		logger.info("JSP에서 넘겨준 회원아이디 : " + id);
		
		request.setCharacterEncoding("UTF-8");

		int result = memberService.removeMember(id);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
		
	} // End - 아이디에 해당하는 회원 정보 삭제하기()

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 화면 불러오기 (Ajax)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/registerAjaxForm.do", method = RequestMethod.GET)
	public String getRegisterAjaxForm() throws Exception {

		logger.info("MemberControllerImpl 회원 가입 화면 불러오기 (Ajax) 시작");
		return "/member/registerAjax";

	} // End - 회원 가입 화면 불러오기 (Ajax)

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 (Ajax)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberDTO memberDTO) throws Exception {

		logger.info("MemberControllerImpl 아이디 중복 검사 (Ajax) 시작");

		int result = memberService.idCheck(memberDTO);
		logger.info("MemberControllerImpl 아이디 중복 검사 (Ajax) 후 받은 값 : " + result);
		
		return result;
	} // End - 아이디 중복 검사 (Ajax)

	
	
	
	
	
	
} // End - public class MemberControllerImpl






