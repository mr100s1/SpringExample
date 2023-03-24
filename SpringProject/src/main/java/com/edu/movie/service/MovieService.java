package com.edu.movie.service;

import java.util.HashMap;
import java.util.List;

import com.edu.movie.dto.SeatDTO;

//-------------------------------------------------------------------------------------------------
// public interface MovieService
//-------------------------------------------------------------------------------------------------
public interface MovieService {
	
	//-------------------------------------------------------------------------------------------------
	// 좌석 리스트 가져오기
	//-------------------------------------------------------------------------------------------------
	public List<SeatDTO> getSeatList(int movieID) throws Exception;

	//-------------------------------------------------------------------------------------------------
	// 좌석 예약 처리하기
	//-------------------------------------------------------------------------------------------------
	public int insertSeatID(int seatID) throws Exception;
	public HashMap<String, String> insertSeatInfo(HashMap<String, String> seatInfo) throws Exception;
	
	//-------------------------------------------------------------------------------------------------
	// 예약된 좌석 수 알아내기
	//-------------------------------------------------------------------------------------------------
	public int seatReserveCount() throws Exception;
	
	
} // End - public interface MovieService
