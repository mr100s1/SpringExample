package com.edu.common.util;

//-----------------------------------------------------------------------------------------------------------
// Criteria(기준) : 검색의 기준을 마련한다.
//-----------------------------------------------------------------------------------------------------------
public class Criteria {

	private	int	page;		// 현재 페이지 번호
	private	int	perPageNum;	// 한 페이지당 보여줄 글의 갯수
	
	// 생성자
	public Criteria() {
		this.page		= 1;	// 목록보기가 시작되는 페이지번호.
		this.perPageNum	= 10;	// 한 페이지당 보여줄 게시글의 수.
	}
	
	public int getPage() {
		return page;
	}
	// 페이지 설정
	public void setPage(int page) {
		if(page <= 0) {			// 페이지는 0이나 음수가 없다.
			this.page	= 1;	// 보고자 하는 페이지가 0이하이면 1페이지로 변경한다.
		} else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	
	// 생성자에서 생성된 perPageNum 값이 아닌 다른 값으로 변경요청이 오면, 생성된 값으로 유지한다.
	public void setPerPageNum(int perPageNum) {
		int originalPerPageNum = this.perPageNum;
		
		if(perPageNum != originalPerPageNum) {
			this.perPageNum = originalPerPageNum;
		} else {
		this.perPageNum = perPageNum;
		}
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
} // End - public class Criteria
