package com.edu.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//-----------------------------------------------------------------------------------------------------------
// public class PageMaker
//-----------------------------------------------------------------------------------------------------------
public class PageMaker {
	
	/*	Criteria의 멤버 변수
	private	int	page;		// 현재 페이지 번호
	private	int	perPageNum;	// 한 페이지당 보여줄 글의 갯수
	*/
	private	Criteria	cri;
	private	int			totalCount;				// 전체 게시글 갯수
	private	int			startPage;				// 화면 하단에 보여지는 시작 페이지 번호
	private	int			endPage;				// 화면 하단에 보여지는 끝 페이지 번호
	private	boolean		prev;					// 이전
	private	boolean		next;					// 이후
	private	int			displayPageNum	= 10;	// 화면 하단에 보여줄 페이지의 갯수
	
	private	static final Logger logger = LoggerFactory.getLogger(PageMaker.class);

	//-----------------------------------------------------------------------------------------------------------
	public Criteria getCri() {
		return cri;
	}
	//-----------------------------------------------------------------------------------------------------------
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	//-----------------------------------------------------------------------------------------------------------
	public int getTotalCount() {
		return totalCount;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 개수
	//-----------------------------------------------------------------------------------------------------------
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		logger.info("-----------------------------------------------------");
		logger.info("***** setTotalCount(int totalCount) ==> " + totalCount);
		logger.info("-----------------------------------------------------");
		
		// 총 데이터 건수를 가지고 페이지 관련하여 필요한 것들을 계산을 한다.
		calculatePages();
	}

	//-----------------------------------------------------------------------------------------------------------
	// 페이지 계산		: 총 데이터 건수를 가지고 페이지를 계산한다.
	// Math.ceil(숫자)	: 입력받은 숫자보다 크거나 같은 정수 중에서 가장 작은 정수를 리턴한다.
	//-----------------------------------------------------------------------------------------------------------
	private void calculatePages() {
		
		logger.info("----------------------------------------------------------------------------------------------");
		logger.info("***** calculatePages ==> cri.getPage():" + cri.getPage() + ", displayPageNum:" + displayPageNum);
		logger.info("----------------------------------------------------------------------------------------------");

		endPage 	= (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		
		// 화면 하단에 10개씩 페이지를 보여준다면, 
		// 시작 페이지는 (보여줄 마지막 페이지 - 보여줄 페이지 개수) + 1;
		startPage	= (endPage - displayPageNum) + 1;
		
		// 마지막 페이지 번호가 [10]보다 작거나 같으면, 시작 페이지는 무조건 1페이지가 된다.
		if(endPage	 <= displayPageNum)		startPage = 1;
		if(startPage <= 0) startPage = 1;	// 시작 페이지가 0이하가 나오면 무조건 1페이지로 만든다.
		
		// realEndPage : 실제 가지고 있는 페이지의 개수를 구해서 저장하는 변수
		int realEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		// 실제로 가지고 있는 마지막 페이지가 10단위를 채우지 못한다면,
		// 마지막 페이지는 실제로 가지고 있는 총 페이지 개수로 한다.
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// 시작 페이지가 1이 아닌 경우에만 [prev] 버튼을 보여준다.
		prev = startPage == 1 ? false : true;
		
		// 마지막 페이지 번호 x 한 페이지당 보여줄 게시글 갯수(perPageNum) < 총 데이터 건수이면,
		// 화면 하단에 나타나는 페이지의 갯수를 넘어가기 때문에 [next] 버튼을 보여준다.
		next = endPage * cri.getPerPageNum() < totalCount ? true : false;
		
	} // End - private void calculatePages()
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}

} // End - public class PageMaker






