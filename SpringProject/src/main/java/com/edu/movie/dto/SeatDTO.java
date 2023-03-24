package com.edu.movie.dto;

//-------------------------------------------------------------------------------------------------
// 영화 좌석
//-------------------------------------------------------------------------------------------------
public class SeatDTO {
	
	int		movieID;	// 영화
	String	userID;		// 사용자
	int		seatID;		// 좌석 아이디
	
	public SeatDTO(int movieID, String userID, int seatID) {
		this.movieID	= movieID;	// 영화
		this.userID		= userID;	// 예매자
		this.seatID		= seatID;	// 예매좌석
	}
	
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	
	@Override
	public String toString() {
		return "SeatDTO [movieID=" + movieID + ", userID=" + userID + ", seatID=" + seatID + "]";
	}
	

} // End - public class SeatDTO
