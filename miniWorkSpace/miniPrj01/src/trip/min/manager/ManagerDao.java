package trip.min.manager;

import static trip.min.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trip.hyewon.lodging.LodgingVo;
import trip.min.common.JDBCTemplate;
import trip.min.util.InputUtil;

public class ManagerDao {

	public int InsertLodging(LodgingVo vo, Connection conn) throws Exception {
		//숙소 등록
		//DB insert
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			
			//SQL 준비
			String sql = "INSERT INTO LODGING_INFORMATION(NO, LODGING_CODE, NAME, PHONE, ADDRESS, LODGING_LIKE, BREAKFAST_YN, LOCATION_CODE) VALUES (SEQ_LODGING_INFORMATION_NO.NEXTVAL,?,?,?,?,?,'N',?)";
			
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLodgingCode());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getLodgingLike());
			pstmt.setString(6, vo.getLocationCode());
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}//InsertLodging
	
	public int UpdateLodging(LodgingVo vo, Connection conn) throws Exception {
		//숙소 수정

		PreparedStatement pstmt = null;
		int result = 0;
		
			// SQL 준비
		String sql = "UPDATE LODGING_INFORMATION SET LODGING_CODE = ?, NAME = ?, PHONE = ?, ADDRESS = ?, LODGING_LIKE = ?, BREAKFAST_YN = ?, LOCATION_CODE = ? WHERE NO = ?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getLodgingCode());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getPhone());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getLodgingLike());
				pstmt.setString(6, vo.getBreakfastYn());
				pstmt.setString(7, vo.getLocationCode());
				pstmt.setString(8, vo.getNo());
				
				result = pstmt.executeUpdate();
				
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
		return result;
	}//Update
	
}
