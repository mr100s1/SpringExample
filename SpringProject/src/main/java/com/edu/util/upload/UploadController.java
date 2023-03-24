package com.edu.util.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//-----------------------------------------------------------------------------------------------------------
// 파일 올리기 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/util/upload/")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// servlet-context.xml에서 선언한 파일올리기 설정을 참조한다.
	// 자바 11부터는 @Resource를 사용하기 위해서
	// https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api를 pom.xml에 추가하여야 한다.
	//
	// 파일 업로드 경로 : servlet-context.xml에 기술한다.
	//-----------------------------------------------------------------------------------------------------------
	@Resource(name = "uploadPath")
	// String uploadPath = "c:/data/upload"; 직접 기술해도 된다.
	String uploadPath;	// 파일 경로를 공통으로 사용하기 위해서 선언한다.
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 화면을 불러온다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		
		logger.info("UplodaController 파일 올리기 화면으로 이동.....");
		
		return "/util/upload/uploadForm";
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 업로드된 파일을 처리하기
	// MultipartFile file : 업로드한 파일이 저장된다.(임시경로)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		
		if(!file.isEmpty()) {
			System.out.println("첨부된 파일이 있습니다.");
			
			System.out.println("파일   이름 : " + file.getOriginalFilename());
			System.out.println("파일   크기 : " + file.getSize());
			System.out.println("컨텐츠 타입 : " + file.getContentType());
			// System.out.println("파일   내용 : " + file.getBytes());
		} else {
			System.out.println("첨부된 파일이 없습니다.");
		}
		
		String savedName = file.getOriginalFilename();		// 파일의 원본이름을 저장한다.
		savedName = uploadFile(savedName, file.getBytes());
		
		mav.setViewName("/util/upload/uploadResult");	// 뷰의 이름 : 처리가 끝나고 돌아갈 페이지
		mav.addObject("savedName", savedName);			// 전달할 데이터
		
		return mav;	// /util/upload/uploadResult.jsp로 포워딩된다.
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 파일의 이름이 중복되지 않도록 처리하는 메서드
	//-----------------------------------------------------------------------------------------------------------
	String uploadFile(String originalName, byte[] fileData) throws Exception {
		
		// Uinversal Unique IDentifier (범용 식별자) => 코드를 랜덤으로 만들어 낸다.
		UUID uuid = UUID.randomUUID();
		
		// c8d4fed7-2b8a-4f89-a038-4e936ca73606_귀여운고양이
		String savedName = uuid.toString() + "_" + originalName;
		
		// new File(디렉토리, 파일이름)
		File target = new File(uploadPath, savedName);
		
		// 첨부파일을 임시 디렉토리에서 우리가 지정한 디렉토리로 복사한다.
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
		
	}
	
	
} // End - public class UploadController




