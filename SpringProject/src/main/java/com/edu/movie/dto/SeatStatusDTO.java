package com.edu.movie.dto;

//-------------------------------------------------------------------------------------------------
// 좌석 예약 상태
//-------------------------------------------------------------------------------------------------
public class SeatStatusDTO {

	int		seatID;		// 좌석번호
	boolean	status;		// 좌석예약상태 : true - 예약중, false - 비어있는 좌석
	
	public SeatStatusDTO(int seatID, boolean status) {
		super();
		this.seatID	= seatID;
		this.status	= status;
	}
	
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "SeatStatusDTO [seatID=" + seatID + ", status=" + status + "]";
	}
	
} // End - public class SeatStatusDTO
