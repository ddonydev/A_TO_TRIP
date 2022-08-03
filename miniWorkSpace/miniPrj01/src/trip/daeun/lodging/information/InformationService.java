package trip.daeun.lodging.information;


import java.sql.Connection;

import trip.min.common.JDBCTemplate;



public class InformationService {
	
	/*
	 * 숙소 상세조회
	 * 
	 * 
	 * */
	
	public InformationVo showDetailByNo(int num) {

		Connection conn = null;
		InformationVo vo = null;

		try {

			conn = JDBCTemplate.getConnection();
			vo = new InformationDao().showDetailByNo(conn, num);

		} catch (Exception e) {
			System.out.println("[ERROR] 숙소 조회 중 예외 발생 !!!");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return vo;
	}


	
}
