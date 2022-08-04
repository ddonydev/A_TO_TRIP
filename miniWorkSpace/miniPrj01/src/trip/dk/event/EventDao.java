package trip.dk.event;


import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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



}
