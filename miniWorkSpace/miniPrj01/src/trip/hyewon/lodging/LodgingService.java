package trip.hyewon.lodging;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import trip.min.common.JDBCTemplate;



public class LodgingService {

	//지역번호 체크
	public boolean checkLocationNum(String location) {
		if(location.equals("1")||location.equals("2")||location.equals("3")||location.equals("4")||location.equals("5")||location.equals("6")||location.equals("Q")) {
			return true;
		}
		return false;
	}

	//날짜 체크
	public boolean checkDate(String date) {
		//8글자가 아니면 false
		if(date.length() != 8) {
			return false;
		}
		//3번째칸, 6번째 칸에 슬래시 없으면 false
		if(date.charAt(2) != '/' || date.charAt(5) != '/') {
			return false;
		}
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");         
		String formatedNow = now.format(formatter); //현재 날짜      
		
		String nowYear = formatedNow.substring(0, 2); //현재 년도
		String nowMonth = formatedNow.substring(3, 5); //현재 월
		String nowDay = formatedNow.substring(6, 8); //현재 일
		
		int nextYear2 = Integer.parseInt(nowYear)+1; //다음 월 = 현재 월+1
		String nextYear = Integer.toString(nextYear2); //다음 월 String 형변환
		
		String dateYear = date.substring(0,2); //입력받은 년도
		String dateMonth = date.substring(3,5); //입력받은 월
		String dateDay = date.substring(6,8); //입력받은 일
		
		//입력년도가 현재 년도 또는 다음 년도가 아니면 잘못된 입력임
		if(!dateYear.equals(nowYear) && !dateYear.equals(nextYear)) {
			return false;
		}
		//입력년도가 현재 년도인데 입력 월이 현재 월보다 작으면 잘못된 입력임
		if(dateYear.equals(nowYear) && (Integer.parseInt(dateMonth) < Integer.parseInt(nowMonth))) {
			return false;
		}
		//입력 년,월이 현재 년,월인데 입력 일이 현재 일보다 작으면 잘못된 입력임
		if(dateYear.equals(nowYear) && dateMonth.equals(nowMonth) && (Integer.parseInt(dateDay) < Integer.parseInt(nowDay))) {
			return false;
		}
		
		int last2; //2월의 마지막일
		int last;  //마지막일
		int dateYear2 = Integer.parseInt("20" + dateYear);//입력받은 년도가 22라면 2022로 변환하고 정수로 형변환
		int dateMonth2 = Integer.parseInt(dateMonth);
		
		//2월의 마지막 일 판별
		if(dateYear2 % 400 == 0) {
			last2 = 29;
		} else if (dateYear2 % 4 == 0 && dateYear2 % 100 != 0) {
			last2 = 29;
		} else {
			last2 = 28;
		}
		
		//모든 달의 마지막 일 판별
		if (dateMonth2 == 1 ||dateMonth2 == 3 || dateMonth2 == 5|| dateMonth2 == 7|| dateMonth2 == 8||dateMonth2 == 10||dateMonth2 == 12) {
			last = 31;
		} else if (dateMonth2 == 4||dateMonth2 == 6||dateMonth2 == 9||dateMonth2 == 11) {
			last = 30;
		} else {
			last = last2;
		}
		//일이 1보다 작거나 마지막날보다 크면 false
		if(Integer.parseInt(dateDay)<1 || Integer.parseInt(dateDay)>last) {
			return false;
		}
		//모든 조건 통과하면 true		
		return true;
	}
	
	//퇴실 날짜 체크
	public boolean checkEndDate(String startDate, String endDate) {		
		int startDateYear = Integer.parseInt(startDate.substring(0, 2)); //입실 년도
		int startDateMonth = Integer.parseInt(startDate.substring(3, 5)); //입실 월
		int startDateDay = Integer.parseInt(startDate.substring(6, 8)); //입실 일
		
		int endDateYear = Integer.parseInt(endDate.substring(0, 2)); //퇴실 년도
		int endDateMonth = Integer.parseInt(endDate.substring(3, 5)); //퇴실 월
		int endDateDay = Integer.parseInt(endDate.substring(6, 8)); //퇴실 일
		
		//입실 년도와 퇴실 년도가 같은데 입실 월 퇴실 월보다 더 크면 false
		if((startDateYear == endDateYear) && (startDateMonth > endDateMonth)) {
			return false;
		}
		//입실 년도와 퇴실 년도 같고, 입실 월과 퇴실 월이 같은데 입실 일이 퇴실 일보다 크면 false
		if((startDateYear == endDateYear) && (startDateMonth == endDateMonth) && (startDateDay >= endDateDay)) {
			return false;
		}
		//모든 조건 통과하면 true
		return true;
	}

	//최대인원수 체크
	public boolean checkMaxPeople(String headCount) {
		double cnt = Double.parseDouble(headCount);
		if(cnt<1 || cnt>6) { //1보다 작거나 6보다 크면 false
			return false;
		}
		if(cnt%1 != 0) { //정수가 아니면 false
			return false;
		}
		return true;
	}

