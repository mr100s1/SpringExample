package com.edu.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//-----------------------------------------------------------------------------------------------------------
// AJAX를 이용하여 파일 올리기
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/util/upload")
public class AjaxUploadController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	// 업로드할 경로(디렉토리)는 servlet-context.xml에 설정해 놓았다.
	@Resource(name = "uploadPath")
	String uploadPath;
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기(AJAX) 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public String uploadAjax() {
		
		logger.info("AjaxUploadController 파일 올리기(AJAX) 화면 불러오기");
		return "/util/upload/uploadAjax";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일을 드래그해서 업로드했을 경우에 진행되는 메서드(AJAX)
	// 업로드된 파일은 MultipartFile 변수에 저장이 된다.
	// @ResponseBody : jsp로 넘어가는 것이 아니라 데이터 자체를 되돌려 주는 것이다.
	// ResponseEntity<String> : 메시지와 에러코드를 같이 돌려준다.
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody	// json 형식으로 리턴한다.
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		logger.info("AjaxUploadController 파일 올리기(AJAX) 처리하기");
		
		// 파일 정보를 로그에 출력한다.
		System.out.println("파일   이름 : " + file.getOriginalFilename());
		System.out.println("파일   크기 : " + file.getSize());
		System.out.println("컨텐츠 타입 : " + file.getContentType());
	
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);

	} // End - public ResponseEntity<String> uploadAjax(MultipartFile file)
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 표시 기능을 하게 하는 메서드
	// 이 메서드가 없으면 View에서 엑박으로 나타난다.
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody	// view 가 아닌 data를 리턴한다.
	@RequestMapping(value = "/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		logger.info("AjaxUploadController 이미지 표시(AJAX) 처리하기");
		
		// 서버의 파일을 다운로드하기 위한 스트림
		InputStream				in		= null;	// import java.io
		ResponseEntity<byte[]>	entity	= null;
		
		try {
			// 넘겨 받은 파일이름에서 확장자를 검사한다.
			// 매개변수가 1개인 경우 : substring(10) 	
			//				=> 매개변수의 위치부터 뒤의 모든 것을 말한다.
			// 매개변수가 2개인 경우 : substring(1, 5)	
			//				=> 첫번째 매개변수 위치부터 두번째 매개변수 바로 전까지의 데이터를 말한다. 
			String	extensionName	= fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType			= MediaUtils.getMediaType(extensionName);
			
			// 헤더 구성 객체
			HttpHeaders headers		= new HttpHeaders();
			
			// InputStream 생성
			in = new FileInputStream(uploadPath + fileName);
			
			// 이미지 파일인지 아닌지에 따라서 처리를 다르게 한다.
			if(mType != null) {	// 이미지 파일인 경우
				headers.setContentType(mType);
			} else {	// 이미지 파일이 아닌 경우
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				
				// 파일이름에 한글이 들어간 경우 스트링.getBytes("언어셑") 스트링을 바이트 배열로 변환
				// new String(바이트 배열, "언어 셑") 문자열의 인코딩 변경
				
				// fileNmae을 utf-8로 읽어서 서유럽언어 형식으로 바꾸어서 넘겨준다.
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + "\"");
				
			} // End - if ~ else
			
			// 바이트 배열, 헤더
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null)
				in.close();	// 사용한 자원(스트림) 닫기
		}
		
		return entity;
		
	} // End - public ResponseEntity<byte[]> displayFile(String fileName)
	
	//-----------------------------------------------------------------------------------------------------------
	// [삭제]버튼을 누르면 파일 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		
		logger.info("AjaxUploadController 파일 삭제하기(AJAX) ==> " + fileName);
		
		// 확장자 검사
		// fileName에는 이미지 파일의 경우 썸네일 파일 이름이 넘어온다.
		String	extensionName	= fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType			= MediaUtils.getMediaType(extensionName);

		// /2023/01/20/s_98fb3e60-e2d8-4147-8abe-9362b5db529e_귀여운 고양이 01.jpg
		// 이미지 파일이면 썸네일 파일에서 이미지 원본 이름을 추출하여 이미지 원본파일을 삭제한다.
		if(mType != null) {	// 이미지 파일이면 원본 파일을 삭제한다.
			String	front	= fileName.substring(0, 12);
			String	end		= fileName.substring(14);
			
			// (front + end) == /2023/01/20/98fb3e60-e2d8-4147-8abe-9362b5db529e_귀여운 고양이 01.jpg
			// 원본 파일 삭제
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		
		// 이미지 파일이면 썸네일 이미지를 삭제하고, 일반 파일은 원본 파일을 삭제한다.
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	} // End - public ResponseEntity<String> deleteFile(String fileName)
	
} // End - public class AjaxUploadController






