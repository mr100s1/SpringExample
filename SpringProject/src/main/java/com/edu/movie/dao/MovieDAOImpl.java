package com.edu.movie.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.movie.dto.SeatDTO;

//-------------------------------------------------------------------------------------------------
// public class MovieDAOImpl implements MovieDAO
//-------------------------------------------------------------------------------------------------
@Repository
public class MovieDAOImpl implements MovieDAO {

	private static final Logger logger = LoggerFactory.getLogger(MovieDAOImpl.class);

	@Inject	// 의존관계 주입(Defendency Injection, DI)
	SqlSession sqlSession;
	
	private static String namespace = "com.edu.movie.mappers.movieMapper";
	
	//-------------------------------------------------------------------------------------------------
	// 좌석 리스트 가져오기
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {
		logger.info("MovieDAOImpl 좌석 리스트 가져오기");
		return sqlSession.selectList(namespace + ".seatList", movieID);
	}

	//-------------------------------------------------------------------------------------------------
	// 좌석 예약 처리하기
	//-------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		return sqlSession.insert(namespace + ".insertSeatID", seatID);
	}

	//-------------------------------------------------------------------------------------------------
	// 예약된 좌석 수 알아내기
	//-------------------------------------------------------------------------------------------------
	@Override
	public int seatReserveCount() throws Exception {
		return sqlSession.selectOne(namespace + ".reserveCount");
	}

	
} // End - public class MovieDAOImpl implements MovieDAO
