package trip.dk.event;


import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trip.dk.coupon.CouponVo;
import trip.min.main.MemberMain;

public class EventDao {


	public List<EventVo> showEventList(Connection conn) throws Exception {
		//CONN 준비
		//SQL 준비
		String sql = "SELECT NO,START_DATE,END_DATE FROM EVENT";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<EventVo> eventVoList = new ArrayList<>();

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String no = rs.getString("NO");
				String startDate = rs.getString("START_DATE");
				String endDate = rs.getString("END_DATE");

				EventVo ev = new EventVo();
				ev.setNo(no);
				ev.setStartDate(startDate);
				ev.setEndDate(endDate);


				eventVoList.add(ev);

			}

		} finally {

			close(rs);
			close(pstmt);
		}
	return eventVoList;
	}

	public EventVo showDetailByNo(Connection conn, String num) throws Exception{

		//connection 준비

				//SQL 준비
				 String sql = "SELECT NO,START_DATE,END_DATE FROM EVENT WHERE NO = ?";

				PreparedStatement pstmt =null;
				ResultSet rs = null;
				EventVo ev = null;

				try {
					//SQL 담을 객체 준비 및 SQL 완성
					pstmt =conn.prepareStatement(sql);
					pstmt.setString(1, num);
					//SQL실행 및 결과 저장
					rs = pstmt.executeQuery();

					// 결과셋 ->자바객체
					if(rs.next()) {
						String no = rs.getString("NO");
						String startDate = rs.getString("START_DATE");
						String endDate = rs.getString("END_DATE");

						ev = new EventVo();
						ev.setNo(no);
						ev.setStartDate(startDate);
						ev.setEndDate(endDate);

					}

				} catch(SQLException e) {
					e.printStackTrace();
					throw e;


				}finally {
					close(pstmt);
					close(rs);
				}

				//실행 결과(자바객체) 리턴
		return ev;
	}

 //
	public int participateCheck(Connection conn,EventParticipateVo epv) throws Exception {
		int result= 0;
		PreparedStatement pstmt = null;

		try {

			//SQL준비
			String sql = "INSERT INTO EVENT_PARTICIPATE(NO,MEMBER_NO,EVENT_NO,PARTICIPATE_YN) VALUES(SEQ_EVENT_PARTICIPATE_NO.NEXTVAL,?,?,?)";

			//SQL 담을 객체
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, epv.getMemberNo());
			pstmt.setString(2, epv.getEventNo());
			pstmt.setString(3, epv.getEventYn());

			//SQL 실행 및 결과 저장
			result= pstmt.executeUpdate();
			
			



		} catch (Exception e) {
			throw e;

		} finally {
			close(pstmt);
		}
		return result;

	}
	
	
	
	//참여 업데이트

//	public int participateUpdate(Connection conn,EventParticipateVo epv) throws Exception {
//		int result= 0;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			//SQL준비
//			String sql = "UPDATE EVENT_PARTICIPATE SET PARTICIPATE_YN = 'Y' WHERE MEMBER_NO = ? and EVENT_NO = ?";
//			
//
//			//SQL 담을 객체
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, epv.getMemberNo());
//			pstmt.setString(2, epv.getEventNo());
//
//			//SQL 실행 및 결과 저장
//			result= pstmt.executeUpdate();
//
//
//		} catch (Exception e) {
//			throw e;
//
//		} finally {
//			close(pstmt);
//		}
//		return result;
//
//	}
	
	
	
	
	
	//참여 화긴용 
	public EventParticipateVo EventCheckYN (Connection conn, String num) throws Exception {
		//connection 준비
		
		
		//SQL 준비
		 String sql = "SELECT P.NO,P.MEMBER_NO,P.EVENT_NO,P.PARTICIPATE_YN FROM EVENT_PARTICIPATE P JOIN MEMBER M ON M.NO = P.MEMBER_NO JOIN EVENT E ON E.NO = P.EVENT_NO WHERE M.NO = ? AND P.EVENT_NO = ? AND P.PARTICIPATE_YN = 'Y'";

		PreparedStatement pstmt =null;
		ResultSet rs = null;
		EventParticipateVo epv = null;
		epv = new EventParticipateVo();
		epv.setEventYn("N");
		
		try {
			//SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberMain.LoginMember.getNo());
			pstmt.setString(2, num);
			//SQL실행 및 결과 저장
			rs = pstmt.executeQuery();

			// 결과셋 ->자바객체
			if(rs.next()) {
				String participateNo=rs.getString("NO");
				String meberNo = rs.getString("MEMBER_NO");
				String eventNo = rs.getString("EVENT_NO");
				String eventYn = rs.getString("PARTICIPATE_YN");
				
				epv.setParticipateNo(participateNo);
				epv.setMemberNo(meberNo);
				epv.setEventNo(eventNo);
				epv.setEventYn("Y");
				
				

			}

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;


		}finally {
			close(pstmt);
			close(rs);
		}
		
		return epv;
		
	}



}
