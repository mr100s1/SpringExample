package com.edu.common.util;

//-----------------------------------------------------------------------------------------------------------
// Criteria + 검색
//-----------------------------------------------------------------------------------------------------------
public class SearchCriteria extends Criteria {
	
	// private	int	page;		// 현재 페이지 번호
	// private	int	perPageNum;	// 한 페이지당 보여줄 글의 갯수
	private	String	searchType;	// 검색 타입 (s:제목, c:내용, w:글쓴이)
	private	String	keyword;	// 검색 키워드

	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}

} // End - public class SearchCriteria
