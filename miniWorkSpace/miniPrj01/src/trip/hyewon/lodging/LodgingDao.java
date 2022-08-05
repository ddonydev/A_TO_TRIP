package trip.hyewon.lodging;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.min.common.JDBCTemplate;
import trip.min.main.MemberMain;



public class LodgingDao {

	//숙소 검색
	public List<LodgingVo> showLodgingList(Connection conn, LodgingReservationVo vo) throws Exception {
		
		String locationCode = vo.getLocationCode();
		String startDate = vo.getStartDate();
		String endDate = vo.getEndDate();
		String headCount = vo.getPeople();
				
		String sql = "SELECT DISTINCT(LI.NO), LI.NAME, LI.ADDRESS FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO WHERE LOCATION_CODE = ? AND R.MAX_PEOPLE >= ? AND R.NO NOT IN (SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN LODGING_RESERVATION LR ON R.NO = LR.ROOM_NO AND ((LR.START_DATE >= TO_DATE(?) AND LR.START_DATE < TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.END_DATE > TO_DATE(?) AND LR.END_DATE <= TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.START_DATE < TO_DATE(?) AND LR.END_DATE > TO_DATE(?) AND CANCEL_YN = 'N'))) ORDER BY LI.NO";
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
		//String sql = "SELECT NO, NAME, ADDRESS FROM LODGING_INFORMATION WHERE NO IN (SELECT COUNT(LODGING_NO) FROM LODGING_REVIEW LR JOIN LODGING_INFORMATION LI ON LR.LODGING_NO = LI.NO GROUP BY LODGING_NO ORDER BY COUNT(LR.LODGING_NO) DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY)";
		String sql = "SELECT LI.NO, COUNT(LR.LODGING_NO), LI.NAME, LI.ADDRESS FROM LODGING_REVIEW LR JOIN LODGING_INFORMATION LI ON LR.LODGING_NO = LI.NO GROUP BY NAME, ADDRESS, LI.NO ORDER BY COUNT(LR.LODGING_NO) DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
		
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
				int reviewCnt = rs.getInt("COUNT(LR.LODGING_NO)");
				
				LodgingVo lodgingVo = new LodgingVo();
				lodgingVo.setNo(Integer.toString(no));
				lodgingVo.setName(name);
				lodgingVo.setAddress(address);
				lodgingVo.setReviewCnt(reviewCnt);
		
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

		//String sql = "SELECT * FROM LODGING_INFORMATION ORDER BY LODGING_LIKE DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
		String sql = "SELECT LI.NO, NAME, ADDRESS, COUNT(LW.LODGING_NO) FROM LODGING_WISH LW JOIN LODGING_INFORMATION LI ON LW.LODGING_NO = LI.NO GROUP BY NAME, ADDRESS, LI.NO ORDER BY COUNT(LW.LODGING_NO) DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
		
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
				int zzimCnt = rs.getInt("COUNT(LW.LODGING_NO)");
				
				LodgingVo lodgingVo = new LodgingVo();
				lodgingVo.setNo(Integer.toString(no));
				lodgingVo.setName(name);
				lodgingVo.setAddress(address);
				lodgingVo.setZzimCnt(zzimCnt);
		
				lodgingVoList.add(lodgingVo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingVoList;
	}

	public List<RoomVo> showRoom(LodgingReservationVo vo, Connection conn) throws Exception {
				
		String sql = "SELECT LI.NAME,LI.PHONE, LI.ADDRESS, R.NO, RT.R_TYPE, R.PRICE, R.MAX_PEOPLE, LI.BREAKFAST_YN FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN ROOM_TYPE RT ON R.ROOM_CODE = RT.CODE WHERE R.NO IN ( SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO WHERE LOCATION_CODE = ? AND LI.NO = ? AND R.MAX_PEOPLE >= ? AND R.NO NOT IN (SELECT R.NO FROM LODGING_INFORMATION LI JOIN ROOM R ON LI.NO = R.LODGING_NO JOIN LODGING_RESERVATION LR ON R.NO = LR.ROOM_NO AND ((LR.START_DATE >= TO_DATE(?) AND LR.START_DATE < TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.END_DATE > TO_DATE(?) AND LR.END_DATE <= TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.START_DATE < TO_DATE(?) AND LR.END_DATE > TO_DATE(?) AND CANCEL_YN = 'N'))) ) ORDER BY R.NO";
		
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

	public List<LodgingCouponVo> checkCoupon(int no, Connection conn) throws Exception {
		
		String sql = "SELECT A.NO, A.COUPON_INFO_NO, A.MEMBER_NO, A.USED_YN, A.USED_DATE, B.EVENT_NO, B.DISCOUNT_RATE, B.COUPON_CODE FROM COUPON_ISSUED A JOIN COUPON_INFO B ON A.COUPON_INFO_NO = B.NO WHERE A.MEMBER_NO = ? AND A.USED_YN = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LodgingCouponVo> lodgingCouponVoList = new ArrayList<LodgingCouponVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int couponIssuedNo = rs.getInt("NO");
				int couponInfoNo = rs.getInt("COUPON_INFO_NO");
				String usedYn = rs.getString("USED_YN");
				Timestamp usedDate = rs.getTimestamp("USED_DATE");
				int eventNo = rs.getInt("EVENT_NO");
				String discount = rs.getString("DISCOUNT_RATE");
				String couponCode = rs.getString("COUPON_CODE");
				
				LodgingCouponVo vo = new LodgingCouponVo();
				vo.setCouponIssuedNo(couponIssuedNo);
				vo.setCouponInfoNo(couponInfoNo);
				vo.setUsedYn(usedYn);
				vo.setUsedDate(usedDate);
				vo.setEventNo(eventNo);
				vo.setDiscount(discount);
				vo.setCouponCode(couponCode);
				
				lodgingCouponVoList.add(vo);
			} 
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingCouponVoList;
	}

	public int updateCouponIssued(int couponIssuedNo, Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE COUPON_ISSUED SET USED_YN = 'Y', USED_DATE = SYSDATE WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couponIssuedNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertReservation(LodgingReservationVo rv, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LODGING_RESERVATION VALUES(SEQ_RESERVE_NO.NEXTVAL,?,?,?,SYSDATE,?,TO_DATE(?),TO_DATE(?),?,?,?,'N')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rv.getMemberNo()));
			pstmt.setInt(2, Integer.parseInt(rv.getLodgingNo()));
			pstmt.setInt(3, Integer.parseInt(rv.getRoomNo()));
			pstmt.setInt(4, Integer.parseInt(rv.getPeople()));
			pstmt.setString(5, rv.getStartDate());
			pstmt.setString(6, rv.getEndDate());
			pstmt.setString(7, rv.getPayYn());
			pstmt.setInt(8, Integer.parseInt(rv.getPayment()));
			pstmt.setString(9, rv.getBreakfastYn());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int getReservationNo(LodgingReservationVo rv, Connection conn) throws Exception {
		
		String sql = "SELECT NO FROM LODGING_RESERVATION WHERE MEMBER_NO = ? AND LODGING_NO = ? AND ROOM_NO = ? AND PEOPLE = ? AND START_DATE = TO_DATE(?) AND END_DATE = TO_DATE(?) AND PAYMENT_YN = 'Y' AND PAYMENT = ? AND BREAKFAST_YN = ? AND CANCEL_YN = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			int memberNo = Integer.parseInt(rv.getMemberNo());
			int lodgingNo = Integer.parseInt(rv.getLodgingNo());
			int roomNo = Integer.parseInt(rv.getRoomNo());
			int people = Integer.parseInt(rv.getPeople());
			String startDate = rv.getStartDate();
			String endDate = rv.getEndDate();
			String payment = rv.getPayment();
			String breakfastYn = rv.getBreakfastYn();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, lodgingNo);
			pstmt.setInt(3, roomNo);
			pstmt.setInt(4, people);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, payment);
			pstmt.setString(8, breakfastYn);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("NO");
				
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertPayment(LodgingReservationVo rv, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LODGING_PAYMENT VALUES(SEQ_LODGING_PAYMENT_NO.NEXTVAL,?,?,SYSDATE,?,'N',?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rv.getNo()));
			pstmt.setInt(2, Integer.parseInt(rv.getCouponIssuedNo()));
			pstmt.setString(3, rv.getPayWay());
			pstmt.setString(4, rv.getCouponYn());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public List<LodgingReservationVo> showMyReservation(String num, Connection conn) throws Exception {
		
		String sql = "SELECT LR.NO, LR.RESERVE_DATE, LR.PEOPLE, LR.START_DATE, LR.END_DATE, LR.PAYMENT_YN, LR.PAYMENT, LR.BREAKFAST_YN, LR.CANCEL_YN, LI.NAME, LI.PHONE, LI.ADDRESS FROM LODGING_RESERVATION LR JOIN LODGING_INFORMATION LI ON LR.LODGING_NO = LI.NO WHERE LR.MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LodgingReservationVo> VoList = new ArrayList<LodgingReservationVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int input = Integer.parseInt(num);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String reserveDate = rs.getString("RESERVE_DATE");
				String lodgingName = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");
				String startDate = rs.getString("START_DATE");
				String endDate = rs.getString("END_DATE");
				int people = rs.getInt("PEOPLE");
				String breakfastYn = rs.getString("BREAKFAST_YN");
				int payment = rs.getInt("PAYMENT");
				String payYn = rs.getString("PAYMENT_YN");
				String cancelYn = rs.getString("CANCEL_YN");
				
				LodgingReservationVo vo = new LodgingReservationVo();
				vo.setNo(Integer.toString(no));
				vo.setReserveDate(reserveDate);
				vo.setLodgingName(lodgingName);
				vo.setAddress(address);
				vo.setLodgingPhone(phone);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				vo.setPeople(Integer.toString(people));
				vo.setBreakfastYn(breakfastYn);
				vo.setPayment(Integer.toString(payment));
				vo.setPayYn(payYn);
				vo.setCancelYn(cancelYn);
				
				VoList.add(vo);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return VoList;
	}

