package com.edu.util.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

//-----------------------------------------------------------------------------------------------------------
// 파일 올리기에 공통으로 사용할 메서드를 가진 클래스
// AjaxUploadController에서 사용하는 클래스
//-----------------------------------------------------------------------------------------------------------
public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일을 업로드하면 호출되는 메서드
	// 날짜별로 디렉토리를 만들어서 파일을 올리도록 한다.
	//-----------------------------------------------------------------------------------------------------------
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		
		// UUID (Universal Unique IDentifer) 발급
		UUID uuid = UUID.randomUUID();
		
		// UUID를 추가한 파일이름 만들기
		// 동일한 이름의 파일이 올라오면 덮어쓰기를 하게 되므로,
		// UUID를 추가하여 덮어쓰기가 되지 않도록 한다.
		// 434cf4ca-a9b3-487c-8473-eb75c9dfa603_괴도 고양이의 대물림.jpg
		String savedName 	= uuid.toString() + "_" + originalName;
		
		// 업로드할 디렉토리를 생성한다.
		String 	savedPath	= calculatePath(uploadPath);
		File	target		= new File(uploadPath + savedPath, savedName);
		
		// 파일을 업로드하게 되면 서버의 임시 디렉토리에 업로드가 된다.
		// FileCopyUtils.copy()를 사용하면 지정한 디렉토리에 저장할 수가 있다.
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리에 복사한다.
		FileCopyUtils.copy(fileData, target);
		
		// 파일의 확자자를 검사해야, 이미지 파일인지 아닌지 알 수가 있다.
		// 어떤 파일은 콤마가 여러 개 있기 때문에 마지막 마침표 이후의 확장자를 가지고 구분해야 한다.
		// a.jpb   ab.cdf.jpg  aaa.bbb.ccc.jpg
		// 파일의 확장자 검사 : 마지막 마침표 다음의 문자들이 파일의 확장자이다.
		String	extensionName		= originalName.substring(originalName.lastIndexOf(".")+1);
		String	uploadedFileName	= null;
		
		// MediaUtils.getMediaType(extendsionName)에 마지막 확장자를 넣어서 
		// 검사를 하면 이미지 파일인지 아닌지 알 수가 있다.
		if(MediaUtils.getMediaType(extensionName) != null) {	// 이미지 파일인 경우
			// 썸네일 파일을 만든다.
			// /2023/01/20/s_98fb3e60-e2d8-4147-8abe-9362b5db529e_귀여운 고양이 01.jpg
			uploadedFileName	= makeThumbnail(uploadPath, savedPath, savedName);
		} else {	// 이미지 파일이 아니면
			// 아이콘을 생성한다.
			// /2023/01/20/b820b210-6715-4e5f-9202-d1893a67112a_hello.txt
			uploadedFileName	= makeIcon(uploadPath, savedPath, savedName);
		}
		
		logger.info("uploadedFileName ==> " + uploadedFileName);
		return uploadedFileName;
		
	} // End - public static String uploadFile(String uploadPath, String originalName, byte[] fileData)

	//-----------------------------------------------------------------------------------------------------------
	// 아이콘 생성하기 
	//-----------------------------------------------------------------------------------------------------------
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		// 아이콘의 이름
		// File.separator(디렉토리 구분자) : 윈도우 \, 유닉스(리눅스) /
		String	iconName = uploadPath + path + File.separator + fileName;
		return	iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	} // End - private static String makeIcon(String uploadPath, String path, String fileName)
	
	//-----------------------------------------------------------------------------------------------------------
	// 썸네일 생성하기 
	//-----------------------------------------------------------------------------------------------------------
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		// 이미지를 읽어서 버퍼에 저장한다.
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		// 100픽셀 단위의 썸네일을 생성한다.
		// Scalr.resize : 원본 이미지보다 축소하기 위해서 사용한다.
		// 높이를 100픽셀로 맞추면 폭은 자동으로 맞춰진다.
		// Scalr.resize(sourece, scalingMethod, resizeMode, targetSize);
		BufferedImage destImg	= Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		// 썸네일 파일의 이름
		// s_이 붙으면 썸네일 파일, 아니면 원본 파일이다.
		String	thumbnailName	= uploadPath + path + File.separator + "s_" + fileName;
		
		File	newFile			= new File(thumbnailName);
		String	extensionName	= fileName.substring(fileName.lastIndexOf(".")+1);
		
		// 썸네일 파일을 생성한다.
		// boolean javax.imageio.ImageIO.write(RenderedImage im, String formatName, File output) throws IOException
		ImageIO.write(destImg, extensionName.toUpperCase(), newFile);
		
		// 썸네일 파일 이름을 리턴한다.
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	} // End - private static String makeThumbnail(String uploadPath, String path, String fileName)
	
	//-----------------------------------------------------------------------------------------------------------
	// 경로를 계산하여 만드는 메서드
	// File.separator 	: File 구분자
	// Windows			: "C:\data\ upload"
	// Linux			: "/home/dooley/upload"
	//-----------------------------------------------------------------------------------------------------------
	private static String calculatePath(String uploadPath) {
		
		// 년월일 정보를 얻기 위해서 캘린더의 인스턴스를 가져온다.
		Calendar cal = Calendar.getInstance();
		
		// 년도를 구해서 변수에 저장한다.
		String yearPath		= File.separator + cal.get(Calendar.YEAR);
		
		// 월을 구해서 변수에 저장한다.
		String monthPath	= yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		// 일을 구해서 변수에 저장한다.
		String datePath		= monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// 년월일에 맞게 디렉토리를 생성한다.
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info("Date 경로 : " + datePath);
		
		// 일경로를 리턴한다.
		return datePath;
		
	} // End - private static String calculatePath(String uploadPath)

	//-----------------------------------------------------------------------------------------------------------
	// 디렉토리를 만드는 메서드
	// 가변 사이즈의 매개 변수 : 마침표 3개(...)
	// String ... 에 uploadPath
	// paths[0]에 yearPath
	// paths[1]에 monthPath
	// paths[2]에 datePath가 들어오게 된다.
	//-----------------------------------------------------------------------------------------------------------
	private static void makeDir(String uploadPath, String ... paths) {
		
		// 만들고자하는 디렉토리가 이미 존재한다면, 만들지 않고 돌아간다.
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		
		// paths에 있는 모든 정보(년경로, 월경로, 일경로)만큼 반복을 한다.
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {	// 디렉토리가 존재하지 않는 경우에만
				dirPath.mkdir();	// 디렉토리를 생성한다.
			}
		}
		
	} // End - private static void makeDir(String uploadPath, String ... paths)

} // End - public class UploadFileUtils