	//숙소 검색
	public List<LodgingVo> showLodgingList(LodgingReservationVo vo) {
		Connection conn = null;
		List<LodgingVo> LodgingVoList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			LodgingVoList = new LodgingDao().showLodgingList(conn, vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return LodgingVoList;
	}

	//추천 숙소 조회
	public List<LodgingVo> showRecommendLodgingList() {
		Connection conn = null;
		List<LodgingVo> LodgingVoList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			LodgingVoList = new LodgingDao().showRecommendLodgingList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return LodgingVoList;
	}

	//인기 숙소 조회
	public List<LodgingVo> showPopularodgingList() {
		Connection conn = null;
		List<LodgingVo> LodgingVoList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			LodgingVoList = new LodgingDao().showPopularLodgingList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return LodgingVoList;
	}

	public List<RoomVo> showRoom(LodgingReservationVo vo) {
		Connection conn = null;
		List<RoomVo> roomVoList = null;
		try {
			conn = JDBCTemplate.getConnection();
			roomVoList = new LodgingDao().showRoom(vo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return roomVoList;
	}

	public RoomVo showChoice(LodgingReservationVo vo) {
		Connection conn = null;
		RoomVo roomVo = null;
		try {
			conn = JDBCTemplate.getConnection();
			roomVo = new LodgingDao().showChoice(vo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return roomVo;
	}

	public List<LodgingCouponVo> checkCoupon(int no) {
		Connection conn = null;
		List<LodgingCouponVo> lodgingCouponVoList = null;
		try {
			conn = JDBCTemplate.getConnection();
			lodgingCouponVoList = new LodgingDao().checkCoupon(no, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return lodgingCouponVoList;
	}

	public int updateCouponIssued(int couponIssuedNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().updateCouponIssued(couponIssuedNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int insertReservation(LodgingReservationVo rv) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().insertReservation(rv, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int getReservationNo(LodgingReservationVo rv) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().getReservationNo(rv, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int insertPayment(LodgingReservationVo rv) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().insertPayment(rv, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public List<LodgingReservationVo> showMyReserVation(String num) {
		Connection conn = null;
		List<LodgingReservationVo> voList = null;
		try {
			conn = JDBCTemplate.getConnection();
			voList = new LodgingDao().showMyReservation(num, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return voList;
	}

	
	public int updateCancelY(String input) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().updateCancelY(input, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public boolean checkBfYn(String updateNo) {
		Connection conn = null;
		Boolean isBf = false;
		try {
			conn = JDBCTemplate.getConnection();
			isBf = new LodgingDao().checkBfYn(updateNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return isBf;
	}

	public int cancelBf(String updateNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().cancelBf(updateNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int addBf(String updateNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().addBf(updateNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public boolean checkEmpty(int roomNo, String startDate, String endDate) {
		Connection conn = null;
		Boolean isEmpty = false;
		try {
			conn = JDBCTemplate.getConnection();
			isEmpty = new LodgingDao().checkEmpty(roomNo, startDate, endDate, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return isEmpty;
	}

	public int selectRoomNo(String updateNo) {
		Connection conn = null;
		int roomNo = 0;
		try {
			conn = JDBCTemplate.getConnection();
			roomNo = new LodgingDao().selectRoomNo(updateNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return roomNo;
	}

	public int updateStartEndDate(String updateNo, String startDate, String endDate) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().updateStartEndDate(updateNo, startDate, endDate, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public String checkZzimCancel(String lodgingNo) {
		Connection conn = null;
		String zzimCancel = "";
		try {
			conn = JDBCTemplate.getConnection();
			zzimCancel = new LodgingDao().checkZzimCancel(lodgingNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return zzimCancel;
	}

	public int zzimInsert(String lodgingNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().zzimInsert(lodgingNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int updateZzimCancelY(String lodgingNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().updateZzimCancelY(lodgingNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int updateZzimCancelN(String lodgingNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingDao().updateZzimCancelN(lodgingNo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int selectLodgingNo(String updateNo) {
		Connection conn = null;
		int lodgingNo = 0;
		try {
			conn = JDBCTemplate.getConnection();
			lodgingNo = new LodgingDao().selectLodgingNo(updateNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return lodgingNo;
	}

	public List<LodgingReservationVo> selectTotalReservation() {
		Connection conn = null;
		List<LodgingReservationVo> voList = null;
		try {
			conn = JDBCTemplate.getConnection();
			voList = new LodgingDao().selectTotalReservation(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return voList;
		
	}

	public List<LodgingVo> showLodgingInformation(int num) {
		Connection conn = null;
		List<LodgingVo> voList = null;
		try {
			conn = JDBCTemplate.getConnection();
			voList = new LodgingDao().showLodgingInformation(num, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return voList;
		
	}

	public int getReviewCnt(int num) {
		Connection conn = null;
		int reviewCnt = 0;
		try {
			conn = JDBCTemplate.getConnection();
			reviewCnt = new LodgingDao().getReviewCnt(num, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return reviewCnt;
	}

	public int getZzimCnt(int num) {
		Connection conn = null;
		int zzimCnt = 0;
		try {
			conn = JDBCTemplate.getConnection();
			zzimCnt = new LodgingDao().getZzimCnt(num, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return zzimCnt;
	}

	public int getMinPrice(int num) {
		Connection conn = null;
		int minPrice = 0;
		try {
			conn = JDBCTemplate.getConnection();
			minPrice = new LodgingDao().getMinPrice(num, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return minPrice;
	}

	

}
