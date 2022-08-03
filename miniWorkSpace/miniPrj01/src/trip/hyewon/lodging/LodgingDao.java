package trip.hyewon.lodging;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.hyewon.common.JDBCTemplate;

public class LodgingDao {

	//숙소 검색
	public List<LodgingVo> showLodgingList(Connection conn, LodgingReservationVo vo) throws Exception {
		
		String locationCode = vo.getLocationCode();
		String startDate = vo.getStartDate();
		String endDate = vo.getEndDate();
		String headCount = vo.getPeople();
				
		String sql = "SELECT DISTINCT(LI.NO), LI.NAME, LI.ADDRESS FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO WHERE LOCATION_CODE = ? AND R.MAX_PEOPLE >= ? AND R.NO NOT IN (SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN LODGING_RESERVATION LR ON R.NO = LR.ROOM_NO AND ((LR.START_DATE >= TO_DATE(?) AND LR.START_DATE < TO_DATE(?)) OR (LR.END_DATE > TO_DATE(?) AND LR.END_DATE <= TO_DATE(?)) OR (LR.START_DATE < TO_DATE(?) AND LR.END_DATE > TO_DATE(?)))) ORDER BY LI.NO";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LodgingVo> lodgingVoList = new ArrayList<LodgingVo>();
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(locationCode));
			pstmt.setInt(2, Integer.parseInt(headCount));
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, startDate);
			pstmt.setString(8, endDate);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				
				LodgingVo lodgingVo = new LodgingVo();
				lodgingVo.setNo(Integer.toString(no));
				lodgingVo.setName(name);
				lodgingVo.setAddress(address);
		
				lodgingVoList.add(lodgingVo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingVoList;
		
	}

	//추천 숙소 조회
	public List<LodgingVo> showRecommendLodgingList(Connection conn) throws Exception {
		String sql = "SELECT NO, NAME, ADDRESS FROM LODGING_INFORMATION WHERE NO IN (SELECT COUNT(LODGING_NO) FROM LODGING_REVIEW LR JOIN LODGING_INFORMATION LI ON LR.LODGING_NO = LI.NO GROUP BY LODGING_NO ORDER BY COUNT(LR.LODGING_NO) DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LodgingVo> lodgingVoList = new ArrayList<LodgingVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				
				LodgingVo lodgingVo = new LodgingVo();
				lodgingVo.setNo(Integer.toString(no));
				lodgingVo.setName(name);
				lodgingVo.setAddress(address);
		
				lodgingVoList.add(lodgingVo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingVoList;
	}

	//인기 숙소 조회
	public List<LodgingVo> showPopularLodgingList(Connection conn) throws Exception {

		String sql = "SELECT * FROM LODGING_INFORMATION ORDER BY LODGING_LIKE DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LodgingVo> lodgingVoList = new ArrayList<LodgingVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				
				LodgingVo lodgingVo = new LodgingVo();
				lodgingVo.setNo(Integer.toString(no));
				lodgingVo.setName(name);
				lodgingVo.setAddress(address);
		
				lodgingVoList.add(lodgingVo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingVoList;
	}

	public List<RoomVo> showRoom(LodgingReservationVo vo, Connection conn) throws Exception {
				
		String sql = "SELECT LI.NAME,LI.PHONE, LI.ADDRESS, R.NO, RT.R_TYPE, R.PRICE, R.MAX_PEOPLE, LI.BREAKFAST_YN FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN ROOM_TYPE RT ON R.ROOM_CODE = RT.CODE WHERE R.NO IN ( SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO WHERE LOCATION_CODE = ? AND LI.NO = ? AND R.MAX_PEOPLE >= ? AND R.NO NOT IN (SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN LODGING_RESERVATION LR ON R.NO = LR.ROOM_NO AND ((LR.START_DATE >= TO_DATE(?) AND LR.START_DATE < TO_DATE(?)) OR (LR.END_DATE > TO_DATE(?) AND LR.END_DATE <= TO_DATE(?)) OR (LR.START_DATE < TO_DATE(?) AND LR.END_DATE > TO_DATE(?)))) ) ORDER BY R.NO";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<RoomVo> roomVoList = new ArrayList<RoomVo>();
		
		try {
			//sql 담을 객체 준비 및 sql 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLocationCode());
			pstmt.setString(2, vo.getLodgingNo());
			pstmt.setString(3, vo.getPeople());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());
			pstmt.setString(6, vo.getStartDate());
			pstmt.setString(7, vo.getEndDate());
			pstmt.setString(8, vo.getStartDate());
			pstmt.setString(9, vo.getEndDate());
			
			//sql 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			//커서 내리고, 칼럼별로 읽어오기. 객체로 만들기. 이 작업들 반복ㅇㅇ
			while(rs.next()) {
				int no = rs.getInt("NO");
				String roomType = rs.getString("R_TYPE");
				int price = rs.getInt("PRICE");
				String maxPeople = rs.getString("MAX_PEOPLE");
				String breakfastYn = rs.getString("BREAKFAST_YN");
				
				RoomVo roomVo = new RoomVo();
				roomVo.setNo(no);
				roomVo.setRoomType(roomType);
				roomVo.setMaxPeople(Integer.parseInt(maxPeople));
				roomVo.setBreakfastYn(breakfastYn);
				roomVo.setPrice(price);
		
				roomVoList.add(roomVo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return roomVoList;
	}

	public RoomVo showChoice(LodgingReservationVo vo, Connection conn) throws Exception {
		
		String sql = "SELECT NAME, PHONE, ADDRESS FROM LODGING_INFORMATION WHERE NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomVo roomVo = null;
		try {
			int lodgingNo = Integer.parseInt(vo.getLodgingNo());
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lodgingNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String address = rs.getString("ADDRESS");
				
				roomVo = new RoomVo();
				roomVo.setNo(lodgingNo);
				roomVo.setLodgingName(name);
				roomVo.setAddress(address);
				roomVo.setPhone(phone);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return roomVo;
	}

}