	public int updateCancelY(String input, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_RESERVATION SET CANCEL_YN = 'Y' WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(input));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Boolean checkBfYn(String updateNo, Connection conn) {
		String sql = "SELECT LI.BREAKFAST_YN FROM LODGING_RESERVATION LR JOIN LODGING_INFORMATION LI ON LR.LODGING_NO = LI.NO WHERE LR.NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isBf = false;
		String bf = "N";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(updateNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bf = rs.getString("BREAKFAST_YN");
				if(bf.equals("Y")) {
					isBf = true;
				}
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return isBf;
		
	}

	public int cancelBf(String updateNo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_RESERVATION SET PAYMENT = PAYMENT-35000, BREAKFAST_YN = 'N' WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(updateNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int addBf(String updateNo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_RESERVATION SET PAYMENT = PAYMENT+35000, BREAKFAST_YN = 'Y' WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(updateNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Boolean checkEmpty(int roomNo, String startDate, String endDate, Connection conn) {
		
		String sql = "SELECT * FROM LODGING_RESERVATION LR WHERE ROOM_NO = ? AND ((LR.START_DATE >= TO_DATE(?) AND LR.START_DATE < TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.END_DATE > TO_DATE(?) AND LR.END_DATE <= TO_DATE(?) AND CANCEL_YN = 'N') OR (LR.START_DATE < TO_DATE(?) AND LR.END_DATE > TO_DATE(?) AND CANCEL_YN = 'N'))";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isEmpty = true;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			pstmt.setString(4, startDate);
			pstmt.setString(5, endDate);
			pstmt.setString(6, startDate);
			pstmt.setString(7, endDate);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isEmpty = false;
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return isEmpty;
	}

	public int selectRoomNo(String updateNo, Connection conn) {
		String sql = "SELECT ROOM_NO FROM LODGING_RESERVATION WHERE NO = ? AND CANCEL_YN = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int roomNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(updateNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				roomNo = rs.getInt("ROOM_NO");
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return roomNo;
	}

	public int updateStartEndDate(String updateNo, String startDate, String endDate, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_RESERVATION SET START_DATE = ? , END_DATE = ? WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, Integer.parseInt(updateNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public String checkZzimCancel(String lodgingNo, Connection conn) {
		String sql = "SELECT CANCEL_YN FROM LODGING_WISH WHERE MEMBER_NO = ? AND LODGING_NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String zzimCancel = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(MemberMain.LoginMember.getNo()));
			pstmt.setInt(2, Integer.parseInt(lodgingNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				zzimCancel = rs.getString("CANCEL_YN");
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return zzimCancel;
	}

	public int zzimInsert(String lodgingNo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LODGING_WISH VALUES(SEQ_LODGING_WISH_NO.NEXTVAL,?,?,'N')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(MemberMain.LoginMember.getNo()));
			pstmt.setInt(2, Integer.parseInt(lodgingNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateZzimCancelY(String lodgingNo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_WISH SET CANCEL_YN = 'Y' WHERE MEMBER_NO = ? AND LODGING_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(MemberMain.LoginMember.getNo()));
			pstmt.setInt(2, Integer.parseInt(lodgingNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateZzimCancelN(String lodgingNo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LODGING_WISH SET CANCEL_YN = 'N' WHERE MEMBER_NO = ? AND LODGING_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(MemberMain.LoginMember.getNo()));
			pstmt.setInt(2, Integer.parseInt(lodgingNo));
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectLodgingNo(String updateNo, Connection conn) {
		String sql = "SELECT LODGING_NO FROM LODGING_RESERVATION WHERE NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int lodgingNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(updateNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lodgingNo = rs.getInt("LODGING_NO");
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return lodgingNo;
	}

}
