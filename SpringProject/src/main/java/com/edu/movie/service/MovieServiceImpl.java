package com.edu.movie.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.movie.dao.MovieDAO;
import com.edu.movie.dto.SeatDTO;

//-------------------------------------------------------------------------------------------------
// public class MovieServiceImpl implements MovieService
//-------------------------------------------------------------------------------------------------
@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Inject
	MovieDAO movieDAO;
	
	//-------------------------------------------------------------------------------------------------
	// 좌석 리스트 가져오기
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {
		logger.info("MovieServiceImpl 좌석 리스트 가져오기.....");
		return movieDAO.getSeatList(movieID);
	}

	//-------------------------------------------------------------------------------------------------
	// 좌석 예약 처리하기
	//-------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		return movieDAO.insertSeatID(seatID);
	}

	//-------------------------------------------------------------------------------------------------
	// 예약된 좌석 수 알아내기
	//-------------------------------------------------------------------------------------------------
	@Override
	public int seatReserveCount() throws Exception {
		return movieDAO.seatReserveCount();
	}

	@Override
	public HashMap<String, String> insertSeatInfo(HashMap<String, String> seatInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

} // End - public class MovieServiceImpl implements MovieService
