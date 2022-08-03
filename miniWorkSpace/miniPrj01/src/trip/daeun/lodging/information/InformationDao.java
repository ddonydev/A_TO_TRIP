package trip.daeun.lodging.information;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import trip.daeun.lodging.common.JDBCTemplate;

public class InformationDao {

	/*
	 * 숙소 정보 상세 조회
	 */

	public InformationVo showDetailByNo(Connection conn, int num) throws Exception {

		// connection 준비
		conn = JDBCTemplate.getConnection();

		// SQL 준비
		String sql = "SELECT L.NO, L.NAME , T.R_TYPE , R.PRICE , L.BREAKFAST_YN , L.ADDRESS FROM LODGING_INFORMATION L JOIN ROOM R ON L.NO = R.LODGING_NO JOIN ROOM_TYPE T ON R.ROOM_CODE = T.CODE WHERE L.NO = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InformationVo vo = null;

		try {
			// SQL 객체에 담기 및 쿼리 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();

			// ResultSet -> 자바 객체로
			if (rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String room = rs.getString("R_TYPE");
				int price = rs.getInt("PRICE");
				String breakfast = rs.getString("BREAKFAST_YN");
				String address = rs.getString("ADDRESS");

				vo = new InformationVo();
				vo.setName(name);
				vo.setRoom(room);
				vo.setPrice(price);
				vo.setBreakfast(breakfast);
				vo.setAddress(address);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		// 실행결과(자바 객체) 리턴
		return vo;

	}

	
}

