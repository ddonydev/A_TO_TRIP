package trip.dk.event;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import static trip.dk.common.JDBCTemplate.*;

public class EventDao {
	

	public List<EventVo> showEventList(Connection conn) throws Exception {
		//CONN 준비
		//SQL 준비
		String sql = "SELECT NO,START_DATE,END_DATE FROM EVENT";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<EventVo> eventVoList = new ArrayList<EventVo>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
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

	public EventVo showDetailByNo(Connection conn, int num) throws Exception{
		
		//connection 준비
		
				//SQL 준비
				 String sql = "SELECT NO,START_DATE,END_DATE FROM EVENT WHERE NO = ?";
				
				PreparedStatement pstmt =null;
				ResultSet rs = null;
				EventVo ev = null;
				
				try {
					//SQL 담을 객체 준비 및 SQL 완성
					pstmt =conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					//SQL실행 및 결과 저장
					rs = pstmt.executeQuery();
					
					// 결과셋 ->자바객체
					if(rs.next()) {
						int no = rs.getInt("NO");
						String startDate = rs.getString("START_DATE");
						String endDate = rs.getString("END_DATE");
						
						ev = new EventVo();
						ev.setNo(no);
						ev.setStartDate(startDate);
						ev.setEndDate(endDate);
						
					};
					
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
	
	
	

}
